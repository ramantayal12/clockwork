# clockwork

HLD : 


![20240301_131140](https://github.com/ramantayal12/clockwork/assets/42838224/38f2dce8-f0fe-49dc-ad9a-1e771c1df835)


- Goal : Provides callbacks to expected service at specified time range
- Input: URL and data as request
- Technologies:
  -- Spring Boot and Java for implementation
  -- Kafka for queuing data
  -- Spring Scheduler for cron job functionality
- Database: Currently MongoDB is used, but exploring options for less latency
- Exploration focus: Scalability considerations for larger scale operation, like how things are going to break.
