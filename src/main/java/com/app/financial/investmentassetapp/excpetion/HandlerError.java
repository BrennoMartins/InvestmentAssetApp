package com.app.financial.investmentassetapp.excpetion;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class HandlerError extends ResponseEntityExceptionHandler {


    @ExceptionHandler(AssetNotFoundException.class)
    public final ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest request) throws Exception {
        AssetErrorDetails assetErrorDetails =  new AssetErrorDetails("Asset not found");
        return new ResponseEntity<>(assetErrorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
        AssetErrorDetails assetErrorDetails =  new AssetErrorDetails("Generic Error " + ex.getMessage());
        return new ResponseEntity<>(assetErrorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        BindingResult br = ex.getBindingResult();
        List<ObjectError>teste = br.getAllErrors();
        String msg = String.valueOf(teste.stream()
                .map(i -> "Campo: " + i.getObjectName() + " - " + "Detalhe: " + i.getDefaultMessage())
                .findFirst());


        AssetErrorDetails assetErrorDetails =  new AssetErrorDetails(ex.getFieldError().getDefaultMessage());


        return new ResponseEntity<>(assetErrorDetails, HttpStatus.BAD_REQUEST);
    }
}
