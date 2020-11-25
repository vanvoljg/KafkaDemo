'use strict';

const { runConsumer } = require('./consumer.js');
const { runProducer } = require('./producer.js');

const main = () => {
  const args = process.argv.slice(2);
  if (args.length > 0) {
    switch (args[0].toLowerCase()) {
      case 'consumer':
        const consumerArgs = args.slice(1);
        runConsumer(consumerArgs);
        process.exit(0);
      case 'producer':
        const producerArgs = args.slice(1);
        runProducer(producerArgs);
        process.exit(0);
      default:
    }
  } else {
    printUsage();
    process.exit(0);
  }
};

const printUsage = () => {
  const usageText =
    '`yarn run consumer <topic>` - start a consumer and subscribe to the given topic\n' +
    '`yarn run producer <topic> <count>` - start a producer and send <count> messages to <topic>';
  console.log(usageText);
};

module.exports = main;
