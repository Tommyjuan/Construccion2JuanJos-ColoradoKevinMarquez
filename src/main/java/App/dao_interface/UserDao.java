package App.dao_interface;

import app.dto.UserDto;

public interface UserDao {

    public UserDto findByUserName(UserDto userDto) throws Exception;

    public boolean existsByUserName(UserDto userDto) throws Exception;

    public void createUser(UserDto userDto) throws Exception;
    
    public void uptadeUserRole (UserDto userDto) throws Exception; //actualiza el rol de usuario
    
    public void deleteUser(UserDto userDto) throws Exception;
}
