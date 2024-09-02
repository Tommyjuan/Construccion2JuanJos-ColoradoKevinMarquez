package App.dao_interface;

import app.dto.PersonDto;

public interface PersonDao {

    public boolean existByDocument(PersonDto personDto) throws Exception; //verifica si el documento existe en la base de datos.

    public void createPerson(PersonDto personDto) throws Exception; //crea una nueva persona en la base de datos.

    public void deletePerson(PersonDto personDto) throws Exception; //elimina una persona de la base de datos 

    public PersonDto findByDocument(PersonDto personDto) throws Exception; //encuentra a una persona en la base de datos por su documento

}
