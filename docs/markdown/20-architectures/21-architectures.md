<!-- .slide: class="transition" -->

# Architectures logiciels

##==##

<!-- .slide: class="full-center" -->

# Monobloc

![h-800](./assets/images/monobloc.svg)

Notes:

- Application unique, un seul code source
- sans découpage en services indépendants
- Développement centralisé
- Appels de fonctions/dépendances internes
- Communication d'équipe plus intégrée

##==##

<!-- .slide: class="with-code" -->

# Monobloc (avis)

![sfeir-icons](plus-circle)<!-- .element: style="--icon-color:green;"  --> Simplicité de développement

![sfeir-icons](plus-circle)<!-- .element: style="--icon-color:green;"  --> Simplicité de déploiement

![sfeir-icons](plus-circle)<!-- .element: style="--icon-color:green;"  --> Dans le cadre d'un POC

![sfeir-icons](plus-circle)<!-- .element: style="--icon-color:green;"  --> Debug Facilité

![sfeir-icons](minus-circle)<!-- .element: style="--icon-color:red;"  --> Appels synchrones

![sfeir-icons](minus-circle)<!-- .element: style="--icon-color:red;"  --> Attention à l'effet on verra plus tard

![sfeir-icons](minus-circle)<!-- .element: style="--icon-color:red;"  --> Effet boîte-noire

![sfeir-icons](minus-circle)<!-- .element: style="--icon-color:red;"  --> Complexité de pérennisation

![sfeir-icons](minus-circle)<!-- .element: style="--icon-color:red;"  --> Scalabilité complexifié pour plus une évolution verticale

##==##

<!-- .slide: class="full-center" -->

# Microservice

![h-800](./assets/images/microservice.svg)

Notes:

- Composé de services indépendants
- Communication légère entre services (HTTP/REST)
- Chaque service a sa propre base de données
- Les pannes d'un service n'impactent pas l'ensemble
- Bien adapté aux grandes applications avec de nombreuses fonctionnalités
- Équipes de développement indépendantes
- Communication entre équipes cruciale
- Stateless

##==##

<!-- .slide: class="with-code" -->

# Microservice (avis)

![sfeir-icons](plus-circle)<!-- .element: style="--icon-color:green;"  --> Fortement utilisé

![sfeir-icons](plus-circle)<!-- .element: style="--icon-color:green;"  --> Découpage par Domaine Métier

![sfeir-icons](plus-circle)<!-- .element: style="--icon-color:green;"  --> Déploiement et Évolutivité Indépendants

![sfeir-icons](plus-circle)<!-- .element: style="--icon-color:green;"  --> Couplage faible entre services

![sfeir-icons](plus-circle)<!-- .element: style="--icon-color:green;"  --> Favorable à la communication asynchrone

![sfeir-icons](minus-circle)<!-- .element: style="--icon-color:red;"  --> Sécurité

![sfeir-icons](minus-circle)<!-- .element: style="--icon-color:red;"  --> Recommendation d'avoir une plateforme CI aboutie

![sfeir-icons](minus-circle)<!-- .element: style="--icon-color:red;"  --> Mise en place essentiel d'outils d'observabilité

##==##

<!-- .slide: class="full-center" -->

# Pipeline ou Messaging asynchrone

![h-800](./assets/images/messaging.svg)

Notes:

Ensemble d'étapes distinctes indépendantes
1. Réception
2. Validation
3. Traitement
4. Préparation livraison
5. Confirmation ?

##==##

<!-- .slide: class="with-code" -->

# Pipeline ou Messaging asynchrone (avis)

![sfeir-icons](plus-circle)<!-- .element: style="--icon-color:green;"  --> Cas spécifique (DevOps, EDI, ...)

![sfeir-icons](plus-circle)<!-- .element: style="--icon-color:green;"  --> Gestion fine des ressources nécessaires selon chaque étape

![sfeir-icons](plus-circle)<!-- .element: style="--icon-color:green;"  --> Recherche d'amélioration pour la résilience, la réactivité et la scalabilité

![sfeir-icons](plus-circle)<!-- .element: style="--icon-color:green;"  --> Testabilité de chaque étape indépendendante

![sfeir-icons](plus-circle)<!-- .element: style="--icon-color:green;"  --> Réflexion step-by-step

![sfeir-icons](minus-circle)<!-- .element: style="--icon-color:red;"  --> Gestion d'un "cache" transverse

![sfeir-icons](minus-circle)<!-- .element: style="--icon-color:red;"  --> Attention à l'asynchrone

![sfeir-icons](minus-circle)<!-- .element: style="--icon-color:red;"  --> Tracabilité de la chaîne complète
