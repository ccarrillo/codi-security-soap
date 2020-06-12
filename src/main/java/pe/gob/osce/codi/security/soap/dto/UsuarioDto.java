package pe.gob.osce.codi.security.soap.dto;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDto {
    
    private String codigoUsuario;
    
    private String tipoDocumento;
    
    private String nombreDocumento;
    
    private String numeroDocumento;

    private String nombres; 
    
    private String apellidos;

    private String email;
    
    private String estado;
    
    private List<OrganismoDto> organismos = new ArrayList<>();

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNombreDocumento() {
		return nombreDocumento;
	}

	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<OrganismoDto> getOrganismos() {
		return organismos;
	}

	public void setOrganismos(List<OrganismoDto> organismos) {
		this.organismos = organismos;
	}
	
}
