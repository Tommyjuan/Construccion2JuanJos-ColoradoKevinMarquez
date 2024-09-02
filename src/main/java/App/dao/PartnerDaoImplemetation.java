package App.dao;

// Importación de clases necesarias para el manejo de la conexión a la base de datos, consultas y DTOs
import App.config.MYSQLConnection;
import App.dao_interface.PartnerDao;
import app.dto.PartnerDto;
import app.dto.UserDto;
import App.helpers.Helper;
import app.model.Partner;
import app.model.Person;
import app.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// Implementación de la interfaz PartnerDao
public class PartnerDaoImplemetation implements PartnerDao {

    // Método para crear un nuevo socio en la base de datos
    @Override
    public void createPartner(PartnerDto partnerDto) throws Exception {
        // Convierte el PartnerDto a un objeto Partner
        Partner partner = Helper.parse(partnerDto);
        
        // Consulta SQL para insertar un nuevo registro en la tabla 'partner'
        String query = "INSERT INTO PARTNER(USERID,AMOUNT,TYPE,CREATIONDATE) VALUES (?, ?, ?, ?)";
        // Prepara la declaración SQL
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        // Establece los valores de los parámetros en la consulta
        preparedStatement.setLong(1, partner.getUser_id().getId()); // ID del usuario
        preparedStatement.setDouble(2, partner.getMoney()); // Monto asociado al socio
        preparedStatement.setString(3, partner.getType()); // Tipo de socio
        preparedStatement.setTimestamp(4, partner.getDateCreated()); // Fecha de creación
        // Ejecuta la consulta
        preparedStatement.execute();
        // Cierra la declaración preparada
        preparedStatement.close();
    }

    // Método para eliminar un socio de la base de datos
    @Override
    public void deletePartner(PartnerDto partnerDto) throws Exception {
        // Convierte el PartnerDto a un objeto Partner
        Partner partner = Helper.parse(partnerDto);
        
        // Consulta SQL para eliminar un registro de la tabla 'partner' basado en el ID del usuario
        String query = "DELETE FROM PARTNER WHERE USERID = ?";
        // Prepara la declaración SQL
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        // Establece el valor del parámetro en la consulta
        preparedStatement.setLong(1, partner.getUser_id().getId()); // ID del usuario
        // Ejecuta la consulta
        preparedStatement.execute();
        // Cierra la declaración preparada
        preparedStatement.close();
    }

    // Método para verificar si un socio existe en la base de datos basado en el ID del usuario
    @Override
    public PartnerDto existByPartner(UserDto userDto) throws Exception {
        // Consulta SQL para seleccionar un socio basado en el ID del usuario
        String query = "SELECT ID,USERID,AMOUNT,TYPE,CREATIONDATE FROM PARTNER WHERE USERID = ?";
        // Prepara la declaración SQL
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        // Establece el valor del parámetro en la consulta
        preparedStatement.setLong(1, userDto.getId());
        // Ejecuta la consulta y obtiene el resultado
        ResultSet resulSet = preparedStatement.executeQuery();
        
        // Si se encuentra un registro en el ResultSet
        if (resulSet.next()) {
            // Crea un objeto Partner y establece sus propiedades a partir del ResultSet
            Partner partner = new Partner();
            partner.setId(resulSet.getLong("ID"));
            partner.setMoney(resulSet.getDouble("AMOUNT"));
            partner.setType(resulSet.getString("TYPE"));
            partner.setDateCreated(resulSet.getTimestamp("CREATIONDATE"));
            
            // Crea y configura el objeto User asociado al socio
            User user = new User();
            Person personId = new Person();
            user.setPersonId(personId);
            user.setId(resulSet.getLong("USERID"));
            partner.setUser_id(user);
            
            // Cierra el ResultSet y la declaración preparada
            resulSet.close();
            preparedStatement.close();
            // Convierte el objeto Partner a PartnerDto y lo devuelve
            return Helper.parse(partner);
        }
        // Si no se encuentra ningún registro, cierra el ResultSet y devuelve null
        resulSet.close();
        preparedStatement.close();
        return null;
    }

