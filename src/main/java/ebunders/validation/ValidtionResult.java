package ebunders.validation;

/**
 * Created by ernst on 26-2-15.
 */
public class ValidtionResult {
    private final String path;
    private final Object o;
    private final String message;

    public ValidtionResult(String path, Object object, String message) {
        this.path = path;
        this.o = object;
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public Object getObject() {
        return o;
    }

    public String getMessage() {
        return message;
    }
}
