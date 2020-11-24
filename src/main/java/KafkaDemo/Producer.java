package KafkaDemo;

import org.apache.kafka.clients.producer.*;

import java.util.*;

public class Producer {
    private static int numOfRecords = 50;

    public static void start(String[] args) {
        if (args.length > 2) {
            numOfRecords = Integer.parseInt(args[2]);
        }
        if (args.length > 1) {
            switch (args[1].toLowerCase()) {
                case "numbers":
                    integerMessages(args);
                    return;
                case "strings":
                    stringMessages(args);
                    return;
            }
        }

        System.err.println(producerUsage());
    }

    private static void integerMessages(String[] args) {
        // Example 1 - integers as key and value

        String topic = "numbers";
        String clientId = "string-producer";

        try (KafkaProducer<String, String> producer = createStringStringProducer(clientId)) {
            for (int i = 0; i < numOfRecords; i++) {
                System.out.println("Message " + i + " produced. Sending to '" + topic + "'.");
                producer.send(new ProducerRecord<>(topic, Integer.toString(i), Integer.toString(i)));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static void stringMessages(String[] args) {
        // Example 2 - formatted string as message w/ 100ms delay (10 message/second)

        String topic = "strings";
        String clientId = "string-producer";

        try (KafkaProducer<String, String> producer = createStringStringProducer(clientId)) {
            for (int i = 0; i < numOfRecords; i++) {
                String message = String.format("Producer %s has sent message %S at %s", clientId, i, new Date());
                System.out.println(message);
                producer.send(new ProducerRecord<>(topic, Integer.toString(i), message));
                Thread.sleep(500);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static KafkaProducer<String, String> createStringStringProducer(String clientId) {
        clientId = clientId != null ? clientId : "unidentified-producer";
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092, localhost:9093, localhost:9094");
        props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty("acks", "all");
        props.setProperty("compression.type", "zstd");
        props.setProperty("client.id", clientId);

        return new KafkaProducer<>(props);
    }

    private static String producerUsage() {
        return "First argument to \"producer\" must be the type of message to produce (\"numbers\" or \"strings\")\n" +
               "Second argument (optional) specifies the number of messages to send. (Default: 50)";
    }
}
