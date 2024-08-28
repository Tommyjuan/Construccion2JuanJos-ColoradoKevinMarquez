package App.dao_interface;

import app.dto.GuestDto;
import app.dto.UserDto;

public interface GuestDao {

    public void createGuets(GuestDto guestDto) throws Exception;

}
