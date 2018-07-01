package bean_definition;

/**
 * 存储property元素下的信息，name-value对或者name-ref对
 * Created by MiaoZhuang on 2016/5/25.
 */
public class PropertyValue {

    private String name;

    private Object object;

    public PropertyValue(String name, Object object) {
        this.name = name;
        this.object = object;
    }

    public String getName() {
        return this.name;
    }

    public Object getObject() {
        return this.object;
    }
}
