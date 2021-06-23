package org.perscholas.controllers;

import org.perscholas.models.Medication;
import org.perscholas.models.Fmember;
import org.perscholas.services.FmemberService;
import org.perscholas.services.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller

@SessionAttributes({"fmember", "currentMedications"})
public class AdminController {
    private final MedicationService medicationService;
    private final FmemberService fmemberService;
    @Autowired
    public AdminController( MedicationService medicationService, FmemberService fmemberService) {
        this.medicationService = medicationService;
        this.fmemberService = fmemberService;
    }
    @GetMapping("/")
    public String getString()
    {
        return "login";
    }
    @GetMapping("home")
    public String showHomePage()
    {
        return "home";
    }
    @GetMapping("all-medications")
    public String showMedications(Model model) {
        List<Medication> medications = medicationService.getAllMedications();
        model.addAttribute("medications", medications);

        return "all-medications";
    }
    @GetMapping("all-fmembers")
    public String showFmembers(Model model) {
        Iterable<Fmember> fmembers = fmemberService.getAllFmembers();
        model.addAttribute("fmembers", fmembers);
        return "all-fmembers";
    }

   @GetMapping("fmember-info")
    public String getFmember() {
        return "fmember-info";
    }
    @PostMapping("/lookup")
    public String displayInfo(@ModelAttribute("name") String name, Model model) {
        Fmember fmember = fmemberService.getFmemberByName(name);
        model.addAttribute("fmember", fmember);

        return "fmember-info";
    }
}
