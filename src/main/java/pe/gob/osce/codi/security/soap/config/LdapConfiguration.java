package pe.gob.osce.codi.security.soap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

import pe.gob.osce.codi.security.soap.utils.UsuarioLDAPUtil;

@Configuration
public class LdapConfiguration {
	
	@Bean
    public LdapContextSource contextSource(){
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl(UsuarioLDAPUtil.URL + UsuarioLDAPUtil.BASE_DN);
        contextSource.setUserDn(UsuarioLDAPUtil.loginDN);
        contextSource.setPassword(UsuarioLDAPUtil.password);
        contextSource.afterPropertiesSet();
        return contextSource;
    }

    @Bean
    public LdapTemplate ldapTemplate(){
        LdapTemplate template = new LdapTemplate(contextSource());
        return template;
    }
    
}
