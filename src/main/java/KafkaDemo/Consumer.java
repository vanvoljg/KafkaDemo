package KafkaDemo;

import org.apache.kafka.clients.consumer.*;

import java.time.Duration;
import java.util.*;

public class Consumer {
    private static String[] topics;

    public static void start(String[] args) {
        if (args.length > 1) {
            topics = new String[]{args[1]};
        } else {
            System.err.println(consumerUsage());
        }

        try (KafkaConsumer<String, String> consumer = createStringStringConsumer()) {
            consumer.subscribe(Arrays.asList(topics));

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    String message =
                        String.format(
                            "offset = %d, key = %s, value = %s, partition = %s",
                            record.offset(),
                            record.key(),
                            record.value(),
                            record.partition()
                        );
                    System.out.println(message);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static KafkaConsumer<String, String> createStringStringConsumer() {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092, localhost:9093, localhost:9094");
        props.setProperty("group.id", "first-group");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        return new KafkaConsumer<>(props);
    }

    private static String consumerUsage() {
        return "First argument to \"consumer\" must be the channel to subscribe to (\"numbers\" or \"strings\")\n";
    }
}
