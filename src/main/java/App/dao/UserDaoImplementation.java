package App.dao;


import App.config.MYSQLConnection;
import App.dao_interface.UserDao;
import app.dto.UserDto;
import App.helpers.Helper;
import app.model.Person;
import app.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// Implementación de la interfaz UserDao
public class UserDaoImplementation implements UserDao {

    // Método para encontrar un usuario en la base de datos basado en el nombre de usuario
    @Override
    public UserDto findByUserName(UserDto userDto) throws Exception {
        // Consulta SQL para seleccionar un usuario basado en el nombre de usuario
        String query = "SELECT ID, USERNAME, PASSWORD, ROLE, PERSONNID FROM USER WHERE USERNAME = ?";
        // Prepara la declaración SQL
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        // Establece el valor del parámetro en la consulta
        preparedStatement.setString(1, userDto.getUserName());
        // Ejecuta la consulta y obtiene el resultado
        ResultSet resulSet = preparedStatement.executeQuery();
        
        // Si se encuentra un registro en el ResultSet
        if (resulSet.next()) {
            // Crea un objeto User y establece sus propiedades a partir del ResultSet
            User user = new User();
            user.setId(resulSet.getLong("ID")); // ID del usuario
            user.setUserName(resulSet.getString("USERNAME")); // Nombre de usuario
            user.setPassword(resulSet.getString("PASSWORD")); // Contraseña del usuario
            user.setRol(resulSet.getString("ROLE")); // Rol del usuario
            
            // Crea un objeto Person y establece su ID
            Person person = new Person();
            person.setId(resulSet.getLong("PERSONNID")); // ID de la persona asociada al usuario
            user.setPersonId(person); // Asocia la persona al usuario
            
            // Cierra el ResultSet y la declaración preparada
            resulSet.close();
            preparedStatement.close();
            // Convierte el objeto User a UserDto y lo devuelve
            return Helper.parse(user);
        }
        
        // Si no se encuentra ningún registro, cierra el ResultSet y la declaración preparada, y devuelve null
        resulSet.close();
        preparedStatement.close();
        return null;
    }

    // Método para verificar si un usuario existe en la base de datos basado en el nombre de usuario
    @Override
    public boolean existsByUserName(UserDto userDto) throws Exception {
        // Consulta SQL para verificar la existencia de un usuario basado en el nombre de usuario
        String query = "SELECT 1 FROM USER WHERE USERNAME = ?";
        // Prepara la declaración SQL
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        // Establece el valor del parámetro en la consulta
        preparedStatement.setString(1, userDto.getUserName());
        // Ejecuta la consulta y obtiene el resultado
        ResultSet resulSet = preparedStatement.executeQuery();
        // Verifica si hay algún resultado
        boolean exists = resulSet.next();
        // Cierra el ResultSet y la declaración preparada
        resulSet.close();
        preparedStatement.close();
        // Devuelve true si existe al menos un registro, false en caso contrario
        return exists;
    }

    // Método para crear un nuevo usuario en la base de datos
    @Override
    public void createUser(UserDto userDto) throws Exception {
        // Convierte el UserDto a un objeto User
        User user = Helper.parse(userDto);
        
        // Consulta SQL para insertar un nuevo registro en la tabla 'USER'
        String query = "INSERT INTO USER(PERSONNID, USERNAME, PASSWORD, ROLE) VALUES (?, ?, ?, ?)";
        // Prepara la declaración SQL
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        // Establece los valores de los parámetros en la consulta
        preparedStatement.setLong(1, user.getPersonId().getId()); // ID de la persona asociada al usuario
        preparedStatement.setString(2, user.getUserName()); // Nombre de usuario
        preparedStatement.setString(3, user.getPassword()); // Contraseña del usuario
        preparedStatement.setString(4, user.getRol()); // Rol del usuario
        // Ejecuta la consulta
        preparedStatement.execute();
        // Cierra la declaración preparada
        preparedStatement.close();
    }

    // Método para actualizar el rol de un usuario en la base de datos
    @Override
    public void uptadeUserRole(UserDto userDto) throws Exception {
        // Consulta SQL para actualizar el rol de un usuario basado en el nombre de usuario
        String query = "UPDATE USER SET ROLE = ? WHERE USERNAME = ?";
        // Prepara la declaración SQL
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        // Establece los valores de los parámetros en la consulta
        preparedStatement.setString(1, userDto.getRol()); // Nuevo rol del usuario
        preparedStatement.setString(2, userDto.getUserName()); // Nombre de usuario
        // Ejecuta la consulta de actualización
        preparedStatement.executeUpdate();
        // Cierra la declaración preparada
        preparedStatement.close();
    }

    // Método para eliminar un usuario de la base de datos basado en el ID de la persona asociada
    @Override
    public void deleteUser(UserDto userDto) throws Exception {
        // Consulta SQL para eliminar un registro de la tabla 'USER' basado en el ID de la persona
        String query = "DELETE FROM USER WHERE PERSONNID = ?";
        // Prepara la declaración SQL
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        // Establece el valor del parámetro en la consulta
        preparedStatement.setLong(1, userDto.getPersonId().getId()); // ID de la persona asociada al usuario
        // Ejecuta la consulta de eliminación
        preparedStatement.execute();
        // Cierra la declaración preparada
        preparedStatement.close();
    }
}
