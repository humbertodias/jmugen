package:
	mvn clean package

run:
	arch -x86_64 java -Xmx512m -Xms256m \
    -Dsun.java2d.opengl=false -Dsun.java2d.d3d=false \
	-Djava.library.path=. \
	-cp JMugen.Properties/target/JMugen.Properties-0.0.1-SNAPSHOT.jar:JMugen.Debug/target/JMugen.Debug-0.0.1-SNAPSHOT.jar:JMugen/target/JMugen-0.0.1-SNAPSHOT.jar:JMugen.Render.Lwjgl/target/JMugen.Render.Lwjgl-0.0.1-SNAPSHOT.jar:Syntax/target/Syntax-0.0.1-SNAPSHOT.jar:${HOME}/.m2/repository/org/lwjgl/lwjgl/lwjgl/2.9.3/lwjgl-2.9.3.jar:${HOME}/.m2/repository/org/lwjgl/lwjgl/lwjgl_util/2.9.3/lwjgl_util-2.9.3.jar \
	org.lee.mugen.test.ExpressionTester

clean:
	mvn clean

natives:
	find ${HOME}/.m2 -name lwjgl-platform-2.9.3-natives-*.jar -exec jar -xf {} \;