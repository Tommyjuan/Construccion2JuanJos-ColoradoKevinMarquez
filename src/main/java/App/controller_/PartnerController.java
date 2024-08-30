package App.controller_;

import App.controller.validator.PartnerValidator;
import App.controller.validator.PersonValidator;
import App.controller.validator.UserValidator;
import App.service_interface.PartnerService;
import app.dto.GuestDto;
import app.service.Service;
import app.dto.PersonDto;
import app.dto.UserDto;

public class PartnerController implements ControllerInterface {

    private PartnerValidator partnerValidator;
    private static final String MENU = "ingrese la opcion : \n 1.crear invitado. \n 2.dar de baja \n 3.cerrar sesion \n";

    private PersonValidator personValidator;
    private UserValidator userValidator;
    private PartnerService service;

    public PartnerController() {
        this.partnerValidator = new PartnerValidator();
        this.personValidator = new PersonValidator();
        this.userValidator = new UserValidator();
        this.service=new Service();
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
            String option = Utils.getReader().nextLine();
            return options(option); // Llama al método options para determinar si debe continuar la sesión
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Finaliza la sesión si ocurre una excepción
        }
    }

    private boolean options(String option) throws Exception {
        switch (option) {
            case "1": {
                this.createGuest();
                return true;
            }
            case "2": {
                this.deletePartner();
                return true;
            }
            case "3": {
                System.out.println("se ha cerrado sesión");
                return false;
            }
            default: {
                System.out.println("ingrese una opción válida");
                return true;
            }
        }
    }

    public void createGuest() throws Exception {
        System.out.println("Ingrese el nombre del invitado");
        String name = Utils.getReader().nextLine();
        personValidator.validateName(name);

        System.out.println("Ingrese la cédula");
        long document = personValidator.validateDocument(Utils.getReader().nextLine());

        System.out.println("Ingrese el número de celular (mínimo 10 dígitos)");
        String celPhoneInput;
        long celPhone;

        while (true) {
            celPhoneInput = Utils.getReader().nextLine();
            if (celPhoneInput.matches("\\d{10,}")) { // Verifica que el input tenga al menos 10 dígitos
                celPhone = Long.parseLong(celPhoneInput);
                break;
            } else {
                System.out.println("El número de celular debe tener al menos 10 dígitos. Inténtelo nuevamente:");
            }
        }

        System.out.println("Ingrese el usuario del invitado");
        String userName = Utils.getReader().nextLine();
        userValidator.validateUserName(userName);

        System.out.println("Ingrese la contraseña del invitado");
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
        userDto.setRol("guest");
        GuestDto guestDto = new GuestDto();
        guestDto.setUserId(userDto);
        this.service.createGuest(guestDto);
    }


    
    public void deletePartner() throws Exception {
        this.service.deletePartner();

    }

}
