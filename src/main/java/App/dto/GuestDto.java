/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dto;

/**
 *
 * @author papi yean
 */
public class GuestDto {
    
    private long id;
    private UserDto userId;
    private PartnerDto partnerId;
    private boolean status;

    public GuestDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserDto getUserId() {
        return userId;
    }

    public void setUserId(UserDto userId) {
        this.userId = userId;
    }

    public PartnerDto getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(PartnerDto partnerId) {
        this.partnerId = partnerId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
