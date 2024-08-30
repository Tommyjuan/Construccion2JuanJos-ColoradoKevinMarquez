package App.dao;

import App.config.MYSQLConnection;
import App.dao_interface.GuestDao;
import app.dto.GuestDto;
import App.helpers.Helper;
import app.dto.UserDto;
import app.model.Guest;
import app.model.Partner;
import app.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    @Override
    public void deleteGuest(GuestDto guestDto) throws Exception {

    }

    @Override
    public GuestDto existByGuest(UserDto userDto) throws Exception {
         String query = "SELECT ID,USERID,PARTNERID,STATUS FROM GUEST WHERE USERID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, userDto.getId());
        ResultSet resulSet = preparedStatement.executeQuery();
        if (resulSet.next()) {
            Guest guest = new Guest();
            guest.setId(resulSet.getLong("ID"));
            guest.setStatus(resulSet.getString("STATUS"));
            User user = new User();
            user.setId(resulSet.getLong("USERID"));
            guest.setUserId(user);
            Partner partner=new Partner();
            partner.setId(resulSet.getLong("PARTNERID"));
            guest.setPartnerId(partner);
            resulSet.close(); 
            preparedStatement.close();
            return Helper.parse(guest);
        }
        resulSet.close();
        preparedStatement.close();
        return null;
    }
    
    

}
