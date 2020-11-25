# Python-based Apache Kafka producer

from faker import Faker
from kafka import KafkaProducer

fake = Faker()

producer = KafkaProducer(
    bootstrap_servers=[
        'localhost:9092',
        'localhost:9093',
        'localhost:9094',
    ],
    client_id='python-client',
    acks='all',
    #  key_serializer=str.encode,
    value_serializer=str.encode
)

for _ in range(100):
    string = f'{fake.first_name()}'
    print(string)
    producer.send('names', value=string)

producer.flush()
