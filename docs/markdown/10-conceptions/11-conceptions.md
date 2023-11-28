<!-- .slide: class="transition" -->

# Conceptions logiciels

##==##

<!-- .slide: class="full-center" -->

# Model View Controller

![h-800](./assets/images/mvc.svg)

Notes:

Model: règles métier et gestion des données
=> responsable de la logique métier
Vue: Couche d'adaption du modèle pour un affichage compréhensible
Controlleur: Interface entre la vue et le modèle

Ex: Spring MVC

##--##

<!-- .slide: class="with-code" -->

# Model View Controller (avis)

![sfeir-icons](plus-circle)<!-- .element: style="--icon-color:green;"  --> Duplicatat de code réduit

![sfeir-icons](plus-circle)<!-- .element: style="--icon-color:green;"  --> Testabilité

![sfeir-icons](plus-circle)<!-- .element: style="--icon-color:green;"  --> Séparation des responsabilités

![sfeir-icons](minus-circle)<!-- .element: style="--icon-color:red;"  --> Le métier peut-être perdu au milieu de l'ensemble

![sfeir-icons](minus-circle)<!-- .element: style="--icon-color:red;"  --> Couplage trop fort potentiel

![sfeir-icons](minus-circle)<!-- .element: style="--icon-color:red;"  --> Adaptation à la complexité de l'Interface utilisateur

##==##

<!-- .slide: class="full-center" -->

# Hexagonale

![h-800](./assets/images/hexa.svg)

Notes:

- Noyau
- port primaire et secondaire
- adapter primaire et secondaire

=> Vision modulaire tournant autour d'un noyau métier

##--##

<!-- .slide: class="full-center" -->

# Hexagonale autre visualisation

![h-800](./assets/images/hexa-2.png)

##--##

<!-- .slide: class="with-code-bg-dark" -->

# Conception hexagonale

```java
// Classe représentant la logique métier (Noyau)
public class GestionProduits {

    private ProduitRepository produitRepository; // Port secondaire

    // Constructeur prenant le port secondaire en paramètre
    public GestionProduits(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    // Méthode métier pour ajouter un produit
    public void ajouterProduit(String nom, double prix) {
        // Logique métier ici, par exemple, vérifications, calculs, etc.
        
        // Appel au port secondaire pour persister le produit
        Produit nouveauProduit = new Produit(nom, prix);
        produitRepository.save(nouveauProduit);
    }

    // Méthode métier pour récupérer la liste des produits
    public List<Produit> listerProduits() {
        // Logique métier ici si nécessaire
        
        // Appel au port secondaire pour récupérer la liste des produits
        return produitRepository.getAll();
    }
}

// Classe représentant un produit (entité)
public class Produit {
    private String nom;
    private double prix;

    public Produit(String nom, double prix) {
        this.nom = nom;
        this.prix = prix;
    }

    // Getters et setters éventuels
}

// Port secondaire (interface) pour la gestion des produits
public interface ProduitRepository {
    void save(Produit produit);
    List<Produit> getAll();
}
```


##--##

<!-- .slide: class="with-code" -->

# Hexagonale (avis)

![sfeir-icons](plus-circle)<!-- .element: style="--icon-color:green;"  --> Testabilité du business renforcé

![sfeir-icons](plus-circle)<!-- .element: style="--icon-color:green;"  --> Flexibilité Technologique

![sfeir-icons](plus-circle)<!-- .element: style="--icon-color:green;"  --> Évolutivité et Maintenabilité

![sfeir-icons](plus-circle)<!-- .element: style="--icon-color:green;"  --> Indépendance des Frameworks

![sfeir-icons](minus-circle)<!-- .element: style="--icon-color:red;"  --> Pattern peu pratiqué

![sfeir-icons](minus-circle)<!-- .element: style="--icon-color:red;"  --> Concept peu intuitif au première abord

![sfeir-icons](minus-circle)<!-- .element: style="--icon-color:red;"  --> Amène de la sur-complexité potentielle

##==##

<!-- .slide: class="full-center" -->

# Domain Driven Design

![h-800](./assets/images/ddd.svg)

##--##

<!-- .slide: class="with-code" -->

# Domain Driven Design (avis)

![sfeir-icons](plus-circle)<!-- .element: style="--icon-color:green;"  --> Conception peu connue

![sfeir-icons](plus-circle)<!-- .element: style="--icon-color:green;"  --> Ouvre facilement aux discussions fonctionnelles

![sfeir-icons](plus-circle)<!-- .element: style="--icon-color:green;"  --> Evite l'over-ingenierie

![sfeir-icons](plus-circle)<!-- .element: style="--icon-color:green;"  --> Objectif commun entre le business ET les développeurs

![sfeir-icons](plus-circle)<!-- .element: style="--icon-color:green;"  --> La plus intéressante à mettre en place

![sfeir-icons](plus-circle)<!-- .element: style="--icon-color:green;"  --> Tests

![sfeir-icons](minus-circle)<!-- .element: style="--icon-color:red;"  --> Conception peu répandue

![sfeir-icons](minus-circle)<!-- .element: style="--icon-color:red;"  --> Peu évolutif

![sfeir-icons](minus-circle)<!-- .element: style="--icon-color:red;"  --> Le métier doit être correctement communiqué et interprété

![sfeir-icons](minus-circle)<!-- .element: style="--icon-color:red;"  --> Coût
