package com.smart.incubator.view;

import com.smart.incubator.domain.Statistic;
import com.smart.incubator.service.StatisticService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

@Controller("statisticController")
public class StatisticController {
    private static final Logger LOG = Logger.getLogger(StatisticController.class);
    private final String ACCESS_TOKEN = "12345678";

    @Autowired
    private StatisticService statisticService;


    // this string need for testing
    // http://localhost:8080/smart_incubator/statistic?temperature_1=12.1&temperature_2=45.2&temperature_3=2.1&humidity_1=50.2&access_token=12345678
    @RequestMapping(value = {"/statistic/", "/statistic"}, method = RequestMethod.GET)
    public void stats(@RequestParam Map<String,String> requestParams, ModelMap model) {
        // if access token from server is valid to add new data into database
        //if (requestParams.get("access_token").equals(ACCESS_TOKEN)) {

        Calendar calendar = Calendar.getInstance();
        Timestamp currentTime = new Timestamp(calendar.getTime().getTime());

        Statistic statistic = new Statistic(
                new Double(requestParams.get("temperature_1")),
                new Double(requestParams.get("temperature_2")),
                new Double(requestParams.get("temperature_3")),
                new Double(requestParams.get("humidity_1")),
                currentTime.toString()
        );

        statisticService.saveStatistic(statistic);
        /*} else {
            LOG.error("Get invalid access token!");
        }*/
    }

    @RequestMapping(value = "/statistic/get", method = RequestMethod.GET)
    @ResponseBody
    public Statistic getStatisticLast(HttpServletResponse response) {
        try {
            Statistic  statistic = null;
            statistic = statisticService.getLastStatistic();

            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type");
            return statistic;
        } catch (Exception e) {
            LOG.error(e);
            return null;
        }
    }
}
