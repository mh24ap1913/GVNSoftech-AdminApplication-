package in.co.brings.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetConfigValues {
	
	Properties prop = new Properties();
	InputStream input = null;
	public String getQueryFromConfig(String key) throws IOException
	{
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		 input = classLoader.getResourceAsStream("config.properties");


		// load a properties file
		prop.load(input);
		return (String) prop.get(key);
	}

}
