package com.hzqsmemlimittest;

import com.hazelcast.core.QueueStore;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class TestQueueStore implements QueueStore<SomeDataObj> {

    private final ConcurrentHashMap<Long, SomeDataObj> inMemoryStore = new ConcurrentHashMap<>();

    @Override
    public void store(Long key, SomeDataObj value) {
        inMemoryStore.put(key, value);
    }

    @Override
    public void storeAll(Map<Long, SomeDataObj> map) {
        inMemoryStore.putAll(map);
    }

    @Override
    public void delete(Long key) {
        inMemoryStore.remove(key);
    }

    @Override
    public void deleteAll(Collection<Long> keys) {
        keys.forEach(k->inMemoryStore.remove(k));
    }

    @Override
    public SomeDataObj load(Long key) {
        return inMemoryStore.get(key);
    }

    @Override
    public Map<Long, SomeDataObj> loadAll(Collection<Long> keys) {
        return inMemoryStore.entrySet().stream()
                .filter(u->keys.contains(u.getKey()))
                .collect(Collectors.toMap(u->u.getKey(), u->u.getValue()));
    }

    @Override
    public Set<Long> loadAllKeys() {
        return inMemoryStore.keySet();
    }
}
