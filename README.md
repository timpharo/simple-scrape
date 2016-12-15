# Simple page scraper

This is a simple application that scrapes a website extracts the products information
and outputs the details as JSON into the console.

**The description of each product was not parsed as it doesn't seem to be present anymore on the indevidual products page.  The information under the description header on the individual product page seems to match the title. **

## Dependencies required

Java 8

## How to run the tests

From the base directory run the following command

### If you are a Mac user

```
bash gradlew test
```

### If you are a Windows user

```
gradlew.bat test
```

## How to run the app

From the base directory run the following commands

### If you are a Mac user

```
bash gradlew fatJar
```
This builds the jar file with all its dependencies.  Now run
```
java -jar build/libs/simple-scrape-all.jar

```

### If you are a Windows user

```
gradlew.bat fatJar
```
This builds the jar file with all its dependencies.  Now run
```
java -jar build/libs/simple-scrape-all.jar
```


### Future suggested improvement

* Dependency injection to allow IOC
* Short of converting the back end to provide JSON for the website to read, this app may be best served as a Rest API.

