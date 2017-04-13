
package budgetApplication.baseClasses;

public class DataTypeGeneric<T> {
    
    private final T property;
    private final String propertyName;
    
    public DataTypeGeneric(T property, String propertyName) {
        this.property = property;
        this.propertyName = propertyName;
    }
    
    public T getProperty() {
        return property;
    }
    
    public String getPropertyName() {
        return propertyName;
    }
}
