![Maven Central](https://img.shields.io/maven-central/v/io.github.mathieusoysal/codingame-puzzles-stats-history)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=MathieuSoysal_CodinGame-Puzzles-History-Library&metric=coverage)](https://sonarcloud.io/summary/new_code?id=MathieuSoysal_CodinGame-Puzzles-History-Library)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=MathieuSoysal_CodinGame-Puzzles-History-Library&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=MathieuSoysal_CodinGame-Puzzles-History-Library)
![GitHub Actions](https://github.com/MathieuSoysal/CodinGame-Puzzles-History-Library/workflows/Java%20CI%20with%20Maven/badge.svg)
[![Javadoc](https://img.shields.io/badge/JavaDoc-Online-green)](https://mathieusoysal.github.io/CodinGame-Puzzles-History-Library/javadoc/)

# <img src="https://www.svgrepo.com/show/232495/java.svg" width="100"> CodinGame Puzzles stats library [![GitHub](https://img.shields.io/badge/license-GNU%20General%20Public%20License%20v3.0-green)](https://github.com/MathieuSoysal/CodinGame-Puzzles-History-Library/blob/master/LICENSE)

Simple library for interacting with CodinGame's puzzle API.

## How to integrate the CodinGame-Puzzles-Stats-library into your code

**Required Java version :** 17
**Required MongoDB database**

### Maven 

If you have Maven, add the following to the dependencies of your `pom.xml` file:

```xml
<dependency>
  <groupId>io.github.mathieusoysal</groupId>
  <artifactId>codingame-puzzles-stats-history</artifactId>
  <version>1.0.0</version>
</dependency>
```

>*See an example of a [pom.xml](https://github.com/MathieuSoysal/CodinGame-Puzzles-History-Library/blob/d8bdf1a7f1002e387bfae0beb255638f59e3c8b9/ressources-readme/pom-exemple.xml#L20-L24) file with the CodinGame-Puzzles-History-Library*
### Gradle

If you are using Gradle, add the following to the dependencies of your `build.gradle` file:

```
    implementation 'io.github.mathieusoysal:codingame-puzzles-stats-history:1.0.2'
```

## Example code for using the CodinGame-Puzzles-Stats library

```java
import com.github.mathieusoysal.CodinGameHistory;

public class CodeExemple {

    public static void main(String[] args) {
        CodinGameHistory codinGameHistory = new CodinGameHistory(mongoClient, "CodinGame-stats");

        // Get all puzzles statistics of 2020/01/01
        List<DatedPuzzle> puzzles = codinGameHistory.getPuzzlesOf(LocalDate.of(2020, 1, 1));

        // Get all puzzles statistics between two dates
        List<DatedPuzzle> puzzles = codinGameHistory.getPuzzlesBetweenTwoDate(
              LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1));
    }
}
```
>*See more [code exemples](https://github.com/MathieuSoysal/CodinGame-Puzzles-History-Library/blob/master/ressources-readme/CodeExemple.java)*
## Contribution
Suggestions and contributions are always welcome! Please discuss larger changes via an [issue](https://github.com/MathieuSoysal/CodinGame-Puzzles-History-Library/issues) before submitting a request.

## Licence

This project is released under the [GNU General Public License v3.0](https://github.com/MathieuSoysal/CodinGame-Puzzles-History-Library/blob/master/LICENSE)
