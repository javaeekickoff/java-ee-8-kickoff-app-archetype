#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${groupId}.config;

import static java.util.logging.Logger.getLogger;
import static org.omnifaces.utils.properties.PropertiesUtils.loadPropertiesFromClasspath;
import static org.omnifaces.utils.properties.PropertiesUtils.loadXMLPropertiesStagedFromClassPath;

import java.util.Map;
import java.util.logging.Logger;

import org.omnifaces.persistence.datasource.PropertiesFileLoader;

/**
 * Adaptor for the switchable datasource as defined in web.xml to be able to read properties from the
 * right file.
 */
public class DataSourceStagedPropertiesFileLoader implements PropertiesFileLoader {

	private static final Logger logger = getLogger(DataSourceStagedPropertiesFileLoader.class.getName());

	@Override
	public Map<String, String> loadFromFile(String fileName) {

		// Make sure we use the same names as the application settings are using
		Map<String, String> omniSettings = loadPropertiesFromClasspath("META-INF/omni-settings");

		Map<String, String> dataSourceProperties = loadXMLPropertiesStagedFromClassPath(
			fileName,
			omniSettings.getOrDefault("stageSystemPropertyName", "omni.stage"),
			omniSettings.get("defaultStage"));

		logger.info(
			"${symbol_escape}n${symbol_escape}nAbout to install DataSource. ${symbol_escape}n" +
			"Classname: " + dataSourceProperties.get("className") + "${symbol_escape}n" +
			"URL: " + dataSourceProperties.getOrDefault("url", dataSourceProperties.get("URL") + "${symbol_escape}n" +
			"See META-INF/conf/" + fileName + " for details. ${symbol_escape}n" +
			"${symbol_escape}n${symbol_escape}n")
		);

		return dataSourceProperties;

	}

}