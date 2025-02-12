# JMugen

JMugen is a clone of Mugen.

Clone of [JMugen-0.2alpha.zip](https://sourceforge.net/projects/mugen-net/files/) adapted to use maven

## Requirements
* [sdkman](https://sdkman.io/install)
* java 8+
* maven 3.6.3+

install
```sh
sdk install java 8.0.432-amzn
sdk install maven 3.6.3
```

use
```sh
sdk use java 8.0.432-amzn
sdk use maven 3.6.3
```

## Run


```sh
mvn package
java $JAVA_OPTS -cp JMugen.Debug/target/JMugen.Debug-0.0.1-SNAPSHOT.jar org.lee.mugen.test.TestMenu
```

## Debug
```properties
JAVA_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=0.0.0.0:5005"
```

## References
* [mugen](https://en.wikipedia.org/wiki/Mugen_(game_engine))
* [code.google.com/jmugen](https://code.google.com/archive/p/jmugen/)
* [JMugen Live Debug and Expression Watch](https://www.youtube.com/watch?v=6uVFrC91OU8)