package spp.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import spp.models.Inspection;
import spp.models.InspectionDao;

/**
 * A class to test interactions with the MySQL database using the UserDao class.
 *
 * @author spp
 */
@Controller
public class InspectionController {

  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
    /**
     *
     * @param name
     * @param price
     * @param period
     * @return
     */
  @RequestMapping("/inspection/create")
  @ResponseBody
  public String create(String name, int price, int period) {
    Inspection inspection = null;

    try {
      inspection = new Inspection(name, price, period);
      inspectionDao.save(inspection);

    }
    catch (Exception ex) {
      return "Error creating the inspection: " + ex.toString();
    }
    return "Inspection succesfully created! (id = " + inspection.getId() + ")";
  }

  @RequestMapping("/inspections")
  public String inspections(Model model) {
    List<Inspection> inspectionsList = null;
    try {
      inspectionsList = (List<Inspection>) inspectionDao.findAll();
    }
    catch (Exception ex) {
      // here you should handle unexpected errors
      // ...
      // throw ex;
    }
    model.addAttribute("inspectionsList", inspectionsList);
    return "inspections";
  }

    @Autowired
    private InspectionDao inspectionDao;
} // class UserController
