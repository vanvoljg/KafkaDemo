
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

All commands assume a running Apache Kafka cluster (at least one broker). Any topics
should be created beforehand using the `kafka-topics.sh` Kafka script.

## Java

All commands should be run from within the `java_demo` subdirectory.

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

### Consumer

- `javascript_demo $ yarn run consumer <topic>`

- `<topic>` is the topic to subscribe to

### Producer

- `javascript_demo $ yarn run producer <topic>`

- `<topic>` is the topic to send messages to

## Python


## Elixir
