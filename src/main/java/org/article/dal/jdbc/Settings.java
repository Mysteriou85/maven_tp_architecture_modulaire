package org.article.dal.jdbc;

import eu.unareil.dal.DALException;

import java.io.IOException;
import java.util.Properties;

public class Settings {
    private static Properties properties;
    private static void chargement() throws DALException {
        if (properties==null) {
            properties= new Properties();

            try {
                properties.load(Settings.class.getResourceAsStream("settings.properties"));
            } catch (IOException e) {
                throw new DALException("erreur lors du chargements du fichier contenant les informations de connection à la base de données");
            }
        }
    }
    public static String getProperty(String key) throws DALException {
        chargement();
        return properties.getProperty(key);
    }

}
