package App.controller_;

import App.controller.validator.UserValidator;
import App.service_interface.LoginService;
import app.dto.UserDto;
import app.service.Service;
import java.util.HashMap;
import java.util.Map;

// Clase LoginController que implementa la interfaz ControllerInterface
public class LoginController implements ControllerInterface {

    private UserValidator userValidator; // Instancia del validador de usuarios
    private LoginService service; // Instancia del servicio de inicio de sesión
    private static final String MENU = "ingrese la opcion: \n 1.iniciar sesion. \n 2.salir.   ";
    private Map<String, ControllerInterface> rol; // Mapa para asociar roles con controladores


    public LoginController() {
        // Inicialización de las instancias de validador y servicio
        this.userValidator = new UserValidator();
        this.service = new Service();
        
        // Creación de instancias de los controladores para diferentes roles
        ControllerInterface adminController = new AdminController();
        ControllerInterface partnerController = new PartnerController();
        ControllerInterface guestController = new GuestController();
        
         // Inicialización del mapa que asocia cada rol con su controlador correspondiente
        this.rol = new HashMap<String, ControllerInterface>();
        rol.put("admin", adminController);
        rol.put("partner", partnerController);
        rol.put("guest", guestController);

    }
    // Método que controla la sesión del usuario
    @Override
    public void session() throws Exception {
        boolean session = true; // Variable que controla si la sesión está activa
        while (session) {
            session = menu(); // Llama al método menu() y actualiza el estado de la sesión
        }

    }
    
    // Variable que controla si la sesión está activa
    private boolean menu() {
        try {
            System.out.println(MENU);
            String option = Utils.getReader().nextLine(); // Lee la opción ingresada por el usuario
            return options(option); // Llama al método options() para procesar la opción
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
        }
    }
    
    // Método que maneja la opción seleccionada por el usuario
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
    
    // Método para realizar el inicio de sesión
    private void login() throws Exception {
        System.out.println("ingrese el usuario");
        String userName = Utils.getReader().nextLine();
        userValidator.validateUserName(userName);
        
        System.out.println("ingrese la contraseña");
        String password = Utils.getReader().nextLine();
        userValidator.validatePassword(password);
        
        // Crea un nuevo objeto UserDto con los datos del usuario
        UserDto userDto = new UserDto();
        userDto.setPassword(password);
        userDto.setUserName(userName);
        
        // Llama al método de servicio para intentar iniciar sesión
        this.service.login(userDto);
        // Verifica si el rol del usuario es válido y llama al controlador correspondiente
        if (rol.get(userDto.getRol()) == null) {
            throw new Exception("Rol invalido");
        }
        rol.get(userDto.getRol()).session();  // Llama al método session() del controlador correspondiente al rol del usuario

    }

}
