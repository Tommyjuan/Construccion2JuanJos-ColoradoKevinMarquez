package App.dao;

import App.config.MYSQLConnection;
import App.dao_interface.PartnerDao;
import app.dto.PartnerDto;
import app.dto.UserDto;
import App.helpers.Helper;
import app.model.Partner;
import app.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PartnerDaoImplemetation implements PartnerDao {

    @Override
    public void createPartner(PartnerDto partnerDto) throws Exception {
        Partner partner = Helper.parse(partnerDto);
        String query = "INSERT INTO PARTNER(USERID,AMOUNT,TYPE,CREATIONDATE) VALUES (?, ?,?,?)";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, partner.getUser_id().getId());
        preparedStatement.setDouble(2, partner.getMoney());
        preparedStatement.setString(3, partner.getType());
        preparedStatement.setTimestamp(4, partner.getDateCreated());
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void deletePartner(PartnerDto partnerDto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PartnerDto existByPartner(UserDto userDto) throws Exception {
        String query = "SELECT ID,USERID,AMOUNT,TYPE,CREATIONDATE FROM PARTNER WHERE USERID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, userDto.getId());
        ResultSet resulSet = preparedStatement.executeQuery();
        if (resulSet.next()) {
            Partner partner = new Partner();
            partner.setId(resulSet.getLong("ID"));
            partner.setMoney(resulSet.getDouble("AMOUNT"));
            partner.setType(resulSet.getString("TYPE"));
            partner.setDateCreated(resulSet.getTimestamp("CREATIONDATE"));
            User user = new User();
            user.setId(resulSet.getLong("USERID"));
            partner.setUser_id(user);
            resulSet.close();
            preparedStatement.close();
            return Helper.parse(partner);
        }
        resulSet.close();
        preparedStatement.close();
        return null;

    }

}
