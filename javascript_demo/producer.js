'use strict';

const Chance = require('chance');
const { Kafka } = require('kafkajs');

const chance = new Chance();

const kafka = new Kafka({
  clientId: 'my-producer',
  brokers: ['localhost:9092', 'localhost:9093', 'localhost:9094'],
});

const producer = kafka.producer();

let count = 50;
let topic;

const animalMessage = async () => {
  const animal = chance.animal();
  const message = createMessage(animal);
  console.info(animal);
  await sendMessage(producer, topic, message);
};

const sendMessage = async (producer, topic, message) => {
  try {
    await producer.send({ topic, messages: [message] });
  } catch (error) {
    console.error(error);
  }
};

const sendBatchMessages = async (producer, topic, messages) => {
  try {
    await producer.send({ topic, messages });
  } catch (error) {
    console.error(error);
  }
};

const createMessage = (value, key) => ({
  key: key ? String(key) : String(value),
  value: String(value),
});

const runProducer = async (args) => {
  if (args.length >= 2) {
    count = parseInt(args[1]);
  }
  if (args.length >= 1) {
    topic = args[0];

    // Sending multiple messages at one time
    // let messages = new Array(count).fill().map((_value, i) => createMessage(i));
    // await producer.connect();
    // await sendBatchMessages(producer, topic, messages);

    // Sending one message every 1000 ms
    await producer.connect();
    setInterval(animalMessage, 1000);
  }
};

module.exports = runProducer;
