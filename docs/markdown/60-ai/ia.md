<!-- .slide: class="transition" -->

# Architectures Applicatives d'IA Générative

## Du Chatbot à l'Agent Autonome

##==##

<!-- .slide: class="with-code" -->

# La découverte des IA Gen - le ChatBot

* Découverte pour le Grand Public du monde de l'IA Gen - ChatGPT

* Intelligence Artificelle - aannée 80 - travaux sur l'architecture neuronale

* Deep Blue bat Kasparov - 1997

Notes:
Bonjour à tous. Aujourd'hui, nous allons dépasser le simple "prompt engineering" pour plonger dans l'architecture logicielle des systèmes d'IA modernes.
Nous allons voir comment on transforme un LLM probabiliste et halluciant en un système fiable, capable d'agir sur le monde réel.
Le cours est divisé en trois solutions architecturales à trois problèmes majeurs, suivi d'une démonstration pratique sur un moteur de Jeu de Rôle.

##==##

<!-- .slide: class="with-code" -->

# Le Problème de la "Production"

**Pourquoi les Chatbots échouent-ils?**

1.  **L'Illusion de l'Intelligence** : Le modèle ne "sait" rien, il prédit le prochain mot.

2.  **L'Amnésie (Stateless)** : Chaque requête est une nouvelle naissance.

3.  **L'Inaction** : Un cerveau dans un bocal ne peut pas cliquer sur un bouton.

Notes:
Si vous lancez un LLM brut en production aujourd'hui, vous heurtez trois murs.

