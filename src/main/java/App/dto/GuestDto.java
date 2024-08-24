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
    
     /*
     * Obtiene el identificador del invitado.
     * @return El identificador del invitado.
     */
    public long getId() {
        return id;
    }
    
     /*
     * Establece el identificador del invitado.
     * @param id El identificador del invitado.
     */
    public void setId(long id) {
        this.id = id;
    }
    
     /*
     * Obtiene el objeto UserDto asociado con el invitado.
     * @return El objeto UserDto asociado con el invitado.
     */
    public UserDto getUserId() {
        return userId;
    }
    
     /*
     * Establece el objeto UserDto asociado con el invitado.
     * @param userId El objeto UserDto a asociar con el invitado.
     */
    public void setUserId(UserDto userId) {
        this.userId = userId;
    }
    
      /*
     * Obtiene el objeto PartnerDto asociado con el invitado.
     * @return El objeto PartnerDto asociado con el invitado.
     */
    public PartnerDto getPartnerId() {
        return partnerId;
    }
    
     /*
     * Establece el objeto PartnerDto asociado con el invitado.
     * @param partnerId El objeto PartnerDto a asociar con el invitado.
     */
    public void setPartnerId(PartnerDto partnerId) {
        this.partnerId = partnerId;
    }
    
    
     /*
     * Obtiene el estado del invitado.
     * @return El estado del invitado (true si está activo, false si está inactivo).
     */
    public boolean isStatus() {
        return status;
    }
    
     /*
     * Establece el estado del invitado.
     * @param status El estado del invitado (true para activo, false para inactivo).
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
