### Notes
1. Currently targeting Java 17

### Steps
1. `mvn clean package`
2. `java -cp "liberty-poc-server/target/liberty/*" -Dliberty.wlp.home=liberty-poc-server/target/liberty/wlp --add-opens java.base/java.lang=ALL-UNNAMED -jar liberty-poc-server/target/liberty-poc-server-1.0-jar-with-dependencies.jar`
