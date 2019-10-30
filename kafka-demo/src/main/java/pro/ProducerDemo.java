package pro;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProducerDemo {

    public static void main(String[] args) {
        KafkaProducer<String, String> producer = new KafkaProducer<>(init());

        while (true) {
            sendMessage(producer);
        }
    }

    private static void sendMessage(KafkaProducer<String, String> producer) {
        ProducerRecord<String, String> record = new ProducerRecord<>("test", "value");

        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {

            }
        });
    }

    private static Properties init() {
        Properties config = new Properties();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.CLIENT_ID_CONFIG, "123");

        return config;
    }
}
