package pe.gob.osce.codi.security.soap.dao;

import pe.gob.osce.codi.security.soap.api.gen.CustomerRequest;
import pe.gob.osce.codi.security.soap.dto.UsuarioDto;

public interface UsuarioRepository {

	UsuarioDto cargarUsuario(CustomerRequest customerRequest);

}
