package com.tahademiryol.rentacar.core.configuration.exceptions;

import com.tahademiryol.rentacar.common.constants.ExceptionTypes;
import com.tahademiryol.rentacar.core.exceptions.BusinessException;
import com.tahademiryol.rentacar.core.utils.results.ExceptionResult;
import jakarta.validation.ValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST) //  400
    public ExceptionResult<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> validationErrors = exception.getBindingResult().getFieldErrors().stream()
                .collect(
                        java.util.stream.Collectors.toMap(
                                fieldError -> fieldError.getField(),
                                fieldError -> fieldError.getDefaultMessage()
                        )
                );
        return new ExceptionResult<>(ExceptionTypes.Exception.Validation, validationErrors);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY) //  422
    public ExceptionResult<Object> handleValidationException(ValidationException exception) {
        return new ExceptionResult<>(ExceptionTypes.Exception.Validation, exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY) //  422
    public ExceptionResult<Object> handleBusinessException(BusinessException exception) {
        return new ExceptionResult<>(ExceptionTypes.Exception.Business, exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT) // 409
    public ExceptionResult<Object> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        return new ExceptionResult<>(ExceptionTypes.Exception.DataIntegrityViolation, exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //  500
    public ExceptionResult<Object> handleRuntimeException(RuntimeException exception) {
        return new ExceptionResult<>(ExceptionTypes.Exception.Runtime, exception.getMessage());
    }
}
