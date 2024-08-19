/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.controller_;

import App.controller.validator.PersonValidator;
import App.controller.validator.UserValidator;
import app.dto.PersonDto;
import app.dto.UserDto;

public class AdminController implements ControllerInterface {

    private PersonValidator personValidator;
    private UserValidator userValidator;

    public AdminController() {
        this.personValidator = new PersonValidator();
        this.userValidator = new UserValidator();
    }

    @Override
    public void session() throws Exception {
        //esto es una herramienta misteriosa que nos ayudara mas tarde.
    }

    public void createPartner() throws Exception {
        System.out.println("Ingrese el nombre del socio");
        String name = Utils.getReader().nextLine();
        personValidator.validateName(name);

        System.out.println("Ingrese la cédula");
        long cedula = personValidator.validateCedula(Utils.getReader().nextLine());

        System.out.println("Ingrese el número de celular");
        long celPhone = personValidator.validateCelPhone(Utils.getReader().nextLine());

        System.out.println("Ingrese el usuario del socio");
        String userName = Utils.getReader().nextLine();
        userValidator.validUserName(userName);

        System.out.println("Ingrese la contraseña");
        String password = Utils.getReader().nextLine();
        userValidator.validUserName(password);

        PersonDto personDto = new PersonDto();
        personDto.setName(name);
        personDto.setCedula(cedula);
        personDto.setCelPhone(celPhone);

        UserDto userDto = new UserDto();
        userDto.setPersonId(personDto);
        userDto.setUserName(userName);
        userDto.setPassword(password);
        userDto.setRol("Partner");

        System.out.println("Se ha creado el socio exitosamente");
    }

}

/*
import App.controller.validator.PersonValidator;
import App.controller.validator.UserValidator;
import App.controller.validator.PartnerValidator;
import app.dto.PersonDto;
import app.dto.UserDto;
import app.dto.PartnerDto;
import java.sql.Timestamp;

public class AdminController implements ControllerInterface {
    private PersonValidator personValidator;
    private UserValidator userValidator;
    private PartnerValidator partnerValidator;

    public AdminController() {
        this.personValidator = new PersonValidator();
        this.userValidator = new UserValidator();
        this.partnerValidator = new PartnerValidator();
    }

    @Override
    public void session() throws Exception {
//herramienta secreta?
    }

    public void createPartner()throws Exception{
          System.out.println("Ingrese el nombre del socio:");
        String name = Utils.getReader().nextLine();
        personValidator.validName(name);

        System.out.println("Ingrese la cédula del socio:");
        long document = personValidator.validDocument(Utils.getReader().nextLine());

        System.out.println("Ingrese el nombre de usuario del socio:");
        String userName = Utils.getReader().nextLine();
        userValidator.validUserName(userName);

        System.out.println("Ingrese la contraseña del socio:");
        String password = Utils.getReader().nextLine();
        userValidator.validPassword(password);

        System.out.println("Ingrese el monto de dinero inicial del socio:");
        double money = partnerValidator.validMoney(Utils.getReader().nextLine());

        System.out.println("Ingrese el tipo de socio:");
        String type = Utils.getReader().nextLine();
        partnerValidator.validType(type);

        // Creación de objetos DTO
        PersonDto personDto = new PersonDto();
        personDto.setName(name);
        personDto.setDocument(document);

        UserDto userDto = new UserDto();
        userDto.setPersonid(personDto);
        userDto.setUserName(userName);
        userDto.setPassword(password);

        PartnerDto partnerDto = new PartnerDto();
        partnerDto.setUser_id(userDto);
        partnerDto.setMoney(money);
        partnerDto.setType(type);
        partnerDto.setDateCreated(new Timestamp(System.currentTimeMillis()));

        System.out.println("El socio ha sido creado exitosamente.");
    }
    
}
 */
