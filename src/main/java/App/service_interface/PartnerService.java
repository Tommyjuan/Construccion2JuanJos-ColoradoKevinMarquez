package App.service_interface;

import app.dto.GuestDto;
import app.dto.PartnerDto;

public interface PartnerService {

    public void deletePartner() throws Exception;

    public void createGuest(GuestDto guestDto) throws Exception;

    public void changeRole(PartnerDto partnerDto) throws Exception;

    GuestDto getGuestById(long guestId) throws Exception;

    public void updateStatus(GuestDto guestDto) throws Exception;

    public void updateMoney() throws Exception;

    public void checkVipLimit(PartnerDto partnerDto) throws Exception;

    public void guestLimit(PartnerDto partnerDto) throws Exception;

}
