# Python-based Apache Kafka consumer

from kafka import KafkaConsumer

consumer = KafkaConsumer(
    bootstrap_servers=['localhost:9092', 'localhost:9093', 'localhost:9094'],
    client_id='python-consumer',
    group_id='python-group',
    #  key_deserializer=bytes.decode,
    value_deserializer=bytes.decode
)

consumer.subscribe('names')

for msg in consumer:
    string = (f'partition: {msg.partition}, offset: {msg.offset}, '
              f'value: {msg.value}')
    print(string)

consumer.close()
