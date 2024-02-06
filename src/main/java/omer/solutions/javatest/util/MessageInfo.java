package omer.solutions.javatest.util;

@SuppressWarnings("UnnecessaryModifier")
public enum MessageInfo {

    /**INFORMATION*/
    CREATED_PRODUCT("create product successful"),
    UPDATE_PRODUCT("update product successful"),
    UPDATE_PRODUCT_DETAIL("update product details successful"),
    NOT_FOUND_PRODUCT("not found product"),
    NOT_FOUND_PRODUCT_DETAIL("not found product detail"),

    /**EXCEPTIONS*/
    JSON_NOT_VALIDATE("JSON format no validate"),
    METHOD_ARGUMENT_NOT_VALIDATE("argument no validate"),
    INVALIDATE_DATA("No default parameter added"),
    DATA_INTEGRATE_VIOLATION("Error when not entering a required value"),
    HANDLER_NOT_FOUND("not found route"),
    ILLEGAL_ARGUMENT("Contains parameters that should not be in JSON.");

    private final String message;

    private MessageInfo(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
