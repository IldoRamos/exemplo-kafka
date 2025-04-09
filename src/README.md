Como Executar
Inicie o Kafka (local ou Docker).
comando para executar docker
    docker-compose up -d

Execute o Produtor:
    mvn compile exec:java -Dexec.mainClass="producer.KafkaProducerExample"

Execute o Consumidor (em outro terminal):
    mvn compile exec:java -Dexec.mainClass="consumer.KafkaConsumerExample"



Se estiver usando Kafka local, inicie o Zookeeper e o Kafka:

bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties