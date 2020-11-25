package KafkaDemo;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.io.FileWriter;
import java.time.Duration;
import java.util.*;

public class Consumer {
    private static String[] topics;

    public static void start(String[] args) {
        if (args.length > 2) {
            topics = new String[args.length - 2];
            for (int i = 0; i < args.length - 2; i++) {
                topics[i] = args[i + 2];
            }
            switch (args[1].toLowerCase()) {
                case "auto-commit":
                    autoCommitConsumer(topics);
                    break;
                case "manual-commit":
                    manualCommitConsumer(topics);
                    break;
                case "partition-assignment":
                    partitionAssignmentConsumer(topics);
                    break;
                default:
            }
        } else {
            System.err.println(consumerUsage());
        }
    }

    private static void autoCommitConsumer(String[] topics) {
        try (KafkaConsumer<String, String> consumer = createAutoCommitConsumer()) {
            consumer.subscribe(Arrays.asList(topics));

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    String message = createFormattedMessage(record);
                    System.out.println(message);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static void manualCommitConsumer(String[] topics) {
        int minBatchSize = 200;
        List<ConsumerRecord<String, String>> buffer = new ArrayList<>();

        try (
                KafkaConsumer<String, String> consumer = createManualCommitConsumer();
                FileWriter fileWriter = new FileWriter("./numbers.txt", true)
        ) {
            consumer.subscribe(Arrays.asList(topics));

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    buffer.add(record);
                    String message = createFormattedMessage(record);
                    System.out.println(message);
                }
                if (buffer.size() >= minBatchSize) {
                    fileWriter.append(buffer.toString());
                    consumer.commitSync();
                    buffer.clear();
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static void partitionAssignmentConsumer(String[] topics) {
        try (KafkaConsumer<String, String> consumer = createAutoCommitConsumer()) {

            TopicPartition[] partitions = {
                new TopicPartition(topics[0], 0),
                new TopicPartition(topics[0], 1),
            };

            consumer.assign(Arrays.asList(partitions));

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    String message = createFormattedMessage(record);
                    System.out.println(message);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static KafkaConsumer<String, String> createAutoCommitConsumer() {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092, localhost:9093, localhost:9094");
        props.setProperty("group.id", "first-group");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        return new KafkaConsumer<>(props);
    }

    private static KafkaConsumer<String, String> createManualCommitConsumer() {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092, localhost:9093, localhost:9094");
        props.setProperty("group.id", "second-group");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        return new KafkaConsumer<>(props);
    }

    private static String createFormattedMessage(ConsumerRecord<String, String> record) {
        return String.format(
                "offset = %d, key = %s, value = %s, partition = %s",
                record.offset(),
                record.key(),
                record.value(),
                record.partition()
        );
    }

    private static String consumerUsage() {
        return "First argument to \"consumer\" must be the type of consumer. (\"auto-commit\", \"manual-commit\", or \"partition-assignment\")\n" +
                "Second argument to \"consumer\" must be the channel to subscribe to. (\"numbers\" or \"strings\")";
    }
}
