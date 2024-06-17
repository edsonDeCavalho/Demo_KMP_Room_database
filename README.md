# kmp_database 🗄📚 ROOM pour (IOS,Android,Deskshop) 

kmp_database est un projet Kotlin Multiplatform conçu pour fonctionner de manière transparente sur les plateformes iOS, Bureau (Desktop), et Android. Ce projet de base utilise la bibliothèque Room pour une gestion efficace de la base de données et constitue un point de départ robuste pour les développeurs souhaitant implémenter des fonctionnalités de base de données multiplateformes.


## Table of Contents

* Fonctionnalités
*  Prérequis
*  Installation
*  Utilisation
*  Contribution
*  Licence

## Features

* Multiplateforme : Fonctionne sur les plateformes iOS, Bureau (Desktop), et Android.
* Implémentation de Room : Utilise Room pour la gestion de la base de données, offrant une couche de base de données abstraite et facile à utiliser.
* Kotlin Multiplatform : Écrit en Kotlin pour une base de code partagée entre différentes plateformes.

## Prerequisites

* Kotlin 1.5.30 ou version ultérieure
* Android Studio Arctic Fox 2020.3.1 ou version ultérieure
* Xcode 12.5 ou version ultérieure (pour le développement iOS)
* Gradle 7.0 ou version ultérieure

## Installation

### Cloner le dépôt :

```bash

git clone https://github.com/edsonDeCavalho/Demo_KMP_Room_database.git
```
```bash
cd kmp_database
```

Ouvrir le projet dans Android Studio :

1. Choisissez "Ouvrir un projet Android Studio existant" et sélectionnez le dossier du dépôt cloné.

2. Synchroniser le projet avec les fichiers Gradle :

## Utilisation

### Android

#### 1.Ajouter les dépendances Room dans votre fichier build.gradle :

```gradle
implementation "androidx.room:room-runtime:2.3.0"
kapt "androidx.room:room-compiler:2.3.0"
```
#### 2.Définir vos entités de données et DAO :

```kotlin
@Entity(tableName = "example_table")
data class ExampleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String
)
```

```kotlin
@Dao
interface ExampleDao {
@Query("SELECT * FROM example_table")
fun getAll(): List<ExampleEntity>

    @Insert
    fun insert(vararg example: ExampleEntity)
}
```

Créer votre base de données Room :
```Kotlin

@Database(entities = [ExampleEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exampleDao(): ExampleDao
}

val db = Room.databaseBuilder(
    applicationContext,
    AppDatabase::class.java, "database-name"
).build()

```
## Contribution

Nous accueillons les contributions à ce projet. Veuillez suivre ces étapes pour contribuer :
1. Forker le dépôt.
2. Créer une nouvelle branche (git checkout -b fonction/votre-nom-de-fonctionnalité).
3. Commiter vos changements (git commit -m 'Ajouter une fonctionnalité').
4. Pousser la branche (git push origin fonction/votre-nom-de-fonctionnalité).
5. Ouvrir une pull request.

## Licence

Ce projet est sous licence MIT - voir le fichier LICENSE pour plus de détails.



