package App.controller_;

import App.service_interface.GuestService;
import App.service_interface.PartnerService;
import app.dto.GuestDto;
import app.dto.PartnerDto;
import app.dto.UserDto;
import app.service.Service;
import java.sql.Timestamp;

public class GuestController implements ControllerInterface {
    private PartnerService servic; // Instancia del servicio para manejar operaciones relacionadas con socios
    
    // Constructor de la clase que inicializa el servicio

    public GuestController() {
        this.servic=new Service(); // Se crea una nueva instancia del servicio
        
    }

    private static final String MENU = "ingrese la opcion : \n 1. Pasar a socio \n 2.cerrar sesion \n";
    
    // Método que controla la sesión del usuario
    @Override
    public void session() throws Exception {
        boolean session = true; // Variable que controla si la sesión está activa
        while (session) {
            session = menu(); // Llama al método menu() y actualiza el estado de la sesión
        }
    }
    
    // Llama al método menu() y actualiza el estado de la sesión
    private boolean menu() {
        try {
            // Imprime el nombre de usuario actual y el menú de opciones
            System.out.println("bienvenido " + Service.user.getUserName());
            System.out.print(MENU);
            String option = Utils.getReader().nextLine(); // Lee la opción ingresada por el usuario
            return options(option); // Llama al método options() para procesar la opción

        // Captura y muestra cualquier excepción ocurrida y continúa mostrando el menú en caso de error.
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
        }
    }
    // Método que maneja la opción seleccionada por el usuario
    private boolean options(String option) throws Exception {
        switch (option) {
            case "1": {
                this.createPartner(); // Llama al método para crear un nuevo socio
                return true; // Continúa mostrando el menú después de crear el socio
            }

            case "2": {
                System.out.println("se ha cerrado sesion");
                return false;
            }

            default: {
                System.out.println("ingrese una opcion valida");
                return true;
            }
        }
    }

    // Método para crear un nuevo socio
    public void createPartner() throws Exception {
        UserDto userDto = Service.user; // Obtiene el usuario actual
        userDto.setRol("partner"); // Establece el rol del usuario como "socio"
        PartnerDto partnerDto = new PartnerDto(); // Crea una nueva instancia de PartnerDto
        partnerDto.setUserDto_id(userDto); // Asocia el usuario al nuevo socio
        partnerDto.setMoney(50000); // Establece la cantidad de dinero inicial del socio
        partnerDto.setDateCreated(new Timestamp(System.currentTimeMillis())); // Establece la fecha de creación
        partnerDto.setType("regular"); // Establece el tipo de socio como "regular"
        
        
        System.out.println("se ha creado el usuario exitosamente ");
        System.out.println("Tipo de socio: " + partnerDto.getType());
        System.out.println("Sus ingresos actuales son de:" + partnerDto.getMoney());
        System.out.println("Se creo el socio en el dia y hora: " + partnerDto.getDateCreated());
        this.servic.changeRole(partnerDto); // Llama al método del servicio para cambiar el rol del usuario a socio
    }

}
