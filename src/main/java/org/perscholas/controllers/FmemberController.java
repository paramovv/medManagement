/*
package org.perscholas.controllers;

import org.perscholas.models.Fmember;
import org.perscholas.services.FmemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("fmember")
public class FmemberController {
    private final FmemberService fmemberService;

    public FmemberController(FmemberService fmemberService) {
        this.fmemberService = fmemberService;
    }

    @GetMapping("/register")
    public String showRegisterFmemberForm(Model model) {
        model.addAttribute("student", new Fmember());
System.out.println("!!!!!!");
        return "new-fmember";
    }

    @GetMapping("/homepage")
    public String showHomepage() {
        return "home";
    }
}
*/
