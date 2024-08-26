package App.dao_interface;

import app.dto.PartnerDto;
import app.dto.UserDto;

public interface PartnerDao {

    public void createPartner(PartnerDto partnerDto) throws Exception;

    public void deletePartner(PartnerDto partnerDto) throws Exception;

    boolean existsByUser(UserDto userDto) throws Exception;
}
