package App.controller_;

import App.controller.validator.UserValidator;
import App.service_interface.LoginService;
import app.dto.UserDto;
import app.service.Service;
import java.util.HashMap;
import java.util.Map;

public class LoginController implements ControllerInterface {

    private UserValidator userValidator;
    private LoginService service;
    private static final String MENU = "ingrese la opcion: \n 1.iniciar sesion. \n 2.salir.   ";
    private Map<String, ControllerInterface> rol;

    public LoginController() {
        this.userValidator = new UserValidator();
        this.service = new Service();
        ControllerInterface adminController = new AdminController();
        ControllerInterface partnerController = new PartnerController();
        ControllerInterface guestController = new GuestController();
        this.rol = new HashMap<String, ControllerInterface>();
        rol.put("admin", adminController);
        rol.put("partner", partnerController);
        rol.put("guest", guestController);

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
            System.out.println(MENU);
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
                this.login();
                return true;
            }
            case "2": {
                System.out.println("se detiene el programa");;
                return false;
            }
            default: {
                System.out.println("ingrese una opcion valida");
                return true;
            }
        }
    }

    private void login() throws Exception {
        System.out.println("ingrese el usuario");
        String userName = Utils.getReader().nextLine();
        userValidator.validateUserName(userName);
        System.out.println("ingrese la contraseña");
        String password = Utils.getReader().nextLine();
        userValidator.validatePassword(password);
        UserDto userDto = new UserDto();
        userDto.setPassword(password);
        userDto.setUserName(userName);
        this.service.login(userDto);
        if (rol.get(userDto.getRol()) == null) {
            throw new Exception("Rol invalido");
        }
        rol.get(userDto.getRol()).session();

    }

}
