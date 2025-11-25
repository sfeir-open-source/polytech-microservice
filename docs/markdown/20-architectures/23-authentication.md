<!-- .slide: class="transition" -->

# Authentification et Autorisation

##==##

<!-- .slide:  -->

# Le Monolithe : Un seul point d'entrée


![h-600](./assets/images/auth-monolithe.png)

Sécurité gérée à la porte d'entrée.
Session partagée en mémoire.

##==##

<!-- .slide:  -->

## Microservices : Multiples points d'entrée

![h-600](./assets/images/auth-microservice.png)

Chaque service est une forteresse potentielle.
Comment propager l'identité de l'appelant ?

##==##

<!-- .slide: class="with-code" -->

# Authentification dans les microservices

![sfeir-icons](plus-circle)<!-- .element: style="--icon-color:green;"  --> **API Gateway / BFF** : Point d'entrée unique pour l'authentification. Le client s'authentifie auprès de la passerelle, qui propage ensuite l'identité aux services en aval.

![sfeir-icons](plus-circle)<!-- .element: style="--icon-color:green;"  --> **Token-based (JWT)** : Utilisation de JSON Web Tokens pour une authentification stateless. Chaque service peut valider le token de manière indépendante.

![sfeir-icons](plus-circle)<!-- .element: style="--icon-color:green;"  --> **OAuth2 / OpenID Connect** : Standards pour la délégation d'autorisation et l'authentification. Idéal pour les applications tierces et le single sign-on (SSO).

![sfeir-icons](circle)<!-- .element: style="--icon-color:grey;"  --> **Sécurité** : La propagation des informations d'identification doit être sécurisée (ex: TLS).

![sfeir-icons](minus-circle)<!-- .element: style="--icon-color:red;"  --> **Complexité** : La gestion de l'authentification et de l'autorisation dans un environnement distribué peut être complexe.


##==##

<!-- .slide: class="full-center" -->

# Détail d'un flux d'authentification

![h-900](./assets/images/auth-oauth.png)