# Debugging Configuration
DEBUG_SUSPEND=n
JAVA_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=${DEBUG_SUSPEND},address=0.0.0.0:5005"

MAIN_CLASS = org.lee.mugen.test.TestGameFight
# Uncomment to switch the MAIN_CLASS:
# MAIN_CLASS = org.lee.mugen.test.TestScene
# MAIN_CLASS = org.lee.mugen.test.TestSelect
# MAIN_CLASS = org.lee.mugen.core.sound.SoundSystem
# MAIN_CLASS = org.lee.mugen.test.TestMenu
# MAIN_CLASS = org.lee.mugen.snd.SndReader
# MAIN_CLASS = com.Ostermiller.Syntax.ProgrammerEditorDemo
# MAIN_CLASS = org.lee.mugen.core.renderer.game.fight.RoundRender
# MAIN_CLASS = org.lee.mugen.test.ExpressionTester

run-lwjgl:
	java ${JAVA_OPTS} \
	-Djava.library.path=natives \
	-XstartOnFirstThread \
	-cp JMugen.Properties/target/JMugen.Properties-0.0.1-SNAPSHOT.jar:JMugen.Debug/target/JMugen.Debug-0.0.1-SNAPSHOT.jar:${HOME}/.m2/repository/com/googlecode/soundlibs/mp3spi/1.9.5.4/mp3spi-1.9.5.4.jar \
	${MAIN_CLASS}

run-jogl:
	java ${JAVA_OPTS} \
	-cp JMugen.Properties/target/JMugen.Properties-0.0.1-SNAPSHOT.jar:JMugen.Debug/target/JMugen.Debug-0.0.1-SNAPSHOT.jar:${HOME}/.m2/repository/com/googlecode/soundlibs/mp3spi/1.9.5.4/mp3spi-1.9.5.4.jar \
	${MAIN_CLASS}

clean:
	rm -rf natives dist tmp
	mvn clean

package:
	mvn clean package

TAG_NAME := $(shell git describe --tags --exact-match 2>/dev/null)
app-image:
	jpackage --version

	rm -rf tmp dist
	mkdir tmp
	cp JMugen.Debug/target/JMugen.Debug-0.0.1-SNAPSHOT.jar tmp/jmugen.jar
	jpackage --input tmp \
	--name jmugen-${TAG_NAME} \
	--main-jar jmugen.jar \
	--type app-image \
	--dest dist/
	rm -rf tmp

SO := $(shell uname -s)
ARCH := $(shell uname -m)
app-image-tar-gz:	app-image
	(cd dist && tar cfzv ../jmugen-${TAG_NAME}-${SO}-${ARCH}.tar.gz jmugen-${TAG_NAME}*)


natives:
	mkdir -p natives
	find ${HOME}/.m2 -name lwjgl-platform-2.9.3-natives-*.jar -exec unzip -o {} -d natives \;
	find ${HOME}/.m2 -name jogl-all-natives-*-v2.4.0-rc4.jar -exec unzip -o {} -d natives \;
	find ${HOME}/.m2 -name gluegen-rt-natives-macosx-universal-v2.4.0-rc4.jar -exec unzip -o {} -d natives \;

.PHONY: natives package run-lwjgl run-jogl clean
