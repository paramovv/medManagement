package org.perscholas.controllers;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.models.Fmember;
import org.perscholas.models.Medication;
import org.perscholas.services.FmemberService;
import org.perscholas.services.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/register")
@SessionAttributes({"fmember", "currentmedications"})
public class MedicationRegistrationController {
    private final MedicationService medicationService;
    private final FmemberService fmemberService;
    @Autowired
    public MedicationRegistrationController(MedicationService medicationService, FmemberService fmemberService) {
        this.medicationService = medicationService;
        this.fmemberService = fmemberService;
        log.warn("MedicationRegistrationController");
    }
    @ModelAttribute
   public void addMedicationsToModel(Model model) {
        log.warn("addMedicationsToModel");
        List<Medication> medications = medicationService.getAllMedications();
        // model.addAttribute("medications", filterCurrentMedications(medicationService.getAllMedications(), fmember.getFmedications()));
      //инициализирует лекарства при открытии формы
       model.addAttribute("medications", medications);
    }

      @GetMapping
    public String showRegistrationForm(){
          log.warn(" showRegistrationForm");
        return "register";
    }

    @PostMapping
    public String processFmemberRegistration(Fmember fmember, Model model) {
        Fmember updatedFmember = fmemberService.updateFmember(fmember);
        //отвечает за новые лекарства и из обновляет
       model.addAttribute("currentmedications", updatedFmember.getFmedications());
        //отвечает за доступные лекарства
        model.addAttribute("medications", filterCurrentMedications(medicationService.getAllMedications(), fmember.getFmedications()));
        return "finalize";
    }

    private Iterable<Medication> filterCurrentMedications(
            List<Medication> allMedications, List<Medication> fmedications) {
        log.warn("filterCurrentMedications");
        if (fmedications == null || fmedications.size() == 0) {
            log.warn("new allMedications");
            return allMedications;
        }
        log.warn("allMedications.stream");
        return allMedications.stream()
                .filter(medication -> !fmedications.contains(medication))
                .collect(Collectors.toList());
    }
}
