# HazelcastQueueStoreMemoryLimitTest1
Testing the memory-limit feature of Hazelcast's QueueStore

I used the debugger to check the number of Data objects currently stored. 

There should be only 5 entries in Hazelcast's Queue, while the `TestQueueStore` correctly stores all the objects. I expected the `TestQueue`'s size to be equal to `5`.

![Debugger screenshot](https://i.imgur.com/xp4I8Pq.png)
