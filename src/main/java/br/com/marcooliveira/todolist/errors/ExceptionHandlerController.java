package br.com.marcooliveira.todolist.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice /*USADO PARA DEFINIR CLASSES GLOBAIS NO MOMENTO DE TRATAMENTO DE EXCEÇÕES.
TODA EXCEÇÃO QUE OCORRER NA APLICAÇÃO, PASSARÁ PELOS TIPOS QUE ESTIVER NESTE CONTROLLER.*/
public class ExceptionHandlerController {

    @ExceptionHandler(HttpMessageNotReadableException.class)/*TODA EXCEÇÃO DESTE TIPO IRÁ
PASSAR PELO MÉTODO ABAIXO.*/
    public ResponseEntity<String> handlerHttpMessageNotReadableException(HttpMessageNotReadableException e) {
 
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMostSpecificCause().getMessage());
    }
    
}
