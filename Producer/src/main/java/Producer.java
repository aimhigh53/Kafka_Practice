
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;


import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.Scanner;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class Producer {
    //topic name
    private static final String TOPIC_NAME= "s25k";

    //kafka cluster address
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());


        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Input > ");
            String message = sc.nextLine();

            ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, message);
            try {
                producer.send(record, (metadata, exception) -> {
                    if (exception != null) {
                        // some exception
                    }
                });

            } catch (Exception e) {
                // exception
            } finally {
                producer.flush();
            }
        }
    }
}