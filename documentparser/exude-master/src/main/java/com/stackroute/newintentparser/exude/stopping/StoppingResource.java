
package com.stackroute.newintentparser.exude.stopping;

import java.io.InputStream;
import java.util.Properties;

import com.stackroute.newintentparser.exude.common.BaseResource;
import com.stackroute.newintentparser.exude.common.Constants;

public class StoppingResource implements BaseResource {

    private static Properties properties = null;

    private void loadResource() {
        try {
            if (properties == null) {
                properties = new Properties();
                InputStream input = getClass().getClassLoader().getResourceAsStream(Constants.STOPPING_EN_FILE);
                if (input != null) {
                    properties.load(input);
                } else {
                    System.err.println("Stopping word resource file not found");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getProperties(String property) {
        if (properties == null) {
            loadResource();
        }
        return properties.getProperty(property);
    }

}
