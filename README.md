# kmp_database 🗄📚(IOS,Android,Deskshop) 

kmp_database is a Kotlin Multiplatform project designed to work seamlessly on iOS, Desktop, and Android platforms. This base project utilizes the Room library for efficient database management and offers a robust starting point for developers looking to implement cross-platform database functionalities.


## Table of Contents

* Features
* Prerequisites
* Installation
* Usage
* Contributing
* License

## Features

* Cross-Platform: Works on iOS, Desktop, and Android platforms.
* Room Implementation: Uses Room for database management, providing an easy-to-use, abstracted database layer.
* Kotlin Multiplatform: Written in Kotlin for shared codebase across different platforms.

## Prerequisites

* Kotlin 1.5.30 or later
* Android Studio Arctic Fox 2020.3.1 or later
* Xcode 12.5 or later (for iOS development)
* Gradle 7.0 or later

## Installation

### Clone the repository:

```bash

git clone https://github.com/edsonDeCavalho/Demo_KMP_Room_database.git
```
```bash
cd kmp_database
```

Open the project in Android Studio:

1. Choose "Open an existing Android Studio project" and select the cloned repository folder.
Sync the project with Gradle files:

2. Click on "File" > "Sync Project with Gradle Files".
Build the project:

3. Select "Build" > "Make Project" from the menu or use the shortcut Ctrl+F9.

## Usage

### Android

#### 1.Add Room dependencies in your build.gradle file:
gradle

Copier le code

implementation "androidx.room:room-runtime:2.3.0"
kapt "androidx.room:room-compiler:2.3.0"

#### 2.Define your data entities and DAO:

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

Create your Room database:
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
## Contributing

We welcome contributions to this project. Please follow these steps to contribute:
Fork the repository.

1. Create a new branch (git checkout -b feature/your-feature-name).
2. Commit your changes (git commit -m 'Add some feature').
3. Push to the branch (git push origin feature/your-feature-name).
4. Open a pull request.

## License

This project is licensed under the MIT License - see the LICENSE file for details.

