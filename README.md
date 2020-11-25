
# Kafka Demo Project

1. [Introduction](#introduction)
2. [Java](#java)
3. [JavaScript](#javascript)
4. [Python](#python)
5. [Elixir](#elixir)

## Introduction

This is a demonstration project to showcase usage of Apache Kafka consumers and
producers in multiple languages--Java, JavaScript, Python, and Elixir.

Instructions to run each version are listed below. All versions assume reasonably
recent versions of the languages are installed and available. Installation is
beyond the scope of this guide. These will probably run cross-platform, but have
only been tested on Linux.

All commands assume a running Apache Kafka cluster (at least one broker) at
`localhost:9092`. Any topics should be created beforehand using the
`kafka-topics.sh` Kafka script.

## Java

All commands should be run from within the `java_demo` subdirectory.

### Preparation

The built-in Gradle wrapper will automatically download a copy of Gradle, plus
all dependencies, at first run. Subsequent runs are faster.

To complete this step before running consumers or producers (optional, this step
is performed automatically):

- `java_demo $ ./gradlew build`

### Consumer

To start a consumer, use the `consumer.sh` shell script:

- `java_demo $ ./consumer.sh <type> <topic>`

- `<type>` should be one of the following:
  - `auto-commit`
  - `manual-commit`
  - `partition-assignment`

- `<topic>` should be the name of the topic (or topics) to subscribe to.

### Producer

To start a producer, use the `producer.sh` shell script:

- `java_demo $ ./producer.sh <type> <count>`

- `<type>` should be the type of messages to send (**this is also the topic name**)
  - `numbers`
  - `strings`

- `<count>` is the number of messages to send (optional, default: **50**)

## JavaScript

All commands should be run from within the `javascript_demo` subdirectory. These
examples use yarn, but npm will work in the same way (`npm run ...`).

### Preparation

Ensure all dependencies are installed.

- `javascript_demo $ yarn install`

### Consumer

To run a JavaScript consumer on a specific topic:

- `javascript_demo $ yarn run consumer <topic>`

- `<topic>` is the topic to subscribe to

### Producer

To run a JavaScript producer on a specific topic:

- `javascript_demo $ yarn run producer <topic>`

- `<topic>` is the topic to send messages to

## Python

All commands should be run from within the `python_demo` subdirectory.

### Preparation

This requires the `Faker` and `kafka-python` packages from pip. Make sure they are
installed before proceeding.

- `python_demo $ pip install Faker kafka-python`

### Consumer

To run a consumer that will consume messages on the `names` topic:

- `python_demo $ python consumer.py`

### Producer

To run a producer that will create 100 random names and send them to the `names`
topic:

- `python_demo $ python producer.py`

## Elixir

All commands should be run from within the `elixir_demo` subdirectory.

### Preparation

Pull in all dependencies using Mix:

`elixir_demo $ mix deps.get`

### Consumer

To run a consumer which will consume messages from the `cities` topic:

- `elixir_demo $ mix consumer`

### Producer

To run a producer which will produce 100 random cities to the `cities` topic:

- `elixir_demo $ mix producer`

## License

This project is licensed under the [MIT license](./LICENSE.md)
