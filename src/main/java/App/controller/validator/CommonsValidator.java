
package App.controller.validator;


public abstract class CommonsValidator {

    public CommonsValidator() {
        
    }   //Valida que el valor de una cadena no esté vacío.
 	public void isValidString(String element, String value) throws Exception {
		if (value.equals("")) {
			throw new Exception(element + " no puede ser un valor vacio");
		}
	}
        //Valida que el valor de una cadena pueda ser convertido a un entero.
	public int isValidInteger(String element, String value) throws Exception {
		isValidString(element, value);
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			throw new Exception(element + " debe ser un valor valido");
		}
	}
	//Valida que el valor de una cadena pueda ser convertido a un long.
	public long isValidLong(String element, String value) throws Exception {
		isValidString(element, value);
		try {
			return Long.parseLong(value);
		} catch (Exception e) {
			throw new Exception(element + " debe ser un valor valido");
		}
	}
	//Valida que el valor de una cadena pueda ser convertido a un double.
	public double isValidDouble(String element, String value) throws Exception {
		isValidString(element, value);
		try {
			return Double.parseDouble(value);
		} catch (Exception e) {
			throw new Exception(element + " debe ser un valor valido");
		}
	}
}
