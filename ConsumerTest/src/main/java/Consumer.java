import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;


import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class Consumer {
    private static final String TOPIC_NAME= "s25k";
                    //카프카 토픽의 이름

            public static void main(String[] args) {
                Properties properties = new Properties();//프로퍼티를 설정하기 위한 객체 생성
                properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); //B_S_C는 kafka 클러스터에 있는 kafka 브로커의 주소, 여기선 localhost:9092
                properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()); //key를 직렬화해주기 위해 있는 코드
                properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());//value를 직렬화해주기 위해 있는 코드
                properties.put(ConsumerConfig.GROUP_ID_CONFIG, TOPIC_NAME);//

                KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
                consumer.subscribe(Collections.singletonList(TOPIC_NAME));

                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record.value());
            }
        }
    }

}
