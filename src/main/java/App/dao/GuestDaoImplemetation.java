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

// Implementación de la interfaz GuestDao
public class GuestDaoImplemetation implements GuestDao {

    // Método para crear un nuevo invitado en la base de datos
    @Override
    public void createGuest(GuestDto guestDto) throws Exception {
        // Convierte el GuestDto a un objeto Guest
        Guest guest = Helper.parse(guestDto);

        // Consulta SQL para insertar un nuevo registro en la tabla 'guest'
        String query = "INSERT INTO guest(USERID,PARTNERID,STATUS) VALUES (?, ?, ?)";
        // Prepara la declaración SQL
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        // Establece los valores de los parámetros en la consulta
        preparedStatement.setLong(1, guest.getUserId().getId()); // ID del usuario
        preparedStatement.setLong(2, guest.getPartnerId().getId()); // ID del socio
        preparedStatement.setString(3, guest.getStatus()); // Estado del invitado
        // Ejecuta la consulta
        preparedStatement.execute();
        // Cierra la declaración preparada
        preparedStatement.close();
    }

    // Método para eliminar un invitado (no implementado)
    @Override
    public void deleteGuest(GuestDto guestDto) throws Exception {
        // No se proporciona implementación en este método
    }

    // Método para verificar si un invitado existe en la base de datos basado en el ID del usuario
    @Override
    public GuestDto existByGuest(UserDto userDto) throws Exception {
        // Consulta SQL para seleccionar un invitado basado en el ID del usuario
        String query = "SELECT ID,USERID,PARTNERID,STATUS FROM GUEST WHERE USERID = ?";
        // Prepara la declaración SQL
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        // Establece el valor del parámetro en la consulta
        preparedStatement.setLong(1, userDto.getId());
        // Ejecuta la consulta y obtiene el resultado
        ResultSet resulSet = preparedStatement.executeQuery();

        // Si se encuentra un registro en el ResultSet
        if (resulSet.next()) {
            // Crea un objeto Guest y establece sus propiedades a partir del ResultSet
            Guest guest = new Guest();
            guest.setId(resulSet.getLong("ID"));
            guest.setStatus(resulSet.getString("STATUS"));

            // Crea y configura el objeto User asociado al invitado
            User user = new User();
            user.setId(resulSet.getLong("USERID"));
            guest.setUserId(user);

            // Crea y configura el objeto Partner asociado al invitado
            Partner partner = new Partner();
            partner.setId(resulSet.getLong("PARTNERID"));
            guest.setPartnerId(partner);

            // Cierra el ResultSet y la declaración preparada
            resulSet.close();
            preparedStatement.close();
            // Convierte el objeto Guest a GuestDto y lo devuelve
            return Helper.parse(guest);
        }
        // Si no se encuentra ningún registro, cierra el ResultSet y devuelve null
        resulSet.close();
        preparedStatement.close();
        return null;
    }

    // Método para cambiar el estado de un invitado
    @Override
    public void changeStatus(GuestDto guestDto) throws Exception {
        // Consulta SQL para actualizar el estado del invitado
        String query = "UPDATE GUEST SET STATUS = ? WHERE USERID = ?";
        // Prepara la declaración SQL
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        // Establece los valores de los parámetros en la consulta
        preparedStatement.setString(1, guestDto.getStatus()); // Nuevo estado
        preparedStatement.setLong(2, guestDto.getUserId().getId()); // ID del usuario
        // Ejecuta la actualización
        preparedStatement.executeUpdate();
        // Cierra la declaración preparada
        preparedStatement.close();
    }

    // Método para obtener un invitado por su ID
    @Override
    public GuestDto getGuestById(long guestId) throws Exception {
        // Consulta SQL para seleccionar un invitado basado en su ID
        String query = "SELECT ID,USERID,PARTNERID,STATUS FROM GUEST WHERE ID = ?";
        // Prepara la declaración SQL
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        // Establece el valor del parámetro en la consulta
        preparedStatement.setLong(1, guestId);
        // Ejecuta la consulta y obtiene el resultado
        ResultSet resultSet = preparedStatement.executeQuery();

        // Si se encuentra un registro en el ResultSet
        if (resultSet.next()) {
            // Crea un objeto Guest y establece sus propiedades a partir del ResultSet
            Guest guest = new Guest();
            guest.setId(resultSet.getLong("ID"));
            guest.setStatus(resultSet.getString("STATUS"));

            // Crea y configura el objeto User asociado al invitado
            User user = new User();
            user.setId(resultSet.getLong("USERID"));
            guest.setUserId(user);

            // Crea y configura el objeto Partner asociado al invitado
            Partner partner = new Partner();
            partner.setId(resultSet.getLong("PARTNERID"));
            guest.setPartnerId(partner);

            // Cierra el ResultSet y devuelve el GuestDto convertido
            resultSet.close();
            return Helper.parse(guest);
        } else {
            // Si no se encuentra ningún registro, cierra el ResultSet y devuelve null
            resultSet.close();
            return null;
        }
    }
}
