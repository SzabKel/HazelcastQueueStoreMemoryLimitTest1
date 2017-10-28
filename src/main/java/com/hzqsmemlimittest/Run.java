package com.hzqsmemlimittest;

import com.hazelcast.config.Config;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.config.QueueConfig;
import com.hazelcast.config.QueueStoreConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;

public class Run {

    private static final String QUEUE_NAME = "testQueue";

    public static void main(String[] args) {
        TestQueueStore testQueueStore = new TestQueueStore();

        Config config = new Config();

        ManagementCenterConfig managementCenterConfig = new ManagementCenterConfig();
        managementCenterConfig.setEnabled(true);
        managementCenterConfig.setUrl("http://localhost:8080/mancenter");
        config.setManagementCenterConfig(managementCenterConfig);

        QueueConfig queueConfig = config.getQueueConfig(QUEUE_NAME);
        queueConfig.setMaxSize(0);

        QueueStoreConfig queueStoreConfig = new QueueStoreConfig();
        queueStoreConfig.setStoreImplementation(testQueueStore);
        queueStoreConfig.setEnabled(true);
        queueStoreConfig.setProperty("memory-limit", "5");
        queueStoreConfig.setProperty("bulk-load", "10");

        queueConfig.setQueueStoreConfig(queueStoreConfig);

        HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);

        IQueue<SomeDataObj> testQueue = instance.getQueue(QUEUE_NAME);

        for (int i = 0; i < 100; i++) {
            testQueue.offer(new SomeDataObj(i, "Data "+i));
        }

        System.gc();
        System.out.println(); // <-- Breakpoint here
    }
}
