package app.service;

import App.controller_.Utils;
import App.dao.GuestDaoImplemetation;
import App.dao.PartnerDaoImplemetation;
import App.dao.PersonDaoImplementation;
import App.dao.UserDaoImplementation;
import App.dao_interface.GuestDao;
import App.dao_interface.InvoiceDao;
import App.dao_interface.InvoiceDetailDao;
import App.dao_interface.PartnerDao;
import App.dao_interface.PersonDao;
import App.dao_interface.UserDao;
import app.dto.GuestDto;
import app.dto.InvoiceDto;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.dto.UserDto;
import App.service_interface.AdminService;
import App.service_interface.LoginService;
import App.service_interface.PartnerService;
import java.sql.Date;
import java.sql.SQLException;

public class Service implements AdminService, LoginService, PartnerService {

    private UserDao userDao;
    private PersonDao personDao;
    private PartnerDao partnerDao;
    private InvoiceDetailDao invoiceDetailDao;
    private InvoiceDao invoiceDao;
    private GuestDao guestDao;
    private double newFound;
    public static UserDto user;

    public Service() {
        this.userDao = new UserDaoImplementation();
        this.personDao = new PersonDaoImplementation();
        this.partnerDao = new PartnerDaoImplemetation();
        this.guestDao = new GuestDaoImplemetation();
    }

    @Override
    public void login(UserDto userDto) throws Exception {
        UserDto validateDto = userDao.findByUserName(userDto);
        if (validateDto == null) {
            throw new Exception("no existe usuario registrado");
        }
        if (!userDto.getPassword().equals(validateDto.getPassword())) {
            throw new Exception("usuario o contraseña incorrecto");
        }
        userDto.setRol(validateDto.getRol());
        user = validateDto;

    }

    @Override

    public void logout() {
        user = null;
        System.out.println("se ha cerrado sesion");
    }

    private void createUser(UserDto userDto) throws Exception {
        this.createPerson(userDto.getPersonId());
        PersonDto personDto = personDao.findByDocument(userDto.getPersonId());
        userDto.setPersonId(personDto);
        if (this.userDao.existsByUserName(userDto)) {
            this.personDao.deletePerson(userDto.getPersonId());
            throw new Exception("ya existe un usuario con ese user name");
        }
        try {
            this.userDao.createUser(userDto);
        } catch (SQLException e) {
            this.personDao.deletePerson(userDto.getPersonId());
            throw new Exception("error al crear el usuario");
        }
    }

    private void createPerson(PersonDto personDto) throws Exception {

        this.personDao.createPerson(personDto);
    }

    @Override

    public void createPartner(PartnerDto partnerDto) throws Exception {
        checkVipLimit(partnerDto);
        this.createUser(partnerDto.getUserDto_id());
        UserDto userDto = userDao.findByUserName(partnerDto.getUserDto_id());
        partnerDto.setUserDto_id(userDto);
        try {
            this.partnerDao.createPartner(partnerDto);
        } catch (SQLException e) {
            this.personDao.deletePerson(userDto.getPersonId());
            throw new Exception("error al crear el usuario");

        }
    }

    @Override
    public void createGuest(GuestDto guestDto) throws Exception {
        this.createUser(guestDto.getUserId());
        UserDto userDto = userDao.findByUserName(guestDto.getUserId());
        guestDto.setUserId(userDto);
        PartnerDto partnerDto = partnerDao.existByPartner(user);
        partnerDto.setUserDto_id(user);
        guestDto.setStatus("Inactivo");
        guestDto.setPartnerId(partnerDto);
        try {
            this.guestDao.createGuest(guestDto);
        } catch (SQLException e) {
            //this.personDao.deletePerson(userDto.getPersonId());

            throw new Exception("error al crear el invitador", e);
        }
    }

    @Override
    public void changeRole(PartnerDto partnerDto) throws Exception {
        UserDto users = Service.user;
        GuestDto guestDto = this.guestDao.existByGuest(users);
        this.guestDao.deleteGuest(guestDto);
        UserDto userDto = userDao.findByUserName(users);
        partnerDto.setUserDto_id(userDto);
        userDto.setRol("partner");
        this.userDao.uptadeUserRole(userDto);
        try {
            this.partnerDao.createPartner(partnerDto);
            System.out.println("Se ha convertido el Guest en Partner exitosamente.");

        } catch (SQLException e) {
            System.out.println("El usuario no existe en la base de datos.");
        }

    }

    @Override
    public void deletePartner() throws Exception {

        UserDto users = Service.user;
        try {
            PartnerDto partnerDto = this.partnerDao.existByPartner(users);
            UserDto userDto = this.userDao.findByUserName(users);
            this.userDao.existsByUserName(userDto);

            this.partnerDao.deletePartner(partnerDto);
            this.userDao.deleteUser(userDto);
            this.personDao.deletePerson(userDto.getPersonId());
            System.out.println("Su cuenta ha sido eliminada exitosamente.");

        } catch (SQLException e) {
            System.out.println("El usuario no existe en la base de datos.");
        }
    }

    public void updateMoney() throws Exception {
        UserDto users = Service.user;
        PartnerDto partnerDto = partnerDao.existByPartner(users);
        System.out.println("su tipo es :" + partnerDto.getType());
        System.out.println("su tipo es :" + users.getUserName());
        System.out.println("dinero actual:" + partnerDto.getMoney());
        System.out.println("Cuanto desea ingresar : ");
        double getMoney = Double.parseDouble(Utils.getReader().nextLine());

        newFound = partnerDto.getMoney() + getMoney;
        if ("regular".equals(partnerDto.getType()) && newFound >= 1000000) {

            System.out.println("el limite de ingresos es de 1000000");
            newFound = newFound - getMoney;
        }

        if ("vip".equals(partnerDto.getType()) && newFound >= 5000000) {
            System.out.println("el limite de ingresos es de 5000000");
            newFound = newFound - getMoney;

        }

        partnerDto.setMoney(newFound);
        this.partnerDao.getMoney(newFound);
        this.partnerDao.updateMoney(partnerDto);
        System.out.println("actualizacion de dinero total: " + partnerDto.getMoney());

    }

    @Override
    public GuestDto getGuestById(long guestId) throws Exception {
        return guestDao.getGuestById(guestId);
    }

    @Override
    public void updateStatus(GuestDto guestDto) throws Exception {
        guestDao.changeStatus(guestDto);
    }


    @Override
    public void checkVipLimit(PartnerDto partnerDto) throws Exception {

        if ("vip".equals(partnerDto.getType())) {
            int vipCount = this.partnerDao.countVip();
            final int vip = 5;
            if (vipCount >= vip) {
                throw new Exception("El número máximo de socios VIP ya ha sido alcanzado.");
            }
        }
    }

    @Override
    public void guestLimit(PartnerDto partnerDto) throws Exception {
        if ("regular".equals(partnerDto.getType())) {
            int guestCount = this.guestDao.countGuests(partnerDto.getId());
            final int guest = 3;
            if (guestCount >= guest) {
                throw new Exception("El numero maximo de invitados a sido alcanzado.");
            }
        }
    }

}
