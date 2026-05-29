# FizzBuzz Microservices Edition

*I don't remember why and how i created this.*

## About

A simple FizzBuzz microservices solution, using Kotlin, Spring, Apache Kafka, MySQL and Docker.
Inspired by the [FizzBuzzEnterpriseEdition](https://github.com/EnterpriseQualityCoding/FizzBuzzEnterpriseEdition).

## Overview

While there are a lot of FizzBuzz solutions out there, most of them unfortunately do not use microservices.
<br>The general rules are as following:

- If number is divisible by 3 return Fizz
- If number is divisible by 5 return Buzz
- If number is divisible by 3 and 5 return FizzBuzz

## Infrastructure

The solution contains multiple modules, each of them is a microservice, that can be deployed, and duplicated for
reliability.

- **core:** Contains general settings, exceptions and DTOs that are used in multiple modules.
- **fizzbuzz-database-service:** Saves and updates FizzBuzz requests.
- **fizzbuzz-gateway:** All requests are sent through the gateway, uses basic-auth, for simplicity.
- **fizzbuzz-logging-service:** Receives and saves logs of FizzBuzz requests.
- **fizzbuzz-orchestration-service:** Receives events from the other microservices and sends commands.
- **fizzbuzz-transform-service:** Will receive numbers and apply the FizzBuzz rules.

Logs, FizzBuzz requests and User-Details get saved in different DBs, communication between the microservices are via
Apache Kafka, unless it is to receive results or logs.

## Workflows

There are 3 workflows, depending on the request sent by the user.

### FizzBuzz request

This workflow will be running when a user wishes to get a FizzBuzz result for any number, the communication between the
microservices will run via Apache Kafka.
<br>Databases are not part of the diagram, else it would get too big.

```mermaid
sequenceDiagram
    participant c as Client
    participant gs as Gateway Service
    participant os as Orchestration Service
    participant ts as Transformation Service
    participant ls as Logging Service
    participant ds as Database Service
    c -->> gs: POST FizzBuzz request
    gs ->> os: request-event
    os ->> ls: log-create-command
    ls -->> ls: Save log to Database
    os ->> ds: database-update-command
    ds -->> ds: Save entry to Database
    alt Save success
        ds ->> os: database-update-success-event
    else Save failure
        ds ->> os: database-update-failed-event
    end
    os ->> ls: log-create-command
    ls -->> ls: Save log to Database
    alt database-update-success-event
        os ->> ts: transform-command
        alt Transform success
            ts ->> os: transform-success-event
        else Transform failure
            ts ->> os: transform-failed-event
        end
        os ->> ls: log-create-command
        ls -->> ls: Save log to Database
        alt transform-success-event
            os ->> ds: database-update-command
            ds -->> ds: Update entry in Database
            alt Update success
                ds ->> os: database-update-success-event
            else Update failure
                ds ->> os: database-update-failed-event
            end
            os ->> ls: log-create-command
            ls -->> ls: Save log to Database
        end
    end
```

### FizzBuzz result

This workflow will run to return the FizzBuzz result, communication between microservices is via REST.

```mermaid
sequenceDiagram
    participant c as Client
    participant gs as Gateway Service
    participant ds as Database Service
    participant db as MySQL Database
    c -->> gs: GET FizzBuzz result
    gs ->> ds: GET FizzBuzz result
    ds -->> ds: Get entry from Database
    alt Has entry
        db -->> ds: Return entry
        ds ->> gs: return FizzBuzz result with status 200
        gs -->> c: return FizzBuzz result with status 200
    else Has no entry
        db -->> ds: Return null
        ds ->> gs: return null with status 404
        gs -->> c: return null with status 404
    end
```

### Logging request

This workflow will run to return logs, communication between microservices is via REST.

```mermaid
sequenceDiagram
    participant c as Client
    participant gs as Gateway Service
    participant ls as Logging Service
    participant db as MySQL Database
    c -->> gs: GET logs
    gs ->> ls: GET logs
    ls -->> db: Get logs from Database
    db -->> ls: Return list of logs
    ls ->> gs: return logs with status 200
    gs -->> c: return logs with status 200
```

## Prerequisites

## Preparations

## Run

### Authentication

### Send a FizzBuzz request

### Get a FizzBuzz result

### See the logs

## Final words
