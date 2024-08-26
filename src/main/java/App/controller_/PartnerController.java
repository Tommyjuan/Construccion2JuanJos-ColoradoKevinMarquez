/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.controller_;

import App.controller.validator.PartnerValidator;
import App.controller.validator.PersonValidator;
import App.controller.validator.UserValidator;
import app.service.Service;
import app.dto.PersonDto;
import app.dto.UserDto;

public class PartnerController implements ControllerInterface {

    private PartnerValidator partnerValidator;
    private static final String MENU = "ingrese la opcion que desea ejecutar: \n 1. para crear invitado. \n 2. para agregar fondos. \n 3.para gastar en x cosa . \n 4. para ver historial de facturas";
    private PersonValidator personValidator;
    private UserValidator userValidator;

    public PartnerController() {
        this.partnerValidator = new PartnerValidator();
        this.personValidator = new PersonValidator();
        this.userValidator = new UserValidator();
    }

    @Override
    public void session() throws Exception {
        boolean session = true;
        while (session) {
            session = partnerSession();
        }
    }

    private boolean partnerSession() {
        try {
            System.out.println("bienvenido " + Service.user.getUserName());
            System.out.print(MENU);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean options(String option) throws Exception {
        switch (option) {
            case "1": {
                this.createGuest();
                return true;
            }
            case "2": {
                this.newFounds();
                return true;
            }
            case "3": {
                System.out.println("se ha cerrado sesión");
                return false;
            }
            case "4": {
                System.out.println("se ha cerrado sesión");
                return false;
            }
            default: {
                System.out.println("ingrese una opción válida");
                return true;
            }
        }
    }

    public void createGuest() throws Exception {//preguntar a camilo
        System.out.println("Ingrese el nombre del invitado");
        String name = Utils.getReader().nextLine();
        personValidator.validateName(name);
        System.out.println("Ingrese la cédula");
        long document = personValidator.validateDocument(Utils.getReader().nextLine());
        System.out.println("ingrese el número de celular");
        long celPhone = personValidator.validateCelPhone(Utils.getReader().nextLine());
        System.out.println("ingrese el usuario del invitado");
        String userName = Utils.getReader().nextLine();
        userValidator.validateUserName(userName);
        System.out.println("ingrese la contraseña del invitado");
        String password = Utils.getReader().nextLine();
        userValidator.validateUserName(password);

        PersonDto personDto = new PersonDto();
        personDto.setName(name);
        personDto.setDocument(document);
        personDto.setCelPhone(celPhone);

        UserDto userDto = new UserDto();
        userDto.setPersonId(personDto);
        userDto.setUserName(userName);
        userDto.setPassword(password);
        userDto.setRol("Guest");
    }

    private void newFounds() throws Exception {
        System.out.println("Cuanto quiere ingresar?");
        String money = Utils.getReader().nextLine();
        partnerValidator.validateMoney(money);
    }
}
