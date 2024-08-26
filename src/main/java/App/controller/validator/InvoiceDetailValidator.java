package App.controller.validator;

public class InvoiceDetailValidator extends CommonsValidator {

    public InvoiceDetailValidator() {
        super();
    }

    public int validItem(String item) throws Exception {
        return super.isValidInteger("el ítem", item);
    }

    public void validDescription(String description) throws Exception {
        super.isValidString("la descripción", description);
    }

    public double validAmount(String amount) throws Exception {
        return super.isValidDouble("el monto", amount);
    }

}
