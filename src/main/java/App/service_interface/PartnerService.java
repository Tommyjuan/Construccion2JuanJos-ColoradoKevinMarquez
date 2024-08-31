package App.service_interface;

import app.dto.GuestDto;
import app.dto.PartnerDto;

public interface PartnerService {

    public void deletePartner() throws Exception;

    public void createGuest(GuestDto guestDto) throws Exception;

    public void changeRole(PartnerDto partnerDto) throws Exception;

    GuestDto getGuestById(long guestId) throws Exception;

    public void updateGuestStatus(GuestDto guestDto) throws Exception;
}
