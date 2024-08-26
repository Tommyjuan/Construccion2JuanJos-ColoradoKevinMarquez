package App.controller.validator;

public class GuestValidator extends CommonsValidator {

    public void validUserName(String userName) throws Exception {
        super.isValidString("el nombre de usuario ", userName);
    }

}
