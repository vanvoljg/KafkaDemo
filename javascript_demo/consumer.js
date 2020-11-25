'use strict';

const { Kafka } = require('kafkajs');

const kafka = new Kafka({
  clientId: 'my-consumer',
  brokers: ['localhost:9092', 'localhost:9093', 'localhost:9094'],
});

const consumer = kafka.consumer({
  groupId: 'first-group',
});

const runConsumer = async (args) => {
  if (args.length >= 1) {
    let topic = args[0];

    await consumer.connect();
    await consumer.subscribe({
      topic,
      fromBeginning: false,
    });

    await consumer.run({
      eachMessage: async ({ topic, partition, message }) => {
        console.info({
          partition: partition,
          offset: message.offset,
          value: message.value.toString(),
        });
      },
    });
  } else {
    console.error('First argument to consumer must be the topic');
  }
};

module.exports = runConsumer;
