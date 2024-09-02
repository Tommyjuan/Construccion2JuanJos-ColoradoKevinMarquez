package App.dao_interface;

import app.dto.GuestDto;
import app.dto.UserDto;

public interface GuestDao {

    public void createGuest(GuestDto guestDto) throws Exception; //crea un nuevo invitado en la base de datos.

    public void deleteGuest(GuestDto guestDto) throws Exception; //elimina un invitado de la base de datos.

    public GuestDto existByGuest(UserDto userDto) throws Exception; //Verifica si un invitado existe en la base de datos basado en el usuario.

    public void changeStatus(GuestDto guestDto) throws Exception; //Cambia el estado de un invitado en la base de datos.

    public GuestDto getGuestById(long guestId) throws Exception; //Obtiene un invitado de la base de datos basado en su ID.


}
