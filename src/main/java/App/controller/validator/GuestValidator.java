/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.controller.validator;

/**
 *
 * @author USUARIO
 */
public class GuestValidator extends CommonsValidator {
    
    	public void validUserName(String userName) throws Exception {
		super.isValidString("el nombre de usuario ", userName);
	}
    
}
