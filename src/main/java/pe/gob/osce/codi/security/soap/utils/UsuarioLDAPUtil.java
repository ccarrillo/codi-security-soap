package pe.gob.osce.codi.security.soap.utils;

import java.util.Properties;

public class UsuarioLDAPUtil {
	
	public static  String ldapHost = new String();
	public static  String password = new String();
	public static  String loginDN = new String();
	public static  String ldapPort = new String();
	public static  String BASE_DN = new String();
	public static  String USER_FILTER = new String();
	public static  String USER_FILTER_UID = new String();
	public static  String URL = new String();
	public static Properties propertie = new Properties();
	public static  String PATHFILENAME_CFG = "/fsapp/seace3/cfg/usuarioldap.properties";
	
	static {
		try {
			propertie = PropertiesUtil.loadFileSystemProperties(PATHFILENAME_CFG);
			ldapHost=(propertie.getProperty("ldapHost"));
			password=(propertie.getProperty("password"));
			loginDN=(propertie.getProperty("loginDN"));
			ldapPort=(propertie.getProperty("ldapPort"));
			BASE_DN=(propertie.getProperty("BASE_DN"));
			USER_FILTER=(propertie.getProperty("USER_FILTER"));
			URL=("ldap://" + ldapHost + ":" + ldapPort + "/");
			USER_FILTER_UID=("uid={0}");
						
		} catch (Exception e) {
			System.out.println( e);
		}
	}
	
	public UsuarioLDAPUtil() {

	}
	
	
}
