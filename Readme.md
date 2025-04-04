# Readme

## Project Summary
This project contains 2 projects for processing a charge initialization request, i.e.
1. ChargeInitializer
2. AclAuthenticator

### Tech stack used:
write the stack

### Design Details:
what happens in each step, insert diagram

## Testing 

### Setting up Kafka:
Follow the below steps to perform one time setup of Kafka before booting the service:
1. Install kafka version kafka_2.13-3.8.1 from https://kafka.apache.org/downloads (source download)
2. Navigate to the directory where kafka is installed & start Zookeeper 
- `bin/zookeeper-server-start.sh config/zookeeper.properties`
3. Navigate to the directory where kafka is installed & start Kafka
- `bin/kafka-server-start.sh config/server.properties`
4. Once Kafka is up, create topic AclAuthenticatorQueuingMessage. This is required to be done only once.
- `bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic AclAuthenticatorQueuingMessage`
5. If required, you can check consume & produce messages using the following command in the directory where kafka is installed. The following is the command to consume messages produced on our topic.
- `./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic AclAuthenticatorQueuingMessage`

### Running the applications:
Follow the below commands to boot up the applications :
1. Run commands from the previous section's step 2, followed by step 3 for getting Kafka up.
2. Run `./gradlew build` to build application
3. Run `./gradlew bootRun` to run service locally
   - The ChargeInitializer application will be hosted at `localhost:8080`
   - The AclAuthenticator application will be hosted at `localhost:8081`

### Testing the application:
Positive hit
Negative hit
Invalid input

// Insert video for testing

### Querying the DataBase:
No separate setup needs to be perfomed for DataBase, follow the below steps to view table data:
1. Once the service AclAuthenticator is up, you can open the following link on Chrome - 
`http://localhost:8081/h2-console`
2. Log in with the following credentials:
   - Driver Class: org.h2.Driver
   - JDBC URL: jdbc:h2:mem:aclDB
   - User Name: sa
   - Password : _Keep this Empty_
3. Run the following at any time to view the state of the DB

```
SELECT * FROM ACL_RESPONSE_AUDIT;
SELECT * FROM CHARGE_SESSION_ACL;
```
4. Any change to the DataBase can be made from the console provided in the above URL.


## Other details:

### Scaling considerations

### Table schema