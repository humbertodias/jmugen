
# JMugen Development Guide

## Requirements

Before starting, make sure you have the following installed:

- [SDKMAN](https://sdkman.io/install)
- Java 8 or higher
- Maven 3.6.3 or higher

### Installation Steps

1. Install Java and Maven using SDKMAN:

```sh
sdk install java 8.0.432-amzn
sdk install maven 3.6.3
```

2. Switch to the correct Java and Maven versions:

```sh
sdk use java 8.0.432-amzn
sdk use maven 3.6.3
```

## Running the Project

To compile and run the project, follow these steps:

1. Build the project with Maven:

```sh
mvn package
```

2. Run the application with the following command:

```sh
java $JAVA_OPTS -cp JMugen.Debug/target/JMugen.Debug-0.0.1-SNAPSHOT.jar org.lee.mugen.test.TestGameFight
```

## Debugging

To debug the project, use the following Java options:

```properties
JAVA_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=0.0.0.0:5005"
```

## References

- [Download JMugen 0.2 Alpha](https://sourceforge.net/projects/mugen-net/files/)
- [Mugen Engine Wikipedia](https://en.wikipedia.org/wiki/Mugen_(game_engine))
- [Google Code Archive: JMugen](https://code.google.com/archive/p/jmugen/)
- [JMugen Live Debug and Expression Watch (YouTube)](https://www.youtube.com/watch?v=6uVFrC91OU8)
