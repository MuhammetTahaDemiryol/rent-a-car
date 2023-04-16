package com.tahademiryol.rentacar.core.configuration.exceptions;

import com.tahademiryol.rentacar.core.exceptions.BusinessException;
import com.tahademiryol.rentacar.core.utils.results.ExceptionResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY) //  422
    public ExceptionResult<BusinessException> handleBusinessException(BusinessException exception) {
        return new ExceptionResult<>(BusinessException.class, exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //  500
    public ExceptionResult<RuntimeException> handleRuntimeException(RuntimeException exception) {
        return new ExceptionResult<>(RuntimeException.class, exception.getMessage());
    }
}
