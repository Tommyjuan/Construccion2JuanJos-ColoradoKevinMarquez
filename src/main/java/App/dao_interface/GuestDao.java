package App.dao_interface;

import app.dto.GuestDto;
import app.dto.UserDto;

public interface GuestDao {

    public void createGuest(GuestDto guestDto) throws Exception;

    public void deleteGuest(GuestDto guestDto) throws Exception;

    public GuestDto existByGuest(UserDto userDto) throws Exception;
}
