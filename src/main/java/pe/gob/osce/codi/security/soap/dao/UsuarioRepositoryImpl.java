package pe.gob.osce.codi.security.soap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osce.codi.security.soap.api.gen.CustomerRequest;
import pe.gob.osce.codi.security.soap.dto.OrganismoDto;
import pe.gob.osce.codi.security.soap.dto.RolDto;
import pe.gob.osce.codi.security.soap.dto.UsuarioDto;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {

	@PersistenceContext
	private EntityManager em;
    
	@Override
	@Transactional(readOnly = true)
	public UsuarioDto cargarUsuario(CustomerRequest customerRequest) {
		StringBuilder jpql = new StringBuilder();
		UsuarioDto usuario = new UsuarioDto();
		boolean reqUsername = false;

		reqUsername = customerRequest.getUsername() != null && !customerRequest.getUsername().trim().isEmpty();
		
		jpql.append("SELECT PERS.N_ID_PERS, PERS.C_TIPDOC, PERS.C_NRODOC, PERS.C_NOMBRE, ");
		jpql.append("PERS.C_APELLID, PERS.C_NOMCOM, PERS.C_EMAIL, USU.C_ESTADO ");
		jpql.append("FROM ADM.TBL_ADM_USU USU ");
		jpql.append("INNER JOIN ADM.TBL_ADM_PERSONA PERS ");
		jpql.append("ON USU.N_ID_PERS = PERS.N_ID_PERS ");
		jpql.append("WHERE 1 = 1 ");

		if (reqUsername) {
			jpql.append("AND USU.C_CODOID = :userName ");
		}

		Query query = em.createNativeQuery(jpql.toString());

		if (reqUsername) {
			query.setParameter("userName", customerRequest.getUsername());
		}

		Object[] obj = (Object[]) query.getSingleResult();

		if (obj != null) {
			usuario.setCodigoUsuario(obj[0] != null ? obj[0].toString() : "");
			usuario.setTipoDocumento(obj[1] != null ? obj[1].toString() : "");
			usuario.setNumeroDocumento(obj[2] != null ? obj[2].toString() : "");
			usuario.setNombres(obj[3] != null ? obj[3].toString() : "");
			usuario.setApellidos(obj[4] != null ? obj[4].toString() : "");
			usuario.setEmail(obj[5] != null ? obj[5].toString() : "");
			usuario.setEstado(obj[6] != null ? obj[6].toString() : "");
			if (obj[0] != null) {
				this.cargarOrganismos(customerRequest.getUsername(), usuario);
				this.cargarRoles(customerRequest.getUsername(), usuario);
			}
		}
		
		return usuario;
	}
	
	private void cargarOrganismos(String userName, UsuarioDto usuario) {
		StringBuilder jpql = new StringBuilder();
		
		jpql.append("SELECT ORG.N_ID_ORGAN, ORG.C_NOMORG, ORG.C_NRODOC ");
		jpql.append("FROM ADM.DET_ADM_USU_ORG USUORG ");
		jpql.append("INNER JOIN ADM.TBL_ADM_ORG ORG ON USUORG.N_ID_ORGAN = ORG.N_ID_ORGAN ");
		jpql.append("INNER JOIN ADM.TBL_ADM_USU USU ON USUORG.N_ID_PERS = USU.N_ID_PERS ");
		jpql.append("WHERE USUORG.C_ESTADO = 'ACTIV' ");
		jpql.append("AND ORG.N_ID_TIPORG = 1 ");
		jpql.append("AND USU.C_CODOID = :uid ");
		Query query = em.createNativeQuery(jpql.toString());
		if(userName != null) {
			query.setParameter("uid",userName);
		}
		List<Object[]> listaObjeto = query.getResultList();
		for (Object[] contObj : listaObjeto) {
			OrganismoDto organismo = new OrganismoDto();
			organismo.setIdOrganismo(contObj[0]!=null ? contObj[0].toString() : "");
			organismo.setRazonSocial(contObj[1]!=null ? contObj[1].toString() : "");
			organismo.setNumeroDocumento(contObj[2]!=null ? contObj[2].toString() : "");
			usuario.getOrganismos().add(organismo);
		}

	}
	
	private void cargarRoles(String userName, UsuarioDto usuario) {
		StringBuilder jpql = new StringBuilder();
		
		jpql.append("SELECT ROL.N_ID_ROL, ROL.C_NOMROL ");
		jpql.append("FROM ADM.TBL_ADM_USU USU ");
		jpql.append("INNER JOIN ADM.DET_ADM_ROL_USU DRU ON (USU.N_ID_PERS = DRU.N_ID_PERS) ");
		jpql.append("INNER JOIN ADM.TBL_ADM_ROL ROL ON (DRU.N_ID_ROL = ROL.N_ID_ROL) ");
		jpql.append("INNER JOIN ADM.TBL_ADM_MOD MOD ON (ROL.N_ID_MODULO=MOD.N_ID_MODULO) ");
		jpql.append("WHERE USU.C_CODOID = :uid ");
		Query query = em.createNativeQuery(jpql.toString());
		if(userName != null) {
			query.setParameter("uid",userName);
		}
		List<Object[]> listaObjeto = query.getResultList();
		for (Object[] contObj : listaObjeto) {
			RolDto rol = new RolDto();
			rol.setCodigoRol(contObj[0]!=null ? contObj[0].toString() : "");
			rol.setNombreRol(contObj[1]!=null ? contObj[1].toString() : "");
			usuario.getRoles().add(rol);
		}

	}
	
}
