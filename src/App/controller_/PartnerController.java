/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.controller_;

import App.controller.validator.GuestValidator;
import App.controller.validator.PersonValidator;
import App.controller.validator.UserValidator;
import App.service.Service;
import app.dto.PersonDto;
import app.dto.UserDto;

/**
 *
 * @author USUARIO
 */
public class PartnerController implements ControllerInterface {

    private GuestValidator guestValidator;
    private static final String MENU = "ingrese la opcion que desea ejecutar: \n 1.crear invitado. \n 2.agregar fondos \n 3. ver historial de facturas \n4cerrar sesion";
    private PersonValidator personValidator;
    private UserValidator userValidator;

    public PartnerController() {
        this.guestValidator = new GuestValidator();
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
            // Aquí podrías agregar el código para manejar la entrada del usuario y la lógica del menú.
            return true; // Devuelve true para mantener la sesión activa, false para terminarla.
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
                //this.(x); nose  como ponerlo, preguntar en clase
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
        personValidator.validName(name);
        System.out.println("Ingrese la cédula");
        long cedula = personValidator.validDocument(Utils.getReader().nextLine());
        System.out.println("ingrese el número de celular");
        long celPhone = personValidator.validAge(Utils.getReader().nextLine());
        System.out.println("ingrese el usuario del invitado");
        String userName = Utils.getReader().nextLine();
        userValidator.validUserName(userName);
        System.out.println("ingrese la contraseña del invitado");
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
        userDto.setRol("Guest");
    }

}
