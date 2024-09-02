package App.controller_;

import App.controller.validator.GuestValidator;
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
    private static final String MENU = "ingrese la opcion : \n 1.crear invitado. \n 2.dar de baja \n 3. activar/desactivar invitado \n 4. solicitud VIP \n 5. consignar al monto \n 6.cerrar sesion \n";

    private PersonValidator personValidator;
    private UserValidator userValidator;
    private GuestValidator guestValidator;
    private PartnerService service;

    public PartnerController() {
        this.partnerValidator = new PartnerValidator();
        this.personValidator = new PersonValidator();
        this.userValidator = new UserValidator();
        this.service = new Service();
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

   // Método que maneja las opciones seleccionadas por el usuario
private boolean options(String option) throws Exception {
    switch (option) {
        case "1": {
            this.createGuest(); // Llama al método para crear un nuevo invitado
            return true; // Continúa mostrando el menú después de crear el invitado
        }
        case "2": {
            this.deletePartner(); // Llama al método para eliminar un socio
            return true; // Continúa mostrando el menú después de eliminar el socio
        }
        case "3": {
            this.changeStatus(); // Llama al método para cambiar el estado de un invitado
            return true; // Continúa mostrando el menú después de cambiar el estado
        }
        case "4": {
            this.service.promocionV(); // Llama al método de servicio para una promoción (el método está en el servicio)
            return true; // Continúa mostrando el menú después de la promoción
        }
        case "5": {
            this.newFound(); // Llama al método para actualizar el dinero (el método está en el servicio)
            return true; // Continúa mostrando el menú después de actualizar el dinero
        }
        case "6": {
            System.out.println("se ha cerrado sesión"); // Mensaje de cierre de sesión
            return false; // Termina la sesión y cierra el menú
        }
        default: {
            System.out.println("ingrese una opción válida"); // Mensaje de opción inválida
            return true; // Continúa mostrando el menú
        }
    }
}

// Método para crear un nuevo invitado
public void createGuest() throws Exception {
    System.out.println("Ingrese el nombre del invitado"); 
    String name = Utils.getReader().nextLine(); 
    personValidator.validateName(name); // Valida el nombre del invitado

    System.out.println("Ingrese la cédula"); 
    long document = personValidator.validateDocument(Utils.getReader().nextLine()); // Valida y convierte la cédula a Long

    System.out.println("Ingrese el número de celular (mínimo 10 dígitos)"); 
    String celPhoneInput;
    long celPhone;

    // Ciclo para asegurar que el número de celular tenga al menos 10 dígitos
    while (true) {
        celPhoneInput = Utils.getReader().nextLine();
        if (celPhoneInput.matches("\\d{10,}")) { // Verifica que el input tenga al menos 10 dígitos
            celPhone = Long.parseLong(celPhoneInput);
            break; // Sale del ciclo si el número es válido
        } else {
            System.out.println("El número de celular debe tener al menos 10 dígitos. Inténtelo nuevamente:");
        }
    }

    System.out.println("Ingrese el usuario del invitado"); 
    String userName = Utils.getReader().nextLine();
    userValidator.validateUserName(userName); // Valida el nombre de usuario

    System.out.println("Ingrese la contraseña del invitado");
    String password = Utils.getReader().nextLine();
    userValidator.validatePassword(password); // Valida la contraseña

    // Crea y configura un nuevo objeto PersonDto
    PersonDto personDto = new PersonDto();
    personDto.setName(name);
    personDto.setDocument(document);
    personDto.setCelPhone(celPhone);

    // Crea y configura un nuevo objeto UserDto
    UserDto userDto = new UserDto();
    userDto.setPersonId(personDto);
    userDto.setUserName(userName);
    userDto.setPassword(password);
    userDto.setRol("guest"); // Establece el rol como "guest"

    // Crea y configura un nuevo objeto GuestDto
    GuestDto guestDto = new GuestDto();
    guestDto.setUserId(userDto);

    // Llama al servicio para crear el nuevo invitado
    this.service.createGuest(guestDto);
}

// Método para eliminar un socio
public void deletePartner() throws Exception {
    this.service.deletePartner(); // Llama al método del servicio para eliminar el socio
}

// Método para cambiar el estado de un invitado
public void changeStatus() throws Exception {
    System.out.println("Ingrese el ID del invitado:"); 
    long guestId = Long.parseLong(Utils.getReader().nextLine()); // Lee y convierte el ID a tipo long
    GuestDto guestDto = service.getGuestById(guestId); // Obtiene el objeto GuestDto correspondiente al ID

    if (guestDto == null) {
        System.out.println("-Invitado no existe-"); 
        return; // Sale del método
    }

    System.out.println("Ingresar nuevo estado (activo o inactivo): "); 
    String status = Utils.getReader().nextLine(); // Lee el nuevo estado

    guestDto.setStatus(status); // Establece el nuevo estado en el objeto GuestDto
    service.updateStatus(guestDto); // Llama al servicio para actualizar el estado del invitado
    System.out.println("Estado actualizado exitosamente."); 
}

// Método para actualizar el dinero (llamado desde el servicio)
private void newFound() throws Exception {
    this.service.updateMoney(); // Llama al método del servicio para actualizar el dinero
}

// Método para realizar una promoción (llamado desde el servicio)
private void promocionV() throws Exception {
    this.service.promocionV(); // Llama al método del servicio para realizar la promoción
  }
}