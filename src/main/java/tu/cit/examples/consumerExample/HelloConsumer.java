package tu.cit.examples.consumerExample;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.kafka.common.serialization.StringDeserializer;
import java.util.Arrays;
import java.util.Properties;

public class HelloConsumer {
    private static final Logger logger = LogManager.getLogger();
    public static void main(String args[]){
        Properties props = new Properties();
        props.put("bootstrap.servers","localhost:9092");
        props.put("group.id","test");
        //props.put("enable.auto.commit","true");
        //props.put("auto.commit.interval.ms","1000");
        props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String,String> consumer = new KafkaConsumer<String,String>(props);
        consumer.subscribe(Arrays.asList("foo","bar"));
        while(true){
            ConsumerRecords<String, String> records = consumer.poll(10000);
            for(ConsumerRecord<String,String> record : records){
                System.out.printf("Topic=%s, Partition Number=%d, Offset=%d, TimeStamp=%s,key = %s, value = %s%n" ,record.topic(),record.partition(),record.offset(),record.timestamp(), record.key(), record.value());
            }
        }
 }

}
