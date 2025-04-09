package br.com.ramos.unifor.exemplokafka.producer;


import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;

public class KafkaProducerExample {
    private static final String TOPIC = "meu-topico";
    private static final String BOOTSTRAP_SERVERS = "localhost:9092";

    public static void main(String[] args) {
        // Configurações do Produtor
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // Criar o produtor
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        try {
            for (int i = 0; i < 10; i++) {
                String message = "Mensagem " + i;
                ProducerRecord<String, String> record =
                        new ProducerRecord<>(TOPIC, "key-" + i, message);

                // Enviar mensagem de forma assíncrona
                producer.send(record, (metadata, exception) -> {
                    if (exception == null) {
                        System.out.printf(
                                "Mensagem enviada -> Topico: %s, Partição: %d, Offset: %d%n",
                                metadata.topic(), metadata.partition(), metadata.offset()
                        );
                    } else {
                        System.err.println("Erro ao enviar mensagem: " + exception.getMessage());
                    }
                });
            }
        } finally {
            producer.close(); // Fechar o produtor
        }
    }
}