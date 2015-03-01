package ebunders.validation;

import java.util.Collection;

/**
 * Created by ernst on 26-2-15.
 */
public interface Value {
    public String asString() throws ConversionException;
    public Number asNumber() throws ConversionException;
    public Collection asCollection() throws ConversionException;
}
