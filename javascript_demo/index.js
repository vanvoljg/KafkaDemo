'use strict';

const consumer = require('./consumer.js');
const producer = require('./producer.js');

const main = () => {
  const args = process.argv.slice(2);
  if (args.length > 0) {
    switch (args[0].toLowerCase()) {
      case 'consumer':
        const consumerArgs = args.slice(1);
        consumer(consumerArgs);
        break;
      case 'producer':
        const producerArgs = args.slice(1);
        producer(producerArgs);
        break;
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
  console.error(usageText);
};

main();
