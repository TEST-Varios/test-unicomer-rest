package cl.testapi.unicomer.model.dao;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import cl.testapi.unicomer.model.entity.ClientsDataInfo;


public interface IClientsDataInfoDao extends CrudRepository<ClientsDataInfo, Long> {
	
	ClientsDataInfo findByCliId(UUID cliId);

}