1.  Il hallucine car il compresse la connaissance avec perte.
2.  Il ne se souvient pas de vous (c'est l'app qui gère l'historique, pas le modèle).
3.  Il ne peut pas agir sur vos systèmes (API, Bases de données).
    Ce cours présente les solutions architecturales à ces trois murs.

##==##

<!-- .slide: class="with-code" -->

# Le Mur de la Connaissance

**Solution 1 : Le RAG (Retrieval-Augmented Generation)**

Injecter la **Vérité Terrain** dans le contexte avant l'inférence.

> "Ne devine pas. Lis ceci, puis réponds."

Notes:
Le premier défi est l'hallucination. Le modèle ne doit pas utiliser sa mémoire d'entraînement (figée) mais une mémoire externe.
Le RAG transforme un problème de génération en un problème de recherche d'information.
On ne demande pas au modèle de réciter par cœur, on lui donne le manuel.

##==##

<!-- .slide: class="full-center" -->

# Architecture RAG de Production

![h-400](./assets/images/rag.png)
<!-- 
```mermaid
flowchart LR
  User(Requête) --\> Query[Query Expansion]
  Query --\> Hybrid{Recherche Hybride}
  Hybrid --\> Vector[(Vecteurs)]
  Hybrid --\> Kw[(Mots-Clés)]
  Vector & Kw --\> Rerank
  Rerank --\> Context(Contexte Filtré)
  Context --\> LLM(Génération)
``` 
-->

Notes:
Le "RAG Naïf" (juste des vecteurs) ne suffit pas en prod.
Si je cherche un code d'erreur spécifique "X-99", le vecteur peut échouer.
L'architecture robuste combine :

* **Vecteurs** : Pour le sens sémantique (*"problème d'écran"*).
* **Mots-clés** : Pour la précision exacte (*"Erreur 404"*).
* **Re-ranking** : Trier les résultats par pertinence réelle.

1.  Recherche sémantique (Vecteurs).

2.  Recherche lexicale (BM25/Mots-clés).

3.  Une étape de "Re-ranking" (Cross-encoder) pour ne garder que les 3 meilleurs documents sur les 50 trouvés.

##==##

<!-- .slide: class="with-code" -->

# Le Mur de l'Action

**Comment connecter l'IA au monde?**

  * **Avant 2024** : Intégrations ad-hoc et fragiles.

  * **Le problème N x M** :
      * N Modèles (GPT-4, Claude, Llama...)

      * M Outils (Google Drive, Slack, GitHub...)

      * `= N*M` connecteurs à maintenir.

Notes:
Une fois que l'IA "sait", elle doit "agir".
Historiquement, on codait des fonctions spécifiques pour OpenAI, puis d'autres pour Anthropic. C'était l'enfer de la maintenance.
Si on change de modèle, on casse tout. Si on change d'outil, on casse tout.

##==##

<!-- .slide: class="with-code" -->

# Solution 2 : MCP

**Model Context Protocol**

Le **standard USB-C** pour les applications d'IA.

  * **Standard Ouvert** : Pas de vendor lock-in.
  
  * **Découplage** : Le modèle ne sait pas *comment* l'outil marche, juste *qu'il existe*.
  
  * **Sécurité** : Contrôle humain avant exécution - réintégrer le développement logique dans l'IA.

Notes:
Introduit par Anthropic mais open-source.
L'idée : créer un protocole universel pour exposer des données et des outils.
Une application (Claude, Cursor, ou votre app Python) peut se connecter à n'importe quel serveur MCP.

##==##

<!-- .slide: class="full-center" -->

# Architecture MCP


![h-800](./assets/images/mcp.png)

<!-- 
```mermaid
sequenceDiagram
  participant Host as Application IA (Host)
  participant Client as Client MCP
  participant Server as Serveur MCP
  participant Resource as DB / API

Host->>Client: "J'ai besoin de lire un fichier"
Client->>Server: ListTools() / ListResources()
Server--\>>Client: Capacités Disponibles
Host->>Client: Execute "ReadFile"
Client->>Server: CallTool("read_file", path)
Server->>Resource: Lecture Disque
Resource--\>>Server: Contenu
Server--\>>Client: Résultat JSON
Client--\>>Host: Contexte pour le LLM
```

-->

Notes:
L'architecture est stricte :

1.  **Host** : Votre application (le cerveau).
2.  **Server** : Un processus indépendant qui détient les outils (les mains).
3.  **Transport** : JSON-RPC via Stdio ou HTTP.
    Le serveur expose 3 primitives : Resources (Lecture), Tools (Action), Prompts (Templates).

##==##

<!-- .slide: class="with-code" -->

# Le Mur de la Complexité

**Solution 3 : Les patterns d'architectures d'agents**

* Le Router

* l'Orchestrateur

* Le sequentiel ou Chaine de prompt (Production dans les résaux sociaux)

* Le réseau ou Swarm

##==##

<!-- .slide: class="with-code" -->

## L'Orchestration

Un **Agent** est une boucle cognitive :

1.  Observer
2.  Raisonner (Planifier)
3.  Agir (Tool Call)
4.  Observer le résultat
5.  *Répéter...*

Notes:
Avoir des outils ne suffit pas. Il faut savoir quand les utiliser.
C'est la différence entre un Chatbot (Réponse directe) et un Agent (Boucle de résolution).
L'agent peut se corriger, essayer autre chose si l'outil échoue.

##==##

<!-- .slide: class="with-code" -->

# De la Chaîne au Graphe

**Introduction à LangGraph**

Les chaînes linéaires (LangChain classique) sont trop rigides.
**LangGraph** modélise l'application comme un automate à états finis.

  * **Nodes** : Les agents ou fonctions.
  * **Edges** : La logique de transition.
  * **State** : La mémoire partagée (`TypedDict`).

Notes:
Dans la vraie vie, les processus ne sont pas linéaires. Il y a des boucles, des conditions, des retours en arrière.
LangGraph permet de définir ces cycles.
Le concept clé est le "State" : un objet JSON qui circule et que chaque nœud modifie.

##==##

<!-- .slide: class="full-center" -->

# Pattern Multi-Agents : Le Superviseur

![h-800](./assets/images/agent.png)

<!-- 
```mermaid
stateDiagram-v2
    [*] --\> Superviseur
    Superviseur --\> Coder: Délégation
    Superviseur --\> Tester: Délégation
    Coder --\> Superviseur: Code généré
    Tester --\> Superviseur: Rapport de bug
    Superviseur --\> [*]: Tâche Finie
``` 
-->

##==##

# L'Alternative "Swarm" (Essaim)

## La Collaboration Décentralisée

Contrairement au Graphe (rigide), le **Swarm** est organique.

  * **Concept clé : Le Handoff (Passage de relais)**
      * L'Agent A ne résout pas tout.
      * Il décide explicitement : *"Je passe la main à l'Agent B"*.

  * **Stateless** : Pas de mémoire globale. L'intelligence est dans le transfert.
  * *Analogie :* Un standard téléphonique (Accueil -\> Support Technique -\> Facturation).

Notes:
Il existe une autre philosophie : OpenAI Swarm.
Ici, pas de "Cerveau Central" qui supervise tout le monde.
Chaque agent est très bête et très spécialisé. Son seul pouvoir est de dire "Ce n'est pas mon job, je transfère à l'agent X".
C'est beaucoup plus léger et rapide à exécuter qu'un graphe d'état complexe.

##==##

<!-- .slide: class="full-center" -->

# L'Alternative "Swarm" (Essaim)

![h-900](./assets/images/swarm.png)

##==##

# LangGraph vs Swarm : Le Match

| LangGraph (StateFull) | Swarm (StateLess / Handoff) |
| :--- | :--- |
| **Workflow Métier Strict** (Assurance, Banque) | **Conversation Fluide** (Support Client) |
| Mémoire globale persistante | Légèreté & Rapidité |
| Centralisé (Orchestrateur) | Décentralisé (Réseau) |

##==##

# Conclusion

> **Conclusion 2025** : L'Hybride. Un nœud du graphe peut être un Swarm d'agents.

* Quand choisir quoi ?
* "Divide & Conquer"

Notes:
Quand choisir quoi ?
Si vous devez garantir qu'une étape B suit toujours l'étape A (ex: validation de paiement), utilisez LangGraph. C'est robuste.
Si vous voulez une conversation naturelle où l'utilisateur saute du coq à l'âne ("Je veux acheter" -\> "Ah non, j'ai un problème technique"), le Swarm est meilleur car les agents se passent la balle dynamiquement.

**Divide & Conquer** : Des petits modèles spécialisés valent mieux qu'un gros modèle généraliste.

Pour des tâches complexes, on utilise plusieurs agents.
Le pattern "Superviseur" est classique : un agent "Chef" décompose la tâche et l'envoie à des experts.
Cela réduit le bruit dans le contexte de chaque agent et améliore la performance.

##==##

<!-- .slide: class="with-code" -->

# Mise en Pratique : Le "JDR en IA"

**Démonstration d'Architecture**

1.  **Règles strictes** (Business Logic)
2.  **Créativité** (Interaction Joueur)
3.  **Incertitude** (Jets de dés = Risques)

Notes:
Nous allons voir une démo d'un Maître de Jeu Artificiel.
Ne voyez pas ça comme un jeu, mais comme une simulation de gestion de contraintes.
L'IA doit raconter une histoire (Créatif) tout en respectant des règles mathématiques strictes (Déterminisme).

##==##

<!-- .slide: class="full-center" -->

# Architecture de la Démo


![h-800](./assets/images/jdr-demo.png)

<!-- 
```mermaid
graph TD
    User((Joueur)) --\> Orch{{Orchestrateur}}
    Orch --\>|Dialogue| Rules["Connaisseur de règles"]
    Orch --\>|Dialogue| Character[Agent Personnage]
    Orch --\>|"requête"| Dice(Lancé de dé)


Rules --\> vector[(PDF de règles)]
Dice --\> script[[Sous-processus]]

```
-->

Notes:
Regardez bien cette architecture :

  - Le **Routeur** trie l'intention.
  - L'agent **Règles** ne génère pas de texte, il manipule des chiffres via le serveur MCP "Dés". Il est interdit d'halluciner un résultat.
  - L'agent **Narrateur** embellit le résultat en consultant le serveur MCP "Lore" (RAG).
  - Tout l'état est stocké dans un objet global persistant.

##==##

<!-- .slide: class="with-code" -->

# LIVE DEMO

**(Console / Interface Web)**

*Focus à observer :*

1.  Le déterministe (Le LLM n'invente pas le jet de dé).
2.  La persistance (AA cette étape aucun stockage de narration).
3.  La collaboration (Le Narrateur utilise le résultat des Règles).

Notes:
Je bascule sur la démo.
(Pendant la démo : Montrer les logs. Montrer que quand je dis "J'attaque", c'est l'outil Python qui tourne, pas le LLM qui devine).

##==##

<!-- .slide: class="with-code" -->

# Conclusion : L'Avenir

**Vers l'Internet des Agents**

  * **2023** : Chatbots (Texte in/out).
  * **2024** : RAG (Lecture seule).
  * **2025** : Agents MCP (Lecture/Écriture standardisée).

Notes:
Pour conclure, nous passons d'une ère de consommation de contenu à une ère d'action déléguée.
Votre rôle d'architecte est de construire ces endpoints MCP et ces graphes de décision.
Merci de votre attention.

##==##

<!-- .slide: class="transition-bg-blue-2" -->

# Questions ?

