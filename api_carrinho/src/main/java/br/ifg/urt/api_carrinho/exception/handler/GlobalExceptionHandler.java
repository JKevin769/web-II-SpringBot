package br.ifg.urt.api_carrinho.exception.handler;

import br.ifg.urt.api_carrinho.exception.EstoqueLnsuficienteException;
import br.ifg.urt.api_carrinho.exception.ExceptionResponse;
import br.ifg.urt.api_carrinho.exception.ProdutoNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;

import java.sql.Date;
import java.time.LocalDateTime;

//@ControllerAdvice
//@RestController
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Tratamento para Recurso Não Encontrado (404)
    @ExceptionHandler(ProdutoNotFoundException.class)
//    public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions(
//            Exception ex, WebRequest request) {
//
//        ExceptionResponse exceptionResponse = new ExceptionResponse(
//                new Date(),
//                ex.getMessage(),
//                request.getDescription(false));
//        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);}

    public ResponseEntity<ExceptionResponse> handleNotFoundExceptions(
            ProdutoNotFoundException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleBadRequest(IllegalArgumentException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(EstoqueLnsuficienteException.class)
    public ResponseEntity<ExceptionResponse> handleEstoqueInsuficiente(EstoqueLnsuficienteException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleRuntime(RuntimeException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI());
    }

    private ResponseEntity<ExceptionResponse> buildResponse(HttpStatus status, String message, String path) {
        ExceptionResponse body = new ExceptionResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                path
        );
        return ResponseEntity.status(status).body(body);
    }


}
