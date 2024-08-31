package App.helpers;

import app.dto.GuestDto;
import app.model.Guest;

import app.dto.PersonDto;
import app.model.Person;

import app.dto.UserDto;
import app.model.User;

import app.model.Partner;
import app.dto.PartnerDto;

public abstract class Helper {

    // Son conversiones
    public static PersonDto parse(Person person) {

        if (person == null) {
            return null;
        }
        PersonDto personDto = new PersonDto();
        personDto.setId(person.getId());
        personDto.setDocument(person.getDocument());
        personDto.setName(person.getName());
        personDto.setCelPhone(person.getCelPhone());
        return personDto;
    }

    public static Person parse(PersonDto personDto) {

        if (personDto == null) {
            return null;
        }
        Person person = new Person();
        person.setId(personDto.getId());
        person.setDocument(personDto.getDocument());
        person.setName(personDto.getName());
        person.setCelPhone(personDto.getCelPhone());
        return person;
    }

    public static UserDto parse(User user) {

        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setPassword(user.getPassword());
        userDto.setPersonId(parse(user.getPersonId()));
        userDto.setRol(user.getRol());
        userDto.setUserName(user.getUserName());
        return userDto;
    }

    public static User parse(UserDto userDto) {

        if (userDto == null) {
            return null;
        }
        User user = new User();
        user.setId(userDto.getId());
        user.setPassword(userDto.getPassword());
        user.setPersonId(parse(userDto.getPersonId()));
        user.setRol(userDto.getRol());
        user.setUserName(userDto.getUserName());
        return user;
    }

    public static Partner parse(PartnerDto partnerDto) {

        if (partnerDto == null) {
            return null;
        }
        Partner partner = new Partner();
        partner.setId(partnerDto.getId());
        partner.setUser_id(parse(partnerDto.getUserDto_id()));
        partner.setMoney(partnerDto.getMoney());
        partner.setType(partnerDto.getType());
        partner.setDateCreated(partnerDto.getDateCreated());
        return partner;
    }

    public static PartnerDto parse(Partner partner) {

        if (partner == null) {
            return null;
        }
        PartnerDto partnerDto = new PartnerDto();
        partnerDto.setId(partner.getId());
        partnerDto.setUserDto_id(parse(partner.getUser_id()));
        partnerDto.setMoney(partner.getMoney());
        partnerDto.setDateCreated(partner.getDateCreated());

        return partnerDto;
    }

    public static Guest parse(GuestDto guestDto) {
        if (guestDto == null) {
            return null;
        }
        Guest guest = new Guest();
        guest.setId(guestDto.getId());
        guest.setUserId(parse(guestDto.getUserId()));
        guest.setPartnerId(parse(guestDto.getPartnerId()));
        guest.setStatus(guestDto.getStatus());

        return guest;
    }

    public static GuestDto parse(Guest guest) {
        if (guest == null) {
            return null;
        }
        GuestDto guestDto = new GuestDto();
        guestDto.setId(guest.getId());
        guestDto.setUserId(parse(guest.getUserId()));
        guestDto.setPartnerId(parse(guest.getPartnerId()));
        guestDto.setStatus(guest.getStatus());

        return guestDto;
    }

}
