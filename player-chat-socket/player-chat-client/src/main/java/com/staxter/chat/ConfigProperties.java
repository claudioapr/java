package com.staxter.chat;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class to hold the system properties
 * 
 * @author cresende
 */
public final class ConfigProperties
{

    /**
     * properties
     */
    private static Properties properties;


    /**
     * Creates a new instance of ConfigProperties.
     */
    private ConfigProperties()
    {
    }


    /**
     * read the properties file
     */
    static
    {
        loadProperties();
    }


    /**
     * Gets the properties by name
     *
     * @param property name
     * @return property value
     */
    public static String getProperty(String property)
    {
        return properties.getProperty(property);
    }

    /**
     * Gets the properties by name
     *
     * @param property name
     * @return property value
     */
    public static String getProperty(String property, String defaultValue)
    {
        String prop = properties.getProperty(property);
        if (prop != null && !prop.isEmpty())
        {
            return prop;
        }
        return defaultValue;
    }

    /**
     * Loads the properties from the file
     */
    private static void loadProperties()
    {
        properties = new Properties();

        try (InputStream input = ConfigProperties.class.getClassLoader().getResourceAsStream("config.properties"))
        {
            if (input == null)
            {
                return;
            }

            properties.load(input);

        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