    // Método para obtener un socio basado en el monto asociado
    @Override
    public PartnerDto getMoney(double getMoney) throws Exception {
        // Consulta SQL para seleccionar un socio basado en el monto
        String query = "SELECT ID,USERID,AMOUNT,TYPE,CREATIONDATE FROM PARTNER WHERE AMOUNT = ?";
        // Prepara la declaración SQL
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        // Establece el valor del parámetro en la consulta
        preparedStatement.setDouble(1, getMoney);
        // Ejecuta la consulta y obtiene el resultado
        ResultSet resulSet = preparedStatement.executeQuery();
        
        // Si se encuentra un registro en el ResultSet
        if (resulSet.next()) {
            // Crea un objeto Partner y establece sus propiedades a partir del ResultSet
            Partner partner = new Partner();
            partner.setId(resulSet.getLong("ID"));
            partner.setMoney(resulSet.getDouble("AMOUNT"));
            partner.setType(resulSet.getString("TYPE"));
            partner.setDateCreated(resulSet.getTimestamp("CREATIONDATE"));
            
            // Crea y configura el objeto User asociado al socio
            User user = new User();
            user.setId(resulSet.getLong("USERID"));
            partner.setUser_id(user);
            
            // Cierra el ResultSet y la declaración preparada
            resulSet.close();
            preparedStatement.close();
            // Convierte el objeto Partner a PartnerDto y lo devuelve
            return Helper.parse(partner);
        }
        // Si no se encuentra ningún registro, cierra el ResultSet y devuelve null
        resulSet.close();
        preparedStatement.close();
        return null;
    }

    // Método para actualizar el monto asociado a un socio
    @Override
    public void updateMoney(PartnerDto partnerDto) throws Exception {
        // Consulta SQL para actualizar el monto de un socio
        String query = "UPDATE PARTNER SET AMOUNT = ? WHERE USERID = ?";
        // Prepara la declaración SQL
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        // Establece los valores de los parámetros en la consulta
        preparedStatement.setDouble(1, partnerDto.getMoney()); // Nuevo monto
        preparedStatement.setLong(2, partnerDto.getUserDto_id().getId()); // ID del usuario
        // Ejecuta la actualización
        preparedStatement.executeUpdate();
        // Cierra la declaración preparada
        preparedStatement.close();
    }

    // Método para obtener un socio basado en el tipo
    @Override
    public PartnerDto getType(PartnerDto partnerDto) throws Exception {
        // Consulta SQL para seleccionar un socio basado en el tipo
        String query = "SELECT ID,USERID,AMOUNT,TYPE,CREATIONDATE FROM PARTNER WHERE TYPE = ?";
        // Prepara la declaración SQL
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        // Establece el valor del parámetro en la consulta
        preparedStatement.setString(1, partnerDto.getType());
        // Ejecuta la consulta y obtiene el resultado
        ResultSet resulSet = preparedStatement.executeQuery();
        
        // Si se encuentra un registro en el ResultSet
        if (resulSet.next()) {
            // Crea un objeto Partner y establece sus propiedades a partir del ResultSet
            Partner partner = new Partner();
            partner.setId(resulSet.getLong("ID"));
            partner.setMoney(resulSet.getDouble("AMOUNT"));
            partner.setType(resulSet.getString("TYPE"));
            partner.setDateCreated(resulSet.getTimestamp("CREATIONDATE"));
            
            // Crea y configura el objeto User asociado al socio
            User user = new User();
            user.setId(resulSet.getLong("USERID"));
            partner.setUser_id(user);
            
            // Cierra el ResultSet y la declaración preparada
            resulSet.close();
            preparedStatement.close();
            // Convierte el objeto Partner a PartnerDto y lo devuelve
            return Helper.parse(partner);
        }
        // Si no se encuentra ningún registro, cierra el ResultSet y devuelve null
        resulSet.close();
        preparedStatement.close();
        return null;
    }

    // Método para contar el número de socios con tipo 'vip'
    @Override
    public int countVip() throws Exception {
        // Consulta SQL para contar el número de socios con tipo 'vip'
        String query = "SELECT COUNT(*) FROM PARTNER WHERE TYPE = 'vip'";
        // Prepara la declaración SQL
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        // Ejecuta la consulta y obtiene el resultado
        ResultSet resultSet = preparedStatement.executeQuery();
        int count = 0;
        
        // Si se encuentra un resultado en el ResultSet
        if (resultSet.next()) {
            count = resultSet.getInt(1); // Obtiene el conteo de socios 'vip'
        }
        // Cierra el ResultSet y la declaración preparada
        resultSet.close();
        preparedStatement.close();
        return count;
    }

    // Método para actualizar el tipo de un socio
    @Override
    public void updatePartnerType(PartnerDto partnerDto) throws Exception {
        // Consulta SQL para actualizar el tipo de un socio
        String query = "UPDATE PARTNER SET TYPE = ? WHERE ID = ?";
        // Prepara la declaración SQL
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        // Establece los valores de los parámetros en la consulta
        preparedStatement.setString(1, partnerDto.getType()); // Nuevo tipo
        preparedStatement.setLong(2, partnerDto.getId()); // ID del socio
        // Ejecuta la actualización
        preparedStatement.executeUpdate();
        // Cierra la declaración preparada
        preparedStatement.close();
    }
}
