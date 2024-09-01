package App.controller_;

import App.controller.validator.PartnerValidator;
import App.controller.validator.PersonValidator;
import App.controller.validator.UserValidator;
import App.service_interface.AdminService;
import app.dto.InvoiceDto;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.dto.UserDto;
import app.model.Invoice;
import app.service.Service;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Constructor de la clase AdminController. Inicializa los validadores de
 * persona y usuario.
 */
public class AdminController implements ControllerInterface {

    private static final String MENU = "ingrese la opcion: \n 1.crear socio   \n 2.cerrar sesion \n";
    private PersonValidator PersonValidator;
    private UserValidator userValidator;
    private AdminService service;
    private PartnerValidator partnerValidator;

    public AdminController() {
        this.PersonValidator = new PersonValidator();
        this.userValidator = new UserValidator();
        this.service = new Service();
        this.partnerValidator = new PartnerValidator();

    }

    @Override
    public void session() throws Exception {
        boolean session = true;
        while (session) {
            session = menu();
        }

    }

    private boolean menu() {
        try {
            System.out.println("bienvenido " + Service.user.getUserName());
            System.out.print(MENU);
            String option = Utils.getReader().nextLine();
            return options(option);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
        }
    }

    private boolean options(String option) throws Exception {
        switch (option) {
            case "1": {
                this.createPartner();
                return true;
            }
            case "2": {
                System.out.println("se ha cerrado sesion exitosamente");
                return true;
            }

            default: {
                System.out.println("ingrese una opcion valida");
                return true;
            }
        }
    }

    /**
     * Método para crear un nuevo socio.
     *
     * @throws Exception Si ocurre un error durante la validación o creación del
     * socio.
     */
    public void createPartner() throws Exception {
        System.out.println("Ingrese el nombre del socio");
        String name = Utils.getReader().nextLine();
        PersonValidator.validateName(name);
        System.out.println("ingrese la cedula");
        long document = PersonValidator.validateDocument(Utils.getReader().nextLine());
        System.out.println("ingrese el numero de celular");
        long celPhone = PersonValidator.validateCelPhone(Utils.getReader().nextLine());
        String celPhoneInput;

        while (true) {
            celPhoneInput = Utils.getReader().nextLine();
            if (celPhoneInput.matches("\\d{10,}")) { // Verifica que el input tenga al menos 10 dígitos
                celPhone = Long.parseLong(celPhoneInput);
                break;
            } else {
                System.out.println("El número de celular debe tener al menos 10 dígitos. Inténtelo nuevamente:");
            }
        }

        System.out.println("ingrese el usuario del socio");
        String userName = Utils.getReader().nextLine();
        userValidator.validateUserName(userName);
        System.out.println("ingrese la contraseña ");
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
        userDto.setRol("partner");
        PartnerDto partnerDto = new PartnerDto();
        partnerDto.setUserDto_id(userDto);
        partnerDto.setMoney(50000);
        partnerDto.setDateCreated(new Timestamp(System.currentTimeMillis()));
        partnerDto.setType("regular");

        System.out.println("se ha creado el usuario exitosamente ");
        System.out.println("Tipo de socio: " + partnerDto.getType());
        System.out.println("Sus ingresos actuales son de:" + partnerDto.getMoney());
        System.out.println("fecha y hora de creacion:  " + partnerDto.getDateCreated());
        this.service.createPartner(partnerDto);
    }

}
