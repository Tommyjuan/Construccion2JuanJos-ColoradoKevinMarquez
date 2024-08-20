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
