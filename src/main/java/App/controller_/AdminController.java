/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.controller_;

import App.controller.validator.PersonValidator;
import App.controller.validator.UserValidator;
import app.dto.PersonDto;
import app.dto.UserDto;


   /**
     * Constructor de la clase AdminController.
     * Inicializa los validadores de persona y usuario.
     */
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
    
    /**
     * Método para crear un nuevo socio.
     *@throws Exception Si ocurre un error durante la validación o creación del socio.
     */ 
    public void createPartner() throws Exception {
        System.out.println("Ingrese el nombre del socio");
        String name = Utils.getReader().nextLine();
        personValidator.validateName(name);

        System.out.println("Ingrese la cédula");
        long cedula = personValidator.validateDocument(Utils.getReader().nextLine());

        System.out.println("Ingrese el número de celular");
        long celPhone = personValidator.validateCelPhone(Utils.getReader().nextLine());

        System.out.println("Ingrese el usuario del socio");
        String userName = Utils.getReader().nextLine();
        userValidator.validUserName(userName);

        System.out.println("Ingrese la contraseña");
        String password = Utils.getReader().nextLine();
        userValidator.validUserName(password);
        
        // Crea una instancia de PersonDto y establece los valores validados.
        PersonDto personDto = new PersonDto();
        personDto.setName(name);
        personDto.setDocument(cedula);
        personDto.setCelPhone(celPhone);

        UserDto userDto = new UserDto();
        userDto.setPersonId(personDto); // Asocia el PersonDto con el UserDto.
        userDto.setUserName(userName);
        userDto.setPassword(password);
        userDto.setRol("Partner"); // Establece el rol del usuario como "Partner".

        System.out.println("Se ha creado el socio exitosamente");
    }

}
