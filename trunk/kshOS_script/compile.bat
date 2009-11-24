mkdir build
javac -d build -classpath lib\antlr-3.2.jar -sourcepath src src\kshos\*.java
javac -d build -classpath lib\antlr-3.2.jar -sourcepath src src\kshos\command\*.java
mkdir build\kshos\config
copy src\kshos\config build\kshos\config