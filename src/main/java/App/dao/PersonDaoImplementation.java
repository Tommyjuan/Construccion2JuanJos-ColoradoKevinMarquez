package App.dao;


import App.config.MYSQLConnection;
import App.dao_interface.PersonDao;
import app.dto.PersonDto;
import App.helpers.Helper;
import app.model.Person;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// Implementación de la interfaz PersonDao
public class PersonDaoImplementation implements PersonDao {

    // Método para verificar si una persona existe en la base de datos basado en el documento
    @Override
    public boolean existByDocument(PersonDto personDto) throws Exception {
        // Consulta SQL para verificar la existencia de una persona basado en el documento
        String query = "SELECT 1 FROM PERSON WHERE DOCUMENT = ?";
        // Prepara la declaración SQL
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        // Establece el valor del parámetro en la consulta
        preparedStatement.setLong(1, personDto.getDocument());
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

    // Método para crear una nueva persona en la base de datos
    @Override
    public void createPerson(PersonDto personDto) throws Exception {
        // Convierte el PersonDto a un objeto Person
        Person person = Helper.parse(personDto);
        
        // Consulta SQL para insertar un nuevo registro en la tabla 'PERSON'
        String query = "INSERT INTO PERSON(NAME, DOCUMENT, CELLPHONE) VALUES (?, ?, ?)";
        // Prepara la declaración SQL
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        // Establece los valores de los parámetros en la consulta
        preparedStatement.setString(1, person.getName()); // Nombre de la persona
        preparedStatement.setLong(2, person.getDocument()); // Documento de la persona
        preparedStatement.setLong(3, person.getCelPhone()); // Número de celular de la persona
        // Ejecuta la consulta
        preparedStatement.execute();
        // Cierra la declaración preparada
        preparedStatement.close();
    }

    // Método para eliminar una persona de la base de datos basado en el documento
    @Override
    public void deletePerson(PersonDto personDto) throws Exception {
        // Convierte el PersonDto a un objeto Person
        Person person = Helper.parse(personDto);
        
        // Consulta SQL para eliminar un registro de la tabla 'PERSON' basado en el documento
        String query = "DELETE FROM PERSON WHERE DOCUMENT = ?";
        // Prepara la declaración SQL
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        // Establece el valor del parámetro en la consulta
        preparedStatement.setLong(1, person.getDocument()); // Documento de la persona
        // Ejecuta la consulta
        preparedStatement.execute();
        // Cierra la declaración preparada
        preparedStatement.close();
    }

    // Método para encontrar una persona en la base de datos basado en el documento
    @Override
    public PersonDto findByDocument(PersonDto personDto) throws Exception {
        // Consulta SQL para seleccionar una persona basado en el documento
        String query = "SELECT ID, DOCUMENT, NAME, CELLPHONE FROM PERSON WHERE DOCUMENT = ?";
        // Prepara la declaración SQL
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        // Establece el valor del parámetro en la consulta
        preparedStatement.setLong(1, personDto.getDocument());
        // Ejecuta la consulta y obtiene el resultado
        ResultSet resulSet = preparedStatement.executeQuery();
        
        // Si se encuentra un registro en el ResultSet
        if (resulSet.next()) {
            // Crea un objeto Person y establece sus propiedades a partir del ResultSet
            Person person = new Person();
            person.setId(resulSet.getLong("ID")); // ID de la persona
            person.setDocument(resulSet.getLong("DOCUMENT")); // Documento de la persona
            person.setName(resulSet.getString("NAME")); // Nombre de la persona
            person.setCelPhone(resulSet.getLong("CELLPHONE")); // Número de celular de la persona
            // Cierra el ResultSet y la declaración preparada
            resulSet.close();
            preparedStatement.close();
            // Convierte el objeto Person a PersonDto y lo devuelve
            return Helper.parse(person);
        }
        // Si no se encuentra ningún registro, cierra el ResultSet y devuelve null
        resulSet.close();
        preparedStatement.close();
        return null;
    }
}
