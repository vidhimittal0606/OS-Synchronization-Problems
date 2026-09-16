# OS Synchronization Problems in Java
This repository contains Java implementations of three classic Operating System process synchronization problems using semaphores.

## Problems Implemented

### 1. Producer-Consumer Problem
The Producer-Consumer problem demonstrates synchronization between
a producer that generates data and a consumer that consumes data
from a shared bounded buffer.

Concepts used:
- Semaphore
- Mutex
- Critical Section
- Mutual Exclusion
- Synchronization
- Bounded Buffer
- Race Condition Prevention

### 2. Reader-Writer Problem
The Reader-Writer problem demonstrates synchronization when multiple
readers and writers access a shared resource.

Multiple readers are allowed to read simultaneously, while writers
require exclusive access.

Concepts used:
- Semaphore
- Mutex
- Reader Count
- Mutual Exclusion
- Shared Resource
- Concurrent Reading
- Exclusive Writing

### 3. Dining Philosophers Problem
The Dining Philosophers problem demonstrates synchronization and
deadlock prevention when multiple philosophers compete for shared
resources (chopsticks).

The implementation uses a mutex and different chopstick acquisition
orders for even and odd philosophers.

Concepts used:
- Semaphore
- Mutex
- Deadlock
- Circular Wait
- Resource Allocation
- Mutual Exclusion
- Synchronization

## Technologies Used
- Java
- Java Threads
- Java Semaphore
- Operating System Synchronization Concepts

## Repository Structure
OS-Synchronization-Problems/
│
├── Producer-Consumer/
│   └── ProducerConsumer.java
│
├── Reader-Writer/
│   └── ReaderWriter.java
│
└── Dining-Philosophers/
    └── DiningPhilosophers.java
