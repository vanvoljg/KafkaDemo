'use strict';

const { kafka } = require('./kafka.js');

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
        console.log(
          `Topic: ${topic}, message.value: ${message.value}, message.offset: ${message.offset}, Partition: ${partition}`
        );
      },
    });
  } else {
    console.log('First argument to consumer must be the topic');
  }
};

module.exports = { runConsumer };

const args = process.argv.slice(2);
runConsumer(args).catch(console.error);
