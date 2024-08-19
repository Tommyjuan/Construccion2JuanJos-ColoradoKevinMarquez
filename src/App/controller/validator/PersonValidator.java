
package App.controller.validator;

public class PersonValidator extends CommonsValidator {

        public PersonValidator() {
            super();  
        }
    
        public void validateName(String name) throws Exception {
             super.isValidString("el nombre de la persona: ", name);
        }

        public long validateCedula(String cedula) throws Exception {
            return super.isValidLong("la cédula de la persona: ", cedula);
        }

        public long validateCelPhone(String celPhone) throws Exception {
            return super.isValidLong("el número de teléfono de la persona: ", celPhone);
        }
    
    
}
