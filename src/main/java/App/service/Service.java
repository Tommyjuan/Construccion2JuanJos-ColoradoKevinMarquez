package app.service;

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
    public static UserDto user;

    public Service() {
        this.userDao = new UserDaoImplementation();
        this.personDao = new PersonDaoImplementation();
        this.partnerDao = new PartnerDaoImplemetation();
        this.guestDao = new GuestDaoImplemetation();
    }

    @Override
    public void createGuest(UserDto userDto) throws Exception {
        this.createUser(userDto);
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
    
        public void createGuest(GuestDto guestDto) throws Exception {
        this.createUser(guestDto.getUserId());
        UserDto userDto = userDao.findByUserName(guestDto.getUserId());
        guestDto.setUserId(userDto);
        PartnerDto partnerDto = partnerDao.existByPartner(user);
        guestDto.setPartnerId(partnerDto);
        try {
            this.guestDao.createGuets(guestDto);
        } catch (SQLException e) {
            this.personDao.deletePerson(userDto.getPersonId());
      
           throw new Exception("error al crear el invitador",e);
        }
    }
}