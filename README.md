# N26 transactions
## Assumptions
- Configuring Kafka is not objective of this task
- Application is eventually consistent
- Stable POST and GET times are crucial and most important
- Latency should be minimal

## Implementation
Incoming transactions are queued on non-blocking thread queue. One thread (yes, exactly one)
iterates over the queue, removes expired transactions and calculates all statistics.
Statistics are pushed to cache which is non blocking, thread-safe class build on
AtomicReference (this should be good enough).

## Eventual bottlenecks
Processing all statistics works takes less than 1ms for about 2500 transactions in queue.
However, it might be possible that for some larger amounts of date this time will increase significantly.

## Possible future solutions
- Use parallel streams in statistic calculators (that won't boost performance much, probably)  
- Parallel statistics calculation. Executor should be replaced
by ExecutorService and passed to statistics collector. All statistics we currently
have can be calculated partially. We divide data into chunks and process them in parallel, using Future to wait for all
tasks. Then we recalculate partial.
- Switch to enterprise grade stream processing solution.

## Building
`mvn install`

## Run
`java -jar target/n26-transactions-0.0.1-SNAPSHOT.jar`

## Testing
`post.sh` is part of the code, it generates valid requests spamming the instance