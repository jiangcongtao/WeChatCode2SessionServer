package com.nickjiang.WeChatCode2SessionServer.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@ControllerAdvice
public class ApiErrorHandler {
    private static final Logger log = LoggerFactory.
            getLogger(ApiErrorHandler.class);
    private final MessageSource messageSource;

    @Autowired
    public ApiErrorHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException
            (HttpServletRequest request, Exception ex, Locale locale) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}