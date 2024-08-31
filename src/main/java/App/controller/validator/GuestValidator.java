package App.controller.validator;

public class GuestValidator extends CommonsValidator {

    public void validUserName(String userName) throws Exception {
        super.isValidString("el nombre de usuario ", userName);
    }
    
    public long validGuestStatus(String id) throws Exception {
        return super.isValidLong("La id esquita ", id);
    }
    
}
