package com.smart.incubator.view;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller("devicesController")
public class DevicesController {
    private static final Logger LOG = Logger.getLogger(DevicesController.class);

    private final String USER_AGENT = "Mozilla/5.0";


    @RequestMapping(value = {"/devices/cooling/", "/devices/cooling"}, method = RequestMethod.GET)
    public String coolingDevices(ModelMap model) {
        try {
            sendGetRequest("http://192.168.1.177/?temp_low=03.01&temp_high=06.23&hum_low=38.21&hum_high=56.02&engine=1");
            return "redirect:/dashboard";
        } catch (Exception e) {
            LOG.error(e);
            return "redirect:/dashboard";
        }
    }

    @RequestMapping(value = {"/devices/heating/", "/devices/heating"}, method = RequestMethod.GET)
    public String heatingDevices(ModelMap model) {
        try {
            sendGetRequest("http://192.168.1.177/?temp_low=11.01&temp_high=22.23&hum_low=38.21&hum_high=56.02&engine=1");
            return "redirect:/dashboard";
        } catch (Exception e) {
            LOG.error(e);
            return "redirect:/dashboard";
        }
    }

    private void sendGetRequest(final String req_url) throws Exception {
        String inputLine = null;
        URL url = null;
        HttpURLConnection httpURLConnection = null;


        url = new URL(req_url);
        httpURLConnection = (HttpURLConnection) url.openConnection();

        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);


        int responseCode = httpURLConnection.getResponseCode();


        BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();
    }
}
