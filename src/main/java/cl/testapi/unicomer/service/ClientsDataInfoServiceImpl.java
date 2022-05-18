package cl.testapi.unicomer.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.testapi.unicomer.model.entity.ClientsDataInfo;
import cl.testapi.unicomer.model.dao.IClientsDataInfoDao;

@Service
public class ClientsDataInfoServiceImpl implements IClientsDataInfoService{
	
	@Autowired
	private IClientsDataInfoDao clientDao;
	
	@Override
	public List<ClientsDataInfo> findAll() {
		return (List<ClientsDataInfo>) clientDao.findAll();
	}

	@Override
	public ClientsDataInfo save(ClientsDataInfo clientsDataInfo) {
		return clientDao.save(clientsDataInfo);
	}

	@Override
	public ClientsDataInfo findOne(Long id) {
		return clientDao.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		clientDao.deleteById(id);		
	}

	@Override
	public Optional<ClientsDataInfo> findByClient(UUID cliId) {
		return Optional.ofNullable(clientDao.findByCliId(cliId));
	}

	
	

}
