package App.dao_interface;

import app.dto.PartnerDto;
import app.dto.UserDto;

public interface PartnerDao {

    public void createPartner(PartnerDto partnerDto) throws Exception; //crea un nuevo partner

    public void deletePartner(PartnerDto partnerDto) throws Exception; //elimina un partner de la base de datos.

    public PartnerDto existByPartner(UserDto userDto) throws Exception; //Verifica si un partner existe en la base de datos basado en el usuario.

    public PartnerDto getMoney(double getMoney) throws Exception; //Obtiene un partner de la base de datos basado en la cantidad de dinero asociada.

    public void updateMoney(PartnerDto partnerDto) throws Exception; //Actualiza la cantidad de dinero de un partner en la base de datos.

    public PartnerDto getType(PartnerDto partnerDto) throws Exception; //obtiene el tipo de socio

    public int countVip() throws Exception; //cuenta la cantidad de vips que existen en la base de datos.

    public void updatePartnerType(PartnerDto partnerDto) throws Exception; //actualiza el tipo de socio en la base de datos.

}
