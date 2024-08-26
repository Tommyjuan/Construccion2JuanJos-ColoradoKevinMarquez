package app;

import App.config.MYSQLConnection;
import App.controller_.ControllerInterface;
import App.controller_.LoginController;

public class App {

    public static void main(String[] args) {

        ControllerInterface controller = new LoginController();
        try {
            controller.session();
            MYSQLConnection.getConnection();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
