package org.sunbird.common.models.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class will be used to read cassandratablecolumn properties file.
 *
 * @author Amit Kumar
 */
public class CassandraPropertyReader {

  private final Properties properties = new Properties();
  private static final String file = "cassandratablecolumn.properties";
  private static CassandraPropertyReader cassandraPropertyReader = null;

  /** private default constructor */
  private CassandraPropertyReader() {
    InputStream in = this.getClass().getClassLoader().getResourceAsStream(file);
    try {
      properties.load(in);
    } catch (IOException e) {
      ProjectLogger.log("Error in properties cache", e);
    }
  }

  public static CassandraPropertyReader getInstance() {
    // change the lazy holder implementation to simple singleton implementation ...
    if (null == cassandraPropertyReader) {
      synchronized (CassandraPropertyReader.class) {
        if (null == cassandraPropertyReader) {
          cassandraPropertyReader = new CassandraPropertyReader();
        }
      }
    }
    return cassandraPropertyReader;
  }

  /**
   * Method to read value from resource file .
   *
   * @param key
   * @return
   */
  public String readProperty(String key) {
    return properties.getProperty(key) != null ? properties.getProperty(key) : key;
  }
}
