package basic;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class TGraph<T> {
    private Map<T, Set<T>> storage = new ConcurrentHashMap<>();

    public void addEdge(T v, T w) {
        if(!storage.containsKey(v)) {
            storage.put(v, new CopyOnWriteArraySet());
        }
        if(!storage.containsKey(w)) {
            storage.put(w, new CopyOnWriteArraySet());
        }
        if(storage.containsKey(v)) {
            storage.get(v).add(w);
        }
    }

    public Set<T> getNodes() {
        return storage.keySet();
    }

    public int getV() {
        return storage.size();
    }

    public Set<T> getLink(T v) {
        return storage.get(v);
    }

    public void print() {
        for(T key: storage.keySet()) {
            System.out.print(key + ": ");
            storage.get(key).forEach(n -> System.out.print(n + " "));
            System.out.println();
        }
    }
}
