package bean_definition;

import java.util.ArrayList;
import java.util.List;

/**
 * 存储一个BeanDefinition所对应的所有参数
 * Created by MiaoZhuang on 2016/5/25.
 */
public class PropertyValues {

    private List<PropertyValue> propertyValueList = new ArrayList<>();

    public void add(PropertyValue propertyValue) {
        propertyValueList.add(propertyValue);
    }

    public List<PropertyValue> getPropertyValues() {
        return propertyValueList;
    }
}
