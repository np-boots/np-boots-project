package cn.np.boots.common.utils.type;

import java.util.Collection;

public class NpCollectionUtils {
    public boolean isEmpty(Collection<?> collection){
        if(collection == null) return true;
        return collection.isEmpty();
    }

    public <T> boolean isEmpty(T[] array){
        if(array == null) return true;
        return array.length == 0;
    }
}
