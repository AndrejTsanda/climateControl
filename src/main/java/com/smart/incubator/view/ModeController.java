package com.smart.incubator.view;

import com.smart.incubator.domain.Mode;
import com.smart.incubator.service.ModeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller("modeController")
public class ModeController {
    private static final Logger LOG = Logger.getLogger(ModeController.class);

    @Autowired private ModeService modeService;


    @RequestMapping(value = "/mode",method = RequestMethod.GET)
    public String mode(Model model) {
        final Mode mode = modeService.getModeByActivationStatus(true);

        try {
            model.addAttribute("current_mode", mode.getModeName());
            model.addAttribute("mode", modeService.getAllMode());
            return "/mode";
        } catch (Exception e) {
            LOG.error(e);
            return "/mode";
        }
    }

    @RequestMapping(value="/mode/add", method = RequestMethod.GET)
    public String add(@ModelAttribute("mode") Mode mode, BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return "redirect:/mode";
        }

        try {
            modeService.saveMode(mode);
            return "redirect:/mode";
        } catch (Exception e) {
            LOG.error(e);
            return "redirect:/mode";
        }
    }

    @RequestMapping(value="/mode/delete/{id}", method=RequestMethod.GET)
    public String delete(@PathVariable Integer id, ModelMap modelMap) {

        try {
            Mode modeForDelete = modeService.getModeById(id);
            if (modeForDelete != null || modeForDelete.getActivationStatus() != true) {
                modeService.deleteMode(id);
            }

            return "redirect:/mode";
        } catch (Exception e) {
            LOG.error(e);
            return "redirect:/mode";
        }
    }

    @RequestMapping(value="/mode/set/active/{id}", method=RequestMethod.GET)
    public String active(@PathVariable Integer id, ModelMap modelMap) {

        try {
            // set old active mode as inactive
            Mode modeSetInactive = modeService.getModeByActivationStatus(true);
            if (modeSetInactive != null) {
                modeSetInactive.setActivationStatus(false);
                modeService.updateMode(modeSetInactive);
            }

            // set new active mode
            Mode modeSetActive = modeService.getModeById(id);
            if (modeSetInactive != null) {
                modeSetActive.setActivationStatus(true);
                modeService.updateMode(modeSetActive);
            }

            return "redirect:/mode";
        } catch (Exception e) {
            LOG.error(e);
            return "redirect:/mode";
        }
    }

    @RequestMapping(value="/mode/set/inactive/{id}", method=RequestMethod.GET)
    public String inactive(@PathVariable Integer id, ModelMap modelMap) {

        try {
            // set old active mode as inactive
            Mode modeSetInactive = modeService.getModeById(id);
            if (modeSetInactive != null) {
                modeSetInactive.setActivationStatus(false);
                modeService.updateMode(modeSetInactive);
            }

            return "redirect:/mode";
        } catch (Exception e) {
            LOG.error(e);
            return "redirect:/mode";
        }
    }
}
