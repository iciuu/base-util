package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListUtil {

    /*
       子List组合进父List
    */
    public static void mergeIntoParentList(String keyPropertyName, String propertyName,
                                           List<? extends Object> parentList, List<? extends Object> sonList) {
        if (parentList == null || sonList == null) {
            return;
        }
        Map<Object, List<Object>> sonMapList = new HashMap<>();
        for (Object son : sonList) {
            Object keyPropertyValue = PropertyUtil.getProperty(son, keyPropertyName);
            List<Object> tmpList = sonMapList.get(keyPropertyValue);
            if (tmpList == null) {
                tmpList = new ArrayList<>();
                sonMapList.put(keyPropertyValue, tmpList);
            }
            tmpList.add(son);
        }
        for (Object parent : parentList) {
            Object parentSonValue = PropertyUtil.getProperty(parent, keyPropertyName);
            List<Object> sonTmpList = sonMapList.get(parentSonValue);
            if (sonTmpList != null) {
                PropertyUtil.setProperty(parent, propertyName, sonTmpList);
            }
        }
    }


}
