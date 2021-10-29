package projectx.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<RestExceptionMessage> handleResorceNotFound(ResourceNotFoundException ex,
			WebRequest request) {
		
		/*
		 * request.getDescription(false) will return usually the current URI 
		 * in a format like uri=/customers/100
		 */
		RestExceptionMessage rem = new RestExceptionMessage(ex.getMessage(), 
				request.getDescription(false), new Date());
		return new ResponseEntity<RestExceptionMessage>(rem, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<RestExceptionMessage> handleAllExceptions(Exception ex,
			WebRequest request) {
		
		RestExceptionMessage rem = new RestExceptionMessage(ex.getMessage(), 
				request.getDescription(false), new Date());
		return new ResponseEntity<RestExceptionMessage>(rem, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
