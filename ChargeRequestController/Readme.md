# Readme

## How to run the application
`./gradlew bootRun` to run service locally
`gradle build` to build application

Install gradle
go to directory where kafka is installed
bin/zookeeper-server-start.sh config/zookeeper.properties
start kafka
bin/kafka-server-start.sh config/server.properties

Create topic
```
msondhi@msondhi-mn7379 kafka_2.13-3.8.1 % bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic AclAuthenticatorQueuingMessage

Created topic AclAuthenticatorQueuingMessage.
```
./bin/kafka-console-producer.sh --bootstrap-server localhost:9092 --topic AclAuthenticatorQueuingMessage
Consume msg
./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic AclAuthenticatorQueuingMessage

msondhi@msondhi-mn7379 bin % ./kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group console-consumer-13193

GROUP                  TOPIC                          PARTITION  CURRENT-OFFSET  LOG-END-OFFSET  LAG             CONSUMER-ID                                           HOST            CLIENT-ID
console-consumer-13193 AclAuthenticatorQueuingMessage 0          -               28              -               console-consumer-c5daba70-2386-48cc-bab2-41b82d2cb1c3 /192.168.1.5    console-consumer%                      

goto http://localhost:8081/h2-console
