package com.smart.incubator.view;

import com.smart.incubator.domain.Mode;
import com.smart.incubator.service.ModeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("dashboard")
public class DashboardController {
    private static final Logger LOG = Logger.getLogger(DashboardController.class);

    @Autowired private ModeService modeService;


    @RequestMapping(value = {"/dashboard", "/"},method = RequestMethod.GET)
    public String dashboard(Model model) {
        final Mode mode = modeService.getModeByActivationStatus(true);

        try {
            model.addAttribute("current_mode", mode.getModeName());
            return "/dashboard";
        } catch (Exception e) {
            LOG.error(e);
            return "/dashboard";
        }
    }
}
