package ebunders.validation.util;

import java.util.Map;

/**
 * Created by ernst on 26-2-15.
 */
public class CollectionsUtil {

    public static <T, K> T getOrPut(Map<K, T> map, K key, T defaultVal){
        if(map.get(key) == null){
            map.put(key, defaultVal);
        }
        return map.get(key);
    }
}
