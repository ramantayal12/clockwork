# clockwork

- Goal: Clockwork provides callbacks to expected services at specified time ranges.
- Input: URL and data as a request.
- Technologies:
  * Implementation: Spring Boot and Java
  * Queueing: Kafka
  * Cron Job Functionality: Spring Scheduler
  * Database: Currently MySQL and MongoDb is used, with ongoing exploration for options with lower latency.
- Exploration Focus: Scalability considerations for larger-scale operations, including planning for potential failures.


## High-Level Design : 

![20240301_131140](https://github.com/ramantayal12/clockwork/assets/42838224/38f2dce8-f0fe-49dc-ad9a-1e771c1df835)

## Project Working Details :
- APIs:
  * Fetch Job Details
  * Post Callback
  * Get Callback 
- Database:
  * Name: curses
  * Tables: event, job
- Swagger Port: 8082
- Kafka Consumer Group Name: jobs-consumer-group-1
- Kafka Topic Name: jobs-topic-id-1




