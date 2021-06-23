
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
        import java.util.Set;
        import java.util.stream.Collectors;

@Slf4j
@Controller
@SessionAttributes({"fmember", "currentmedications"})

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
        List<Medication> medications = medicationService.getAllMedications();
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
//controller to delete medication. It reseived medication id parameter from HTML page and invoke method on service
    @PostMapping("/delete-fmedications/{cid}")
    public String deleteFmedication(@PathVariable("cid") long cid, Fmember fmember, Model model) {
        Fmember removeMedication = fmemberService.removeMedication(fmember,cid);
        model.addAttribute("currentmedications", removeMedication.getFmedications());
        model.addAttribute("medications", filterCurrentMedications(medicationService.getAllMedications(), fmember.getFmedications()));
        model.addAttribute("fusername",fmember.getFusername());
        return "finalize";
    }

    @GetMapping("/edit-fmedications/{cid}")
    public String editFmedication(@PathVariable("cid") long cid, Fmember fmember, Model model) {
Medication cmedication = medicationService.medicationById(cid);
model.addAttribute("cmedication", cmedication);
        return "editMedication";
    }

    @PostMapping("/finalEdit")
    public String finalEdit(Fmember fmember, Model model) {
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