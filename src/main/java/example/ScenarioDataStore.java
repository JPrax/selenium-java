package example;

import java.util.HashMap;

public class ScenarioDataStore {
    public static HashMap<String, String> ScenarioDataStore = new HashMap<String, String>();

    public static void put(String key, String value){
        ScenarioDataStore.put(key,value);
    }

    public static String get(String key){
        return ScenarioDataStore.get(key);
    }
}
