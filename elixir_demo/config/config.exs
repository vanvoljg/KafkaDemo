import Config

# config :kafka_ex,
  # brokers: [
    # {"localhost", 9093},
    # {"localhost", 9094},
    # {"localhost", 9095},
  # ],
  # consumer_group: "elixir-group",
  # client_id: "kafka_ex"

config :kaffe,
  producer: [
    endpoints: [localhost: 9092, localhost: 9093, localhost: 9094],
    topics: ["cities"]
  ],
  consumer: [
    endpoints: [localhost: 9092, localhost: 9093, localhost: 9094],
    topics: ["cities"],
    consumer_group: "elixir-consumer-group",
    message_handler: ElixirDemo.Consumer
  ]
