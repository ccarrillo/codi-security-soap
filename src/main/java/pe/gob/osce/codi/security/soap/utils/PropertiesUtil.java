package pe.gob.osce.codi.security.soap.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

public final class PropertiesUtil {

	private PropertiesUtil() {
	}

	public static String getValue(String fileResource, Locale locale, String key) {
		try {

			return ResourceBundle.getBundle(fileResource, locale).getString(key);

		} catch (MissingResourceException e) {
			return "[" + key + "]";
		}
	}

	public static String getValue(String fileResource, Locale locale, String key, Object param) {
		String valor = null;
		try {
			valor = ResourceBundle.getBundle(fileResource, locale).getString(key);
			valor = MessageFormat.format(valor, param);

		} catch (MissingResourceException e) {
			valor = "[" + key + "]";
		}
		return valor;
	}

	public static String getValue(String fileResource, Locale locale, String key, Object... params) {
		String valor = null;
		try {
			valor = ResourceBundle.getBundle(fileResource, locale).getString(key);
			valor = MessageFormat.format(valor, params);

		} catch (MissingResourceException e) {
			valor = "[" + key + "]";
		}
		return valor;
	}

	public static Properties loadProperties(String pathNameFile) {
		return loadProperties(pathNameFile, PropertiesUtil.class.getClassLoader());
	}

	public static Properties loadProperties(String pathNameFile, ClassLoader classLoader) {
		Properties propiedades = null;
		try {
			InputStream in = classLoader.getResourceAsStream(pathNameFile);
			propiedades = new Properties();
			propiedades.load(in);
			in.close();
		} catch (Exception e) {
			propiedades = null;
		}
		return propiedades;
	}

	public static Properties loadFileSystemProperties(String pathNameFile) {
		Properties propiedades = null;
		try {
			InputStream in = new FileInputStream(pathNameFile);
			propiedades = new Properties();
			propiedades.load(in);
			in.close();
		} catch (Exception e) {
			propiedades = null;
		}
		return propiedades;
	}

	public static Properties loadFileSystemProperties(String pathNameFile, String pathFile) {
		Properties propiedades = null;
		try {
			InputStream in = new FileInputStream(pathNameFile + pathFile);
			propiedades = new Properties();
			propiedades.load(in);
			in.close();
		} catch (Exception e) {
			propiedades = null;
		}
		return propiedades;
	}

	public static Map<String, String> loadFileSystemProperties2Map(String pathNameFile) {
		Properties prop = loadFileSystemProperties(pathNameFile);
		return createMap(prop);
	}

	public static Map<String, String> createMap(Properties prop) {
		Map<String, String> mapa = null;

		if (prop != null) {
			mapa = new HashMap<String, String>();
			Set<Entry<Object, Object>> mapaEntrySet = prop.entrySet();
			for (Entry es : mapaEntrySet) {
				mapa.put((String) es.getKey(), (String) es.getValue());
			}
		}
		return mapa;
	}

}
