package com.javaschool.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.UncategorizedJmsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(UncategorizedJmsException.class)
    protected String handleActiveMQExceptions(UncategorizedJmsException e) {
        log.error(e.getMessage(), e);
        return "errorWithOuterServices";
    }

    @ExceptionHandler(Exception.class)
    protected String handleExceptions(Exception e) {
        log.error(e.getMessage(), e);
        return "errorPage";
    }


}
