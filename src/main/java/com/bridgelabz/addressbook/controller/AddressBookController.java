package com.bridgelabz.addressbook.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.addressbook.dto.AddressBookDto;
import com.bridgelabz.addressbook.dto.ResponseDto;
import com.bridgelabz.addressbook.model.AddressBookData;
import com.bridgelabz.addressbook.services.IAddressBookService;
@RestController
@RequestMapping("/addressbookservice")
public class AddressBookController {
	@Autowired
	private IAddressBookService addressBookService;
	@RequestMapping(value= {"","/","/get"})
	public ResponseEntity<ResponseDto> getAddressBookData() {
		List<AddressBookData> addressBookList=addressBookService.getAddressBookdata();
		ResponseDto respDto=new ResponseDto("Get Call Success",addressBookList);;
		return new ResponseEntity<ResponseDto>(respDto,HttpStatus.OK);
	}
	@GetMapping("/get/{personId}")
	public ResponseEntity<ResponseDto> getAddressBookData(@PathVariable("personId") int personId) {
		ResponseDto respDto;
		AddressBookData addressBookData=addressBookService.getAddressBookDataById(personId);
		respDto=new ResponseDto("Get Call For Id Success",addressBookData.getName());
		return new ResponseEntity<ResponseDto>(respDto,HttpStatus.OK);
	}
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> addAddressBookData(@Valid @RequestBody AddressBookDto addressBookdto) {
		addressBookService.createAddressBookData(addressBookdto);
		ResponseDto respDto=new ResponseDto("Created Address Book Data Successfully");
		return new ResponseEntity<ResponseDto>(respDto,HttpStatus.OK);
	}
	@PutMapping("/update/{personId}")
	public ResponseEntity<ResponseDto> updateAddressBookData(@PathVariable("personId") int personId,@Valid @RequestBody AddressBookDto addressBookdto) {
		ResponseDto respDto;
		addressBookService.updateAddressBookData(personId,addressBookdto);
		respDto=new ResponseDto("Updated Address Book Data Successfully");
		return new ResponseEntity<ResponseDto>(respDto,HttpStatus.OK);
	}
	@DeleteMapping("/delete/{personId}")
	public ResponseEntity<ResponseDto> deleteAddressBookData(@PathVariable("personId") int personId) {
		ResponseDto respDto;
		addressBookService.deleteAddressBookData(personId);
		respDto=new ResponseDto("Deleted Successfully id="+personId);
		return new ResponseEntity<ResponseDto>(respDto,HttpStatus.OK);
	}
}	