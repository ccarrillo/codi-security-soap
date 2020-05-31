package pe.gob.osce.codi.security.soap.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import pe.gob.osce.codi.security.soap.api.gen.CustomerRequest;
import pe.gob.osce.codi.security.soap.api.gen.CustomerResponse;
import pe.gob.osce.codi.security.soap.service.UsuarioService;

@Endpoint
public class UsuarioEndpoint {

	private static final String NAMESPACE = "http://www.osce.gob.pe/codi/security/soap/api/gen";
	
	@Autowired
	private UsuarioService service;

	@PayloadRoot(namespace = NAMESPACE, localPart = "CustomerRequest")
	@ResponsePayload
	public CustomerResponse getUsuario(@RequestPayload CustomerRequest request) {
		return service.obtenerUsuario(request);
	}

}
