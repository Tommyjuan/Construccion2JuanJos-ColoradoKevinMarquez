package App.controller_;

import app.service.Service;

public class GuestController implements ControllerInterface {

    public GuestController() {
    }

    private static final String MENU = "ingrese la opcion : \n 1. Pasar a socio \n 2.cerrar sesion \n";

    @Override
    public void session() throws Exception {
        boolean session = true;
        while (session) {
            session = menu();
        }
    }

    private boolean menu() {
        try {
            System.out.println("bienvenido " + Service.user.getUserName());
            System.out.print(MENU);
            String option = Utils.getReader().nextLine();
            return options(option);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
        }
    }

    private boolean options(String option) throws Exception {
        switch (option) {
            case "1": {
                System.out.println("");
                return true;
            }

            case "2": {
                System.out.println("se ha cerrado sesion");
                return false;
            }

            default: {
                System.out.println("ingrese una opcion valida");
                return true;
            }
        }
    }

}
