# FizzBuzz Microservices Edition

## About

## Overview

## Infrastructure

## Workflow

### FizzBuzz request

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
