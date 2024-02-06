package omer.solutions.javatest.util;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.JsonNode;

public abstract class RequestException {

    @JsonAnySetter
    public void handleUnknownField(String field, JsonNode value) {
        throw new IllegalArgumentException(
                MessageInfo.ILLEGAL_ARGUMENT + field);
    }
}
