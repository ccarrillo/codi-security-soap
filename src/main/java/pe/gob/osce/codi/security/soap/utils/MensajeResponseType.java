package pe.gob.osce.codi.security.soap.utils;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public enum MensajeResponseType {
	
	MSJ_OPERACION_COMPLETADA("00", "Se realizó la autenticación exitosa."),
	MSJ_ERROR_PLATAFORMA("01", "Error en la plataforma del servicio."),
	MSJ_CREDENCIALES_INVALIDAS("02", "El usuario o la contraseña es inválida."),
	MSJ_ERROR_ESTRUCTURA("10", "Error en la estructura proporcionada al servicio."),
	MSJ_DATOS_OBLIGATORIOS("11", "El usuario y contraseña son obligatorios."),
	MSJ_ESTRUCTURA_FORMATO_INVALIDO("13", "Se ha recibido datos en formato inadecuado."),
	MSJ_PERMISOS_INSUFICIENTES("21", "El usuario no cuenta con los permisos suficientes.");

	private String key;
		
	private String value;
	
	private static final Map<String, MensajeResponseType> lookup = new HashMap<String, MensajeResponseType>();

	static {
		for (MensajeResponseType s : EnumSet.allOf(MensajeResponseType.class)) {
			lookup.put(s.getKey(), s);
		}
	}

	private MensajeResponseType(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public String getDescription(Locale locale) {
		return this.getValue();
	}

	public static MensajeResponseType get(String key) {
		return lookup.get(key);
	}
}
