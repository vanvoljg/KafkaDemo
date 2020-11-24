package KafkaDemo;

public class Demo {
    public static void main(String[] args) {
        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "consumer":
                    Consumer.start(args);
                    break;
                case "producer":
                    Producer.start(args);
                    break;
            }
        } else {
            System.err.println(usage());
        }

        System.exit(0);
    }

    private static String usage() {
        return "Usage:\n" +
                "  consumer <topic> - Start a consumer for the given topic\n" +
                "  producer <type> [count] - Start a producer for the given type and produce `count` number of messages (default: 50)";
    }
}