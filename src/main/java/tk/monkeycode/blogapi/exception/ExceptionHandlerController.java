package tk.monkeycode.blogapi.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(Exception.class)
	@ApiResponse(
        	responseCode = "500",
        	description = "Internal Server Error", 
        	content = { @Content(schema = @Schema(implementation = ExceptionResponse.class)) }
    )
    public final ResponseEntity<ExceptionResponse> manejarTodasExcepciones(Exception ex, WebRequest request) {
        ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(), ex.getMessage());
        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        return new ResponseEntity<ExceptionResponse>(er, HttpStatus.INTERNAL_SERVER_ERROR);
    }

	
    @ApiResponse(
        	responseCode = "404",
        	description = "Not Found", 
        	content = { @Content(schema = @Schema(implementation = ExceptionResponse.class)) }
    )
    @ExceptionHandler(ModelNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> manejarModeloException(ModelNotFoundException ex, WebRequest request) {
        ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(), ex.getMessage());
        return new ResponseEntity<ExceptionResponse>(er, HttpStatus.NOT_FOUND);
    }

    
    @Override
    @ApiResponse(
    	responseCode = "400",
    	description = "Bad Request", 
    	content = { @Content(schema = @Schema(implementation = ErrorResponse.class)) }
    )
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    	List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
    	List<String> errors = fieldErrors.stream().map(fe -> fe.getDefaultMessage()).collect(Collectors.toList());
    	ErrorResponse er = new ErrorResponse(errors);
        return new ResponseEntity<Object>(er, HttpStatus.BAD_REQUEST);
    }
    
    
}
