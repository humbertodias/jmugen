package:
	mvn clean package

DEBUG_SUSPEND=n
JAVA_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=${DEBUG_SUSPEND},address=0.0.0.0:5005"

JAVA='arch -x86_64 java'
JAVA=java
run:
	${JAVA} \
	${JAVA_OPTS} \
	-Xmx512m -Xms256m \
    -Dsun.java2d.opengl=false -Dsun.java2d.d3d=false \
	-Djava.library.path=natives \
	-cp JMugen.Properties/target/JMugen.Properties-0.0.1-SNAPSHOT.jar:JMugen.Debug/target/JMugen.Debug-0.0.1-SNAPSHOT.jar:JMugen/target/JMugen-0.0.1-SNAPSHOT.jar:JMugen.Render.Lwjgl/target/JMugen.Render.Lwjgl-0.0.1-SNAPSHOT.jar:JMugen.Render.jogl/target/JMugen.Render.jogl-0.0.1-SNAPSHOT.jar:JMugen.Render.Java/target/JMugen.Render.Java-0.0.1-SNAPSHOT.jar:Syntax/target/Syntax-0.0.1-SNAPSHOT.jar:${HOME}/.m2/repository/org/lwjgl/lwjgl/lwjgl/2.9.3/lwjgl-2.9.3.jar:${HOME}/.m2/repository/org/lwjgl/lwjgl/lwjgl_util/2.9.3/lwjgl_util-2.9.3.jar:${HOME}/.m2/repository/net/java/dev/jogl/jogl/1.1.1-rc6/jogl-1.1.1-rc6.jar:${HOME}/.m2/repository/net/java/dev/gluegen/gluegen-rt/1.0b05/gluegen-rt-1.0b05.jar:${HOME}/.m2/repository/org/jogamp/jogl/jogl-all/2.1.5/jogl-all-2.1.5.jar \
	org.lee.mugen.test.TestGameFight

clean:
	rm -rf natives
	mvn clean

natives:
	mkdir -p natives
	find ${HOME}/.m2 -name lwjgl-platform-2.9.3-natives-*.jar -exec unzip -o {} -d natives \;
#	find ${HOME}/.m2 -name jogl-all-*-natives-*.jar -exec unzip -o {} -d natives \;

.PHONY: natives