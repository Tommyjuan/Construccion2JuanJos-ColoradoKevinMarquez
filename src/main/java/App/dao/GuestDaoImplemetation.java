package App.dao;

import App.config.MYSQLConnection;
import App.dao_interface.GuestDao;
import app.dto.GuestDto;
import App.helpers.Helper;
import app.dto.UserDto;
import app.model.Guest;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GuestDaoImplemetation implements GuestDao {

    public void createGuest(GuestDto guestDto) throws Exception {
        Guest guest = Helper.parse(guestDto);
        String query = "INSERT INTO guest(USERID,PARTNERID,STATUS) VALUES (?, ?,?)";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, guest.getUserId().getId());
        preparedStatement.setLong(2, guest.getPartnerId().getId());
        preparedStatement.setString(3, guest.getStatus());
        preparedStatement.execute();
        preparedStatement.close();
    }

}
