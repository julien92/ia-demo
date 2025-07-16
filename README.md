# Order API

Une API RESTful pour la gestion des commandes développée avec Spring Boot.

## Description

Order API est une application Spring Boot qui fournit des endpoints pour créer, récupérer et gérer des commandes. Elle utilise Jakarta Bean Validation pour valider les données d'entrée et offre une interface REST simple et efficace.

## Fonctionnalités

- Création de commandes avec validation d'email
- Récupération des commandes par ID
- Récupération d'une commande par défaut
- Validation des données d'entrée avec Jakarta Bean Validation
- Gestion des erreurs de validation

## Technologies utilisées

- Java 21
- Spring Boot 3.3.0
- Spring MVC
- Jakarta Bean Validation
- Lombok
- Maven

## Prérequis

- JDK 21
- Maven 3.8+

## Installation et démarrage

1. Clonez le dépôt :

   ```bash
   git clone https://github.com/votre-utilisateur/order-api.git
   cd order-api
   ```

2. Compilez et exécutez l'application :

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

3. L'application sera accessible à l'adresse : `http://localhost:8080`

## Endpoints API

### Récupérer une commande par défaut

```
GET /order
```

Retourne une commande d'exemple.

### Récupérer une commande par ID

```
GET /order/{id}
```

Retourne la commande correspondant à l'ID spécifié ou une erreur 404 si aucune commande n'est trouvée.

### Créer une nouvelle commande

```
POST /order
Content-Type: application/json

{
  "email": "exemple@email.com",
  "description": "Description de la commande"
}
```

Crée une nouvelle commande avec l'email et la description fournis. L'email doit être au format valide selon les standards Jakarta.

## Validation

L'API utilise Jakarta Bean Validation pour valider les données d'entrée :

- L'email ne peut pas être vide (`@NotBlank`)
- L'email doit être au format valide (`@Email`)

En cas d'erreur de validation, l'API retourne un code HTTP 400 (Bad Request) avec des détails sur les erreurs de validation.

## Structure du projet

```
src/main/java/com/example/orderapi/
├── OrderApiApplication.java        # Point d'entrée de l'application
├── Order.java                      # Modèle de données pour les commandes
├── OrderController.java            # Contrôleur REST pour les endpoints
├── OrderService.java               # Service pour la logique métier
└── dto/
    └── OrderRequest.java           # DTO pour la validation des requêtes
```

## Développement

### Compilation

```bash
mvn clean compile
```

### Tests

```bash
mvn test
```

### Packaging

```bash
mvn package
```

Cela générera un fichier JAR exécutable dans le dossier `target/`.

## Licence

Ce projet est sous licence MIT. Voir le fichier [LICENSE](LICENSE) pour plus de détails.
