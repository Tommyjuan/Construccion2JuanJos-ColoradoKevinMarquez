package App.dao_interface;

import app.dto.GuestDto;
import app.dto.UserDto;

public interface GuestDao {

    public void createPartner(GuestDto guestDto) throws Exception;

    public void deletePartner(GuestDto guestDto) throws Exception;

    boolean existsByUser(UserDto userDto) throws Exception;
}
