### Notes
1. Currently targeting Java 21

### Steps
1. `mvn clean package`
2. `java -cp "target/liberty/*" -Dliberty.wlp.home=target/liberty/wlp -jar target/liberty-poc-1.0-jar-with-dependencies.jar`
