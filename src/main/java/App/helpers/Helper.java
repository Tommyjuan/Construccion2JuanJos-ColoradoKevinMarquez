package App.helpers;


import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.dto.UserDto;
import app.model.Partner;

import app.model.Person;
import app.model.User;


public abstract class Helper {

    // Son conversiones
    public static PersonDto parse(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setId(person.getId());
        personDto.setDocument(person.getDocument());
        personDto.setName(person.getName());
        personDto.setCelPhone(person.getCelPhone());
        return personDto;
    }

    public static Person parse(PersonDto personDto) {
        Person person = new Person();
        person.setId(personDto.getId());
        person.setDocument(personDto.getDocument());
        person.setName(personDto.getName());
        person.setCelPhone(personDto.getCelPhone());
        return person;
    }

    public static UserDto parse(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setPassword(user.getPassword());
        userDto.setPersonId(parse(user.getPersonId()));
        userDto.setRol(user.getRol());
        userDto.setUserName(user.getUserName());
        return userDto;
    }

    public static User parse(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setPassword(userDto.getPassword());
        user.setPersonId(parse(userDto.getPersonId()));
        user.setRol(userDto.getRol());
        user.setUserName(userDto.getUserName());
        return user;
    }

    	public static Partner parse(PartnerDto partnerDto) {
		Partner  partner = new Partner();
		partner .setId(partnerDto.getId());
		partner .setUser_id(parse(partnerDto.getUserDto_id()));
		partner .setMoney(partnerDto.getMoney());
		partner .setType(partnerDto.getType());
		partner .setDateCreated(partnerDto.getDateCreated());
		return partner;
	}
	
	public static PartnerDto parse(Partner partner) {
		PartnerDto partnerDto = new PartnerDto();
		partnerDto.setId(partner.getId());
		partnerDto.setUserDto_id(parse(partner.getUser_id()));
		partnerDto.setMoney(partner.getMoney());
		partnerDto.setDateCreated(partner.getDateCreated());
		
		return partnerDto;
	}
    
    
    
}
