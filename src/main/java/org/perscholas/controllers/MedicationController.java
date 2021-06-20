
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
@SessionAttributes({"fmember", "currentmedications"})
//@RequestMapping("/medications")
public class MedicationController {
    private final MedicationService medicationService;
    private final FmemberService fmemberService;
    @Autowired
    public MedicationController(MedicationService medicationService, FmemberService fmemberService) {
        this.medicationService = medicationService;
        this.fmemberService = fmemberService;
        log.warn("MedicationController");
    }
    @ModelAttribute
    public void addMedicationsToModel(Model model) {
        log.warn("addMedicationsToModel");
        List<Medication> medications = medicationService.getAllMedications();
        // model.addAttribute("medications", filterCurrentMedications(medicationService.getAllMedications(), fmember.getFmedications()));
        model.addAttribute("medications", medications);
    }
    @GetMapping("/current")
    public String currentMedications(Model model) {
        return "medications";
    }

    @PostMapping("/update-fmedications")
        public String updateFmedication(Model model) {

            return "update-fmedications";
        }

    @PostMapping("/delete-fmedications")
    public String deleteFmedication(Fmember fmember, Model model) {
        Fmember removeMedication = fmemberService.removeMedication(fmember);
        model.addAttribute("currentmedications", removeMedication.getFmedications());
        model.addAttribute("medications", filterCurrentMedications(medicationService.getAllMedications(), fmember.getFmedications()));
        model.addAttribute("fusername",fmember.getFusername());
        return "register";
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