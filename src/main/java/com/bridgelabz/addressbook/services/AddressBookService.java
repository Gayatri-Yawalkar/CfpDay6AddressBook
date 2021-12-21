package com.bridgelabz.addressbook.services;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.bridgelabz.addressbook.dto.AddressBookDto;
import com.bridgelabz.addressbook.exceptions.AddressBookException;
import com.bridgelabz.addressbook.model.AddressBookData;
@Service
public class AddressBookService implements IAddressBookService{
	private List<AddressBookData> addressBookList=new ArrayList<AddressBookData>();
	@Override
	public List<AddressBookData> getAddressBookdata() {
		return addressBookList;
	}
	@Override
	public AddressBookData getAddressBookDataById(int personId) {
		return addressBookList.stream()
				.filter(personData->personData.getPersonId()==personId)
				.findFirst()
				.orElseThrow(()->new AddressBookException("Person Not Found"));
	}
	@Override
	public AddressBookData createAddressBookData(AddressBookDto addressBookDto) {
		AddressBookData addressBookData=null;
		addressBookData=new AddressBookData(addressBookList.size()+1,addressBookDto);
		addressBookList.add(addressBookData);
		return addressBookData;
	}
	@Override
	public AddressBookData updateAddressBookData(int personId,AddressBookDto addressBookDto) {
		try {
			AddressBookData addressBookData=this.getAddressBookDataById(personId);
			addressBookData.setName(addressBookDto.name);
			addressBookData.setCity(addressBookDto.city);
			addressBookList.set(personId-1,addressBookData);
			return addressBookData;
		} catch(IndexOutOfBoundsException e) {
			return null;
		}
	}
	@Override
	public void deleteAddressBookData(int personId) {
		addressBookList.stream()
		.filter(personData->personData.getPersonId()==personId)
		.findFirst()
		.orElseThrow(()->new AddressBookException("Person Not Found"));
		addressBookList.remove(personId-1);
	}
	
}
