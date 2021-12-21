package com.bridgelabz.addressbook.dto;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.Data;
@Data
public class AddressBookDto {
	@NotEmpty(message="Employee Name cannot be Empty")
	@Pattern(regexp="[A-Z][a-z]{2,}",message="Person Name Invalid")
	public String name;
	public String city;
	public AddressBookDto(String name,String city) {
		this.name=name;
		this.city=city;
		}
	@Override
	public String toString() {
		return "Name="+name+" City="+city;
	}
}
