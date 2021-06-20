
package org.perscholas.controllers;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.models.Fmember;
//import org.perscholas.services.FileService;
import org.perscholas.services.FmemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping(path="/new-fmember")
@SessionAttributes("fmember")
public class RegisterNewFmemberController {

    private final FmemberService fmemberService;
    //private final FileService fileService;

    @Autowired
    public RegisterNewFmemberController(FmemberService fmemberService){//, FileService fileService) {
        this.fmemberService = fmemberService;
        //this.fileService = fileService;
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("fmember", new Fmember());
        log.warn("created new fmamber object");
        return "new-fmember";
    }

    @PostMapping("/register")
    public String addFmember(
            @Valid @ModelAttribute("fmember") Fmember fmember, BindingResult result, Model model){
          // @RequestParam("file") MultipartFile file) {
        if (result.hasErrors()) {
            return "new-fmember";
        }

        if (fmemberService.checkIfFmemberExists(fmember.getFusername(),fmember.getFpassword())) {
            model.addAttribute("error", "You are already in the system. Please login.");
            log.warn("checkIfFmemberExists");
            return "redirect:/home";
        }

       // fmember.setFmemberImage(fileService.uploadFile(file));
        log.warn("addFmember");
        fmemberService.addFmember(fmember);
        model.addAttribute("success", "Account created.");
        model.addAttribute("fmember", fmember);
        return "redirect:/register";
    }
}

