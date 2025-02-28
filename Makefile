OS       := $(shell uname -s)
ARCH     := $(shell uname -m)
# if there is no current tag use the git commit hash
TAG_NAME := $(shell git describe --tags --exact-match 2>/dev/null || git rev-parse --short HEAD)

# Debugging Configuration
DEBUG_SUSPEND=n
JAVA_OPTS= -agentlib:jdwp=transport=dt_socket,server=y,suspend=${DEBUG_SUSPEND},address=0.0.0.0:5005
# Caught AppContextInfo(Bug 1004) IllegalAccessException: class com.jogamp.nativewindow.awt.AppContextInfo cannot access class sun.awt.AppContext (in module java.desktop) because module java.desktop does not export sun.awt to unnamed module @300ffa5d on thread AppKit Thread
JAVA_OPTS+= --add-opens java.desktop/sun.awt=ALL-UNNAMED

ifeq ($(OS),Darwin)
JAVA_OPTS+= -XstartOnFirstThread -Dorg.lwjgl.util.Debug=true -Dorg.lwjgl.util.DebugLoader=true
endif

JAVA=java

# Uncomment to switch the MAIN_CLASS:
# MAIN_CLASS = org.mugen.launch.Launch
MAIN_CLASS = org.lee.mugen.test.TestMenu
#MAIN_CLASS = org.lee.mugen.test.TestGameFight
# MAIN_CLASS = org.lee.mugen.test.TestScene
# MAIN_CLASS = org.lee.mugen.test.TestSelect
# MAIN_CLASS = org.lee.mugen.core.sound.SoundSystem
# MAIN_CLASS = org.lee.mugen.snd.SndReader
# MAIN_CLASS = org.lee.mugen.core.renderer.game.fight.RoundRender
# MAIN_CLASS = org.lee.mugen.test.ExpressionTester
# MAIN_CLASS = com.Ostermiller.Syntax.ProgrammerEditorDemo

run:
	${JAVA} ${JAVA_OPTS} \
	-Djava.library.path=natives \
	-cp JMugen.Properties/target/JMugen.Properties-0.0.1-SNAPSHOT.jar:JMugen.Debug/target/JMugen.Debug-0.0.1-SNAPSHOT.jar:JMugen.Launcher/target/JMugen.Launcher-0.0.1-SNAPSHOT.jar:${HOME}/.m2/repository/com/googlecode/soundlibs/mp3spi/1.9.5.4/mp3spi-1.9.5.4.jar \
	${MAIN_CLASS}

clean:
	rm -rf natives dist tmp
	mvn clean

package:
	mvn clean package

app-image:
	jpackage --version

	rm -rf tmp dist
	mkdir tmp
	cp JMugen.Debug/target/JMugen.Debug-0.0.1-SNAPSHOT.jar tmp/jmugen.jar
	jpackage --input tmp \
	--name jmugen-${TAG_NAME} \
	--main-jar jmugen.jar \
	--type app-image \
	--java-options "--add-opens java.desktop/sun.awt=ALL-UNNAMED" \
	--dest dist/
	rm -rf tmp

copy-release-files:
	echo "OS: ${OS}"
ifeq ($(OS),Linux)
	cp release/lin/* dist/
else ifeq ($(OS),Darwin)
	cp release/mac/* dist/
else
	echo "unknown"
endif

app-image-tar-gz:	app-image	copy-release-files
	(cd dist && tar cfzv ../jmugen-${TAG_NAME}-${OS}-${ARCH}.tar.gz *)

natives:
	mkdir -p natives
	find ${HOME}/.m2 -name lwjgl-platform-2.9.3-natives-*.jar -exec unzip -o {} -d natives \;
	find ${HOME}/.m2 -name jogl-all-natives-*-v2.4.0-rc4.jar -exec unzip -o {} -d natives \;
	find ${HOME}/.m2 -name gluegen-rt-natives-macosx-universal-v2.4.0-rc4.jar -exec unzip -o {} -d natives \;

.PHONY: natives package run-lwjgl run-jogl clean
