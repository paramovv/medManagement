
package org.perscholas.dao;

import org.perscholas.models.Fmember;
import org.perscholas.models.Medication;
import org.perscholas.services.FmemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DaoConfig {
    @Bean
    public CommandLineRunner dataLoader(IMedRepo medicationRepo, FmemberService fmemberService)
    {
        return args -> {
            medicationRepo.save(new Medication("Tylenol", "Notes for Tylenol"));
            medicationRepo.save(new Medication("Advil","Notes for Advil"));
            medicationRepo.save(new Medication("Zoome", "General notesZoome"));
            medicationRepo.save(new Medication("Medication1", "Recommendation1"));
            medicationRepo.save(new Medication("Medication2", "Recommendation2"));

            fmemberService.addFmember(new Fmember("Linda Smith",  "PasswordLS!2"));
            fmemberService.addFmember(new Fmember("John Vagner",  "PasswordJV!2"));
            fmemberService.addFmember(
                    new Fmember("Sindy Vagner", "PasswordSV!2"));
            fmemberService.addFmember(
                    new Fmember("Eliza Vagner", "PasswordEV!2"));
            //authRepo.save(new AuthGroup("dave@email.com", "ROLE_ADMIN"));
        };
    }
}

