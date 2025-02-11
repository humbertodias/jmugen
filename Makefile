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
	-cp JMugen.Properties/target/JMugen.Properties-0.0.1-SNAPSHOT.jar:JMugen.Debug/target/JMugen.Debug-0.0.1-SNAPSHOT.jar:JMugen/target/JMugen-0.0.1-SNAPSHOT.jar:JMugen.Render.Lwjgl/target/JMugen.Render.Lwjgl-0.0.1-SNAPSHOT.jar:JMugen.Render.jogl/target/JMugen.Render.jogl-0.0.1-SNAPSHOT.jar:JMugen.Render.Java/target/JMugen.Render.Java-0.0.1-SNAPSHOT.jar:Syntax/target/Syntax-0.0.1-SNAPSHOT.jar:${HOME}/.m2/repository/org/lwjgl/lwjgl/lwjgl/2.9.3/lwjgl-2.9.3.jar:${HOME}/.m2/repository/org/lwjgl/lwjgl/lwjgl_util/2.9.3/lwjgl_util-2.9.3.jar:${HOME}/.m2/repository/net/java/dev/gluegen/gluegen-rt/1.0b05/gluegen-rt-1.0b05.jar:${HOME}/.m2/repository/org/jogamp/jogl/jogl-all/v2.4.0-rc4/jogl-all-v2.4.0-rc4.jar:${HOME}/.m2/repository/org/jogamp/gluegen/gluegen-rt-natives-macosx-universal/v2.4.0-rc4/gluegen-rt-natives-macosx-universal-v2.4.0-rc4.jar:${HOME}/.m2/repository/org/jogamp/gluegen/gluegen-rt/v2.4.0-rc4/gluegen-rt-v2.4.0-rc4.jar:${HOME}/.m2/repository/com/googlecode/soundlibs/mp3spi/1.9.5.4/mp3spi-1.9.5.4.jar \
	org.lee.mugen.test.TestGameFight
#	org.lee.mugen.test.TestScene
#	org.lee.mugen.test.TestSelect
#	org.lee.mugen.core.sound.SoundSystem
#	org.lee.mugen.test.TestMenu
#	org.lee.mugen.snd.SndReader
#	com.Ostermiller.Syntax.ProgrammerEditorDemo
#	org.lee.mugen.core.renderer.game.fight.RoundRender
#	org.lee.mugen.test.ExpressionTester
#	org.lee.mugen.test.TestScene

clean:
	rm -rf natives
	mvn clean

natives:
	mkdir -p natives
	find ${HOME}/.m2 -name lwjgl-platform-2.9.3-natives-*.jar -exec unzip -o {} -d natives \;
	find ${HOME}/.m2 -name jogl-all-natives-*-v2.4.0-rc4.jar -exec unzip -o {} -d natives \;
	find ${HOME}/.m2 -name gluegen-rt-natives-macosx-universal-v2.4.0-rc4.jar -exec unzip -o {} -d natives \;


.PHONY: natives