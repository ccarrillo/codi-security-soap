package pe.gob.osce.codi.security.soap.dto;

import java.util.ArrayList;
import java.util.List;

public class OrganismoDto {

    private String idOrganismo;

    private String razonSocial;

    private String numeroDocumento;
    
    private List<RolDto> roles = new ArrayList<>();

	public String getIdOrganismo() {
		return idOrganismo;
	}

	public void setIdOrganismo(String idOrganismo) {
		this.idOrganismo = idOrganismo;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	
	public List<RolDto> getRoles() {
		return roles;
	}

	public void setRoles(List<RolDto> roles) {
		this.roles = roles;
	}

}
