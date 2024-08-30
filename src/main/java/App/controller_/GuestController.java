package App.controller_;

import App.service_interface.GuestService;
import App.service_interface.PartnerService;
import app.dto.GuestDto;
import app.dto.PartnerDto;
import app.dto.UserDto;
import app.service.Service;
import java.sql.Timestamp;

public class GuestController implements ControllerInterface {
    private PartnerService servic;

    public GuestController() {
        this.servic=new Service();
        
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
                this.createPartner();
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

    public void createPartner() throws Exception {
        UserDto userDto = Service.user;
        userDto.setRol("partner");
        PartnerDto partnerDto = new PartnerDto();
        partnerDto.setUserDto_id(userDto);
        partnerDto.setMoney(50000);
        partnerDto.setDateCreated(new Timestamp(System.currentTimeMillis()));
        partnerDto.setType("regular");
        
        System.out.println("se ha creado el usuario exitosamente ");
        System.out.println("Tipo de socio: " + partnerDto.getType());
        System.out.println("Sus ingresos actuales son de:" + partnerDto.getMoney());
        System.out.println("Se creo el socio en el dia y hora: " + partnerDto.getDateCreated());
        this.servic.changeRole(partnerDto);
    }

}
