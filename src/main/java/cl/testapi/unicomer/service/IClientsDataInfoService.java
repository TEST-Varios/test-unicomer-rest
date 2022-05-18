package cl.testapi.unicomer.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import cl.testapi.unicomer.model.entity.ClientsDataInfo;

public interface IClientsDataInfoService {
	
    List<ClientsDataInfo> findAll();
    
    Optional<ClientsDataInfo> findByClient(UUID cliId);
	
	ClientsDataInfo save(ClientsDataInfo clientsDataInfo);
	
	ClientsDataInfo findOne(Long id);
	
	void delete(Long id);
}
