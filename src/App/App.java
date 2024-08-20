/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package App;
import App.config.MYSQLConnection;

public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MYSQLConnection controller = new MYSQLConnection();
		try {
			controller.getConnection();
			//MYSQLConnection.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
    
}
