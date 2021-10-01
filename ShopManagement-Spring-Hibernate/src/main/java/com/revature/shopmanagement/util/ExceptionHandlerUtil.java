package com.revature.shopmanagement.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.revature.shopmanagement.exception.DataBaseException;
import com.revature.shopmanagement.exception.DuplicateIdException;
import com.revature.shopmanagement.exception.IdNotFoundException;
import com.revature.shopmanagement.exception.NullValueException;

@ControllerAdvice
public class ExceptionHandlerUtil {
	// for Duplicate Id insertion..
	@ExceptionHandler(DuplicateIdException.class)
	public ResponseEntity<String> duplicateIdFound(DuplicateIdException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// for Id not Found ..
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> idNotFound(IdNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	// for fetching data in DB
	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<String> dataBaseException(DataBaseException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// for null values
	@ExceptionHandler(NullValueException.class)
	public ResponseEntity<String> nullValueException(NullValueException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
	}
}
