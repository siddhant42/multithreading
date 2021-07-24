package concurrent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExample {
    public static void main(String[] args) {

        ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("A","Apple");
        concurrentHashMap.put("B","Blackberry");
        for (Map.Entry<String,String> e : concurrentHashMap.entrySet()) {
            System.out.println(e.getKey() + " = " + e.getValue());
        }
    }
}
