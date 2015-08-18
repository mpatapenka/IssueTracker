package org.maxim.issuetracker.web.advice;

import org.maxim.issuetracker.web.constants.MappingConstants;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleError404() {
        return MappingConstants.PAGE_404;
    }

}
