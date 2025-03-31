# Readme
This project contains 2 projects for processing a charge initialization request, i.e.
1. ChargeRequestController
2. AclAuthenticator

## Testing Setup

### How to run both of the applications
1. Run `gradle build` to build application
2. Run `./gradlew bootRun` to run service locally
ChargeRequestController will be hosted at `localhost:8080`
AclAuthenticator will be hosted at `localhost:8081`

### Enable async communication between these applications
1. Install kafka version kafka_2.13-3.8.1
2. Navigate to the directory where kafka is installed & start Zookekeeper
`bin/zookeeper-server-start.sh config/zookeeper.properties`
3. Navigate to the directory where kafka is installed & start Kafka 
`bin/kafka-server-start.sh config/server.properties`
4. Once Kafka is up, create topic AclAuthenticatorQueuingMessage 
`bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic AclAuthenticatorQueuingMessage`
5. If required, you can check consume & produce messages using the following command in the directory where kafka is installed
```
./bin/kafka-console-producer.sh --bootstrap-server localhost:9092 --topic AclAuthenticatorQueuingMessage
Consume msg
./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic AclAuthenticatorQueuingMessage
```
### Query DataBase
1. Once the service AclAuthenticator is up, you can open the following on Chrome & query tables by following the below link - 
`http://localhost:8081/h2-console`
2. Any change to the DataBase can be made from the console provided in the above URL.
