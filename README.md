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
mvn exec:java -Dexec.mainClass=org.lee.mugen.test.TestGameFight
make package run
```

## References
* [mugen](https://en.wikipedia.org/wiki/Mugen_(game_engine))
* [code.google.com/jmugen](https://code.google.com/archive/p/jmugen/)
* [JMugen Live Debug and Expression Watch](https://www.youtube.com/watch?v=6uVFrC91OU8)