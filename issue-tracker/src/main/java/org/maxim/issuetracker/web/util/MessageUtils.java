package org.maxim.issuetracker.web.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public final class MessageUtils {

    public static String getMessageFromBindingResult(BindingResult bindingResult) {
        StringBuilder builder = new StringBuilder();
        for (FieldError error : bindingResult.getFieldErrors()) {
            builder.append("Field '")
                    .append(error.getField())
                    .append("' has error: ")
                    .append(error.getDefaultMessage())
                    .append("\n");
        }
        return builder.toString();
    }

    private MessageUtils() { }

}
