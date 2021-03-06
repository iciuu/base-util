package utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class PropertyUtil {

    private static final String SET_PREFIX = "set";
    private static final String IS_PREFIX = "is";
    private static final String GET_PREFIX = "get";

    /*
     * 调用属性set方法设值
     * */
    public static void setProperty(Object obj, String propertyName, Object value) {
        Class<?> clazz = obj.getClass();
        PropertyDescriptor pd = getPropertyDescriptor(clazz, propertyName);
        Method setMethod = pd.getWriteMethod();
        try {
            setMethod.invoke(obj, new Object[]{value});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getProperty(Object obj, String propertyName) {
        Class<?> clazz = obj.getClass();
        PropertyDescriptor pd = getPropertyDescriptor(clazz, propertyName);
        Method getMethod = pd.getReadMethod();
        Object value = null;
        try {
            value = getMethod.invoke(obj, new Object[]{});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    private static PropertyDescriptor getPropertyDescriptor(Class<?> clazz, String propertyName) {
        Method setMethod = null;
        Method getMethod = null;
        PropertyDescriptor pd = null;
        try {
            Field field = clazz.getDeclaredField(propertyName);// 根据字段名来获取字段
            if (field != null) {
                // 构建方法的后缀
                String methodEnd = propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
                setMethod = clazz.getDeclaredMethod(SET_PREFIX + methodEnd, new Class[] { field.getType() });
                // 构建get 方法
                getMethod = clazz.getDeclaredMethod(GET_PREFIX + methodEnd, new Class[] {});
                // 构建一个属性描述器 把对应属性 propertyName 的 get 和 set 方法保存到属性描述器中
                pd = new PropertyDescriptor(propertyName, getMethod, setMethod);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return pd;
    }
}
