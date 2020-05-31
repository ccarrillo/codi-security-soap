package pe.gob.osce.codi.security.soap.service;

import pe.gob.osce.codi.security.soap.api.gen.CustomerRequest;
import pe.gob.osce.codi.security.soap.api.gen.CustomerResponse;

public interface UsuarioService {
	
	CustomerResponse obtenerUsuario(CustomerRequest request);

}
