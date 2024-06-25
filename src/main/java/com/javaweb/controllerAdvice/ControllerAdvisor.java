package com.javaweb.controllerAdvice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.javaweb.beans.ErrorResponseDTO;
import com.javaweb.customException.FieldRequiredException;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler{
	@ExceptionHandler (ArithmeticException.class) // class bat exception
	public ResponseEntity<Object> handleArithmeticException (ArithmeticException ex, WebRequest request){//WebRequest de quyet dinh loi tra ra
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
		errorResponseDTO.setError(ex.getMessage());
		List<String> details = new ArrayList<String>();
		details.add("chia cho 0");
		errorResponseDTO.setDetail(details);
		return new ResponseEntity<Object>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler (FieldRequiredException.class) // class bat exception
	public ResponseEntity<Object> handleFieldRequiredExceptionException (FieldRequiredException ex, WebRequest request){//WebRequest de quyet dinh loi tra ra
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
		errorResponseDTO.setError(ex.getMessage());
		List<String> details = new ArrayList<String>();
		details.add("ko dc rong");
		errorResponseDTO.setDetail(details);
		return new ResponseEntity<Object>(errorResponseDTO, HttpStatus.BAD_GATEWAY); 
	}
}
