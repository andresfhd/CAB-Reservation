/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geometrik.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andreshurtadodiez
 */
public class Utilities {
    
    private static Properties properties;
    
    public enum PropertyKeys {
        HOST, PORT
    }
    
    public static Properties loadPropertiesFile() {
        Properties propertiesAux = new Properties();
        try {
            InputStream resouces = Utilities.class.getClassLoader().getResourceAsStream("/META-INF/cab_reservation.properties");
            propertiesAux.load(resouces);
            
        } catch (IOException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return propertiesAux;
    }
    
    public static String getPropetyValue(PropertyKeys propertyKey) {
        if (properties == null) {
             properties = loadPropertiesFile();
        }
        return properties.getProperty(propertyKey.toString());
    }
    
}
