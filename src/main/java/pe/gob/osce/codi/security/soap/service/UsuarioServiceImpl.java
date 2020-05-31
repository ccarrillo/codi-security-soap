package pe.gob.osce.codi.security.soap.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.Filter;
import org.springframework.stereotype.Service;

import pe.gob.osce.codi.security.soap.api.gen.CustomerRequest;
import pe.gob.osce.codi.security.soap.api.gen.CustomerResponse;
import pe.gob.osce.codi.security.soap.api.gen.Organismo;
import pe.gob.osce.codi.security.soap.api.gen.Rol;
import pe.gob.osce.codi.security.soap.api.gen.Usuario;
import pe.gob.osce.codi.security.soap.dao.UsuarioRepository;
import pe.gob.osce.codi.security.soap.dto.UsuarioDto;
import pe.gob.osce.codi.security.soap.utils.MensajeResponseType;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private LdapTemplate ldapTemplate;

	@Override
	public CustomerResponse obtenerUsuario(CustomerRequest request) {
		CustomerResponse response = new CustomerResponse();
		boolean parametrosCorrectos = true;
		Usuario usuario = new Usuario();
		
		try {
			if (request.getUsername() == null || request.getUsername().isEmpty()) {
				response.setCodigo(MensajeResponseType.MSJ_DATOS_OBLIGATORIOS.getKey());
				response.setMensaje(MensajeResponseType.MSJ_DATOS_OBLIGATORIOS.getValue());
				parametrosCorrectos = false;
			} else if (request.getPassword() == null || request.getPassword().isEmpty()) {
				response.setCodigo(MensajeResponseType.MSJ_DATOS_OBLIGATORIOS.getKey());
				response.setMensaje(MensajeResponseType.MSJ_DATOS_OBLIGATORIOS.getValue());
				parametrosCorrectos = false;
			}
			
			if (parametrosCorrectos) {			
		        Filter filter= new EqualsFilter("uid", request.getUsername());

				boolean authenticate = ldapTemplate.authenticate("", filter.encode(), request.getPassword());

				if (authenticate) {
					UsuarioDto usuarioDto = usuarioRepository.cargarUsuario(request);
					if (usuarioDto != null) {
						usuario.setCodigoUsuario(usuarioDto.getCodigoUsuario());
						usuario.setTipoDocumento(usuarioDto.getTipoDocumento());
						usuario.setNumeroDocumento(usuarioDto.getNumeroDocumento());
						usuario.setNombres(usuarioDto.getNombres());
						usuario.setApellidos(usuarioDto.getApellidos());
						usuario.setEmail(usuarioDto.getEmail());
						usuario.setEstado(usuarioDto.getEstado());
						usuarioDto.getOrganismos().forEach(obj -> {
							Organismo organismo = new Organismo();
							organismo.setIdOrganismo(obj.getIdOrganismo());
							organismo.setRazonSocial(obj.getRazonSocial());
							organismo.setNumeroDocumento(obj.getNumeroDocumento());
							usuario.getOrganismo().add(organismo);
						});
						usuarioDto.getRoles().forEach(obj2 -> {
							Rol rol = new Rol();
							rol.setCodigoRol(obj2.getCodigoRol());
							rol.setNombreRol(obj2.getNombreRol());
							usuario.getRol().add(rol);
						});
					}
					
					response.getUsuario().add(usuario);
					response.setCodigo(MensajeResponseType.MSJ_OPERACION_COMPLETADA.getKey());
					response.setMensaje(MensajeResponseType.MSJ_OPERACION_COMPLETADA.getValue());
					
				} else {
					response.setCodigo(MensajeResponseType.MSJ_CREDENCIALES_INVALIDAS.getKey());
					response.setMensaje(MensajeResponseType.MSJ_CREDENCIALES_INVALIDAS.getValue());
				}
			}
		} catch (Exception e) {
			response.setCodigo(MensajeResponseType.MSJ_ERROR_PLATAFORMA.getKey());
			response.setMensaje(MensajeResponseType.MSJ_ERROR_PLATAFORMA.getValue());
			logger.error(e.getLocalizedMessage());
		}
		
		return response;
	}
	
}
