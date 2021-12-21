package com.bridgelabz.addressbook.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.bridgelabz.addressbook.dto.ResponseDto;
@ControllerAdvice
public class AddressBookExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseDto> handleMethodArguementNotValidException(MethodArgumentNotValidException exception){
		List<ObjectError> errorList=exception.getBindingResult().getAllErrors();
		List<String> errMsg=errorList.stream()
							.map(objError->objError.getDefaultMessage())
							.collect(Collectors.toList());
		ResponseDto responseDto=new ResponseDto("Exception while processing Rest Request",errMsg);
		return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.BAD_REQUEST);

	}
	@ExceptionHandler(AddressBookException.class)
	public ResponseEntity<ResponseDto> handleAddressBookException(AddressBookException exception){
		ResponseDto responseDto=new ResponseDto("Exception while processing Rest Request",exception.getMessage());
		return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.BAD_REQUEST);
	}
}
