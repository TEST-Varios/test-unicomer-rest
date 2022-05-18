package cl.testapi.unicomer.api;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import cl.testapi.unicomer.model.entity.ClientsDataInfo;
import cl.testapi.unicomer.service.IClientsDataInfoService;
import cl.testapi.unicomer.service.ResponseHandlerJson;
import cl.testapi.unicomer.service.config.SwaggerConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Controller Manager New Clients")
@Import(SwaggerConfig.class)
@RestController
@RequestMapping("/api/v1")
public class ApiRestClientController {
	
private static final Logger log = LoggerFactory.getLogger(ApiRestClientController.class);
	
	@Autowired
	private IClientsDataInfoService clientService;
	
	@ApiOperation(value = "Lista completa de Clientes", response = ClientsDataInfo.class)
    @GetMapping("/getClientsData")
	public ResponseEntity<Object> getDataClients() {
		
		log.info("Dentro del Servicio [getListaProductos]");
		
					
		if (clientService.findAll().isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR, No Data");
		}
			
		return ResponseHandlerJson.generateResponse(HttpStatus.OK, true, "Success", clientService.findAll());			
		
	}
	
	@ApiOperation(value = "Traer un Cliente por ID", response = ClientsDataInfo.class)
	@GetMapping("/getClientId/{idClient}")
	public ResponseEntity<Object> getClientById(@PathVariable UUID idClient) {
		
		Optional<ClientsDataInfo> busquedaClient = clientService.findByClient(idClient);
		
		if (busquedaClient.isPresent()) {
			return ResponseHandlerJson.generateResponse(HttpStatus.OK, true, "Success, seek", busquedaClient.get());
		} else {
			return ResponseHandlerJson.generateResponse(HttpStatus.NOT_FOUND, false, "ERROR, No Data", null);
		}	
	}
	
	
	@ApiOperation(value = "Agregar nuevo Cliente", response = ClientsDataInfo.class)
	@PostMapping("/saveClient")
	public ResponseEntity<Object> saveClient(@RequestBody ClientsDataInfo newClient) {
		
		try {
			return ResponseHandlerJson.generateResponse(HttpStatus.OK, true, "Success, save", clientService.save(newClient));
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR, Can't Save Data!", ex);
		}
		
	}
	
	@ApiOperation(value = "Actualizar Cliente", response = ClientsDataInfo.class)
	@PutMapping("/updateClient/{idClient}")
	public ResponseEntity<Object> updateClient(@RequestBody ClientsDataInfo updateClient, @PathVariable UUID idClient) {
		
		Optional<ClientsDataInfo> busquedaClient = clientService.findByClient(idClient);
		
		if (busquedaClient.isPresent()) {
			return ResponseHandlerJson.generateResponse(HttpStatus.CREATED, true, "Success, Update Client", clientService.save(updateClient));
		} else {
			return ResponseHandlerJson.generateResponse(HttpStatus.NOT_FOUND, false, "Success, Seek", null);
		}		
		
	}
	
	
	@ApiOperation(value = "Borrar Cliente", response = ClientsDataInfo.class)
	@DeleteMapping("/deleteClient/{idClient}")
	public void borrarClient(@PathVariable Long idClient) {
		
		try {
			clientService.delete(idClient);
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR, Can't Delete Data", ex);
		}
	}
	
	
	

}
