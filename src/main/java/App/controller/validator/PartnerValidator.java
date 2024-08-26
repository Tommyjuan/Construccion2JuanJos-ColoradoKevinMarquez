package App.controller.validator;

/**
 *
 * @author USUARIO
 */
public class PartnerValidator extends CommonsValidator {

    public PartnerValidator() {
        super();
    }

    public double validateMoney(String money) throws Exception {
        return super.isValidDouble("el monto de dinero", money);
    }

}
