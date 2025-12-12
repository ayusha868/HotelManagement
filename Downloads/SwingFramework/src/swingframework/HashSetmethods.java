/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swingframework;

/**
 *
 * @author aayusharijal
 */
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class HashSetmethods {
    public static void main(String[] args)
    {
            HashSet<String>hs = new HashSet<>();
            
            hs.add("Apple");
            hs.add("Banana");
            hs.add("Orange");
            hs.add("Mango");
            hs.add("Banana");
            
            
            System.out.println("HashSet: " + hs);
            
            System.out.println("Contains Mango?" + hs.contains("Mango"));
            
            
            System.out.println("removed :" + hs.remove("Orange"));
            hs.size();
            
            System.out.println("size :" + hs.size());
            System.out.println(hs.toString());
            System.out.println("String:" + hs);
            System.out.println(hs.isEmpty());
            
            LinkedHashSet<String>ls = new LinkedHashSet<>();
            ls.add("Apple");
            ls.add("Banana");
            ls.add("Orange");
            ls.add("Mango");
            ls.add("Banana");
            
            
            System.out.println("LinkedHashSet: " + ls);
            
            System.out.println("Contains Mango?" + ls.contains("Mango"));
            
            
            System.out.println("removed :" + ls.remove("Orange"));
            ls.size();
            
            System.out.println("size :" + ls.size());
            System.out.println(ls.toString());
            System.out.println("String:" + ls);
            System.out.println(ls.isEmpty());
            
            TreeSet<String>ts = new TreeSet<>();
            ts.add("20");
            ts.add("30");
            ts.add("40");
            ts.add("50");
            ts.add("60");
            
            System.out.println("TreeSet: " + ts);
            System.out.println("first: " + ts.first());
            System.out.println("last: " + ts.last());
            System.out.println("higher: " + ts.higher("40"));
            System.out.println("pollfirst: " + ts.pollFirst());
            System.out.println("polllast: " + ts.pollLast());
            System.out.println("clone: " + ts.clone());
            
            
            HashMap<String , String>hm = new HashMap<>();
            hm.put("ap", "apple");
            hm.put("or", "orage");
            hm.put("ba", "banana");
            hm.put("gu", "guava");
            hm.put("ma", "mango");
            
            System.out.println("hashmap: " + hm);
            System.out.println("Apple: " + hm.get("Apple"));
            System.out.println("has in: " + hm.containsKey("or"));
            System.out.println("removed: " + hm.remove("Apple"));
            System.out.println("Contains guava? " + hm.containsValue("guava"));
            System.out.println("Size: " + hm.size());
            
            LinkedHashMap<String, Integer> lhm = new LinkedHashMap<>();
            lhm.put("Apple", 50);
            lhm.put("Mango", 30);
            lhm.put("Banana", 20);
            
            System.out.println("linkedhashmap: " + lhm);
            System.out.println("Apple: " + lhm.get("Apple"));
            System.out.println("removed: " + lhm.remove("Mango"));
            
            TreeMap<Integer, String> tm = new TreeMap<>();
            tm.put(3, "Apple");
            tm.put(1, "Mango");
            tm.put(2, "Banana");
            tm.put(5, "Grapes");
            tm.put(4, "Orange");

            System.out.println("TreeMap: " + tm);

            System.out.println("First Key: " + tm.firstKey());
            System.out.println("Last Key: " + tm.lastKey());

            System.out.println("Higher Key than 3: " + tm.higherKey(3));
            System.out.println("Lower Key than 3: " + tm.lowerKey(3));

            System.out.println("Ceiling Key 3: " + tm.ceilingKey(3));
            System.out.println("Floor Key 3: " + tm.floorKey(3));

            System.out.println("Key Set: " + tm.keySet());
            System.out.println("Values: " + tm.values());
            System.out.println("Entry Set: " + tm.entrySet());

    }
}
