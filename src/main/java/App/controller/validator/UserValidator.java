package App.controller.validator;

public class UserValidator extends CommonsValidator {

    public UserValidator() {
        super();
    }

    public void validatePassword(String password) throws Exception {
        super.isValidString(" la contraseña de usuario ", password);
    }

    public void validateUserName(String userName) throws Exception {
        super.isValidString("el nombre de usuario ", userName);
    }

    public void validateRole(String role) throws Exception {
        super.isValidString("el rol de usuario ", role);
    }


}
