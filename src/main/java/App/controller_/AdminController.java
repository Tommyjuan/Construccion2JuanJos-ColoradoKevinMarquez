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

    private static final String MENU = "ingrese la opcion que desea ejecutar: \n 1. Para crear socio \n 2. Ver facturas (Socios,Invitados)  \n 3. Generar lista de vips\n 4. Para cerrar sesion\n";
    private PersonValidator PersonValidator;
    private UserValidator userValidator;
    private AdminService service;
    private PartnerValidator partnerValidator;
    private final PersonValidator personValidator;

    public AdminController() {
        this.personValidator = new PersonValidator();
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
                //this.invoiceHistory(); aun no se crea factura, pendiente
                return true;
            }
            case "3": {
                System.out.println("se ha cerrado sesion");
                return false;
            }
            case "4": {
                System.out.println("se ha cerrado sesion");
                return false;
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
        personValidator.validateName(name);
        System.out.println("ingrese la cedula");
        long document = personValidator.validateDocument(Utils.getReader().nextLine());
        System.out.println("ingrese el numero de celular");
        long celPhone = personValidator.validateCelPhone(Utils.getReader().nextLine());
        System.out.println("ingrese el usuario del socio");
        String userName = Utils.getReader().nextLine();
        userValidator.validateUserName(userName);
        System.out.println("ingrese la contraseña ");
        String password = Utils.getReader().nextLine();
        userValidator.validateUserName(password);
        System.out.println("Ingrese la cantidad de dinero de socio (50.000 min - 1.000.000 max)");
        double money = partnerValidator.validateMoney(Utils.getReader().nextLine());
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
        partnerDto.setMoney(money);
        partnerDto.setDateCreated(new Timestamp(System.currentTimeMillis()));
        if (money < 1000000) {
            partnerDto.setType("regular");
        } else {
            partnerDto.setType("Vip");
        }

        System.out.println("se ha creado el usuario exitosamente ");
        System.out.println("Tipo de socio: " + partnerDto.getType());
        System.out.println("Y se creo en el momento " + partnerDto.getDateCreated());
        this.service.createPartner(partnerDto);
    }

}
