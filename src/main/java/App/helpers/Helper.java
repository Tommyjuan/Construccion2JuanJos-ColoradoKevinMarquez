
package App.helpers;

import app.dto.PersonDto;
import app.dto.UserDto;
import app.model.Person;
import app.model.User;
//estos imports si son correctos? sabiendo que el App esta en mayuscula?

public abstract class Helper {

    // Son conversiones
    public static PersonDto parse(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setId(person.getId());
        personDto.setDocument(person.getCedula());
        personDto.setName(person.getName());
        personDto.setCelPhone(person.getCelPhone());
        return personDto;
    }

    
    public static Person parse(PersonDto personDto) {
        Person person = new Person();
        person.setId(personDto.getId());
        person.setCedula(personDto.getDocument());
        person.setName(personDto.getName());
        person.setCelPhone(personDto.getCelPhone());
        return person;
    }


    public static UserDto parse(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setRol(user.getRol());
       
        PersonDto personDto = new PersonDto();
        personDto.setName(user.getPerson());
        userDto.setPersonId(personDto); 
        return userDto;
    }

 
    public static User parse(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUserName(userDto.getUserName());
        user.setRol(userDto.getRol());
        
        user.setPerson(userDto.getPersonId().getName()); 
        return user;
    }
}