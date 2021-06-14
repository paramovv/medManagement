package org.perscholas.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity

public class Fmember implements Serializable {
    static final long serialVersionUID = 6381462249347345007L;

    @Fetch(FetchMode.JOIN)
    @ManyToMany(targetEntity = Medication.class)
    List<Medication> fmedication;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long fId;

    @NotBlank(message = "Please enter a name")
    @Length(max = 25, message = "Max name length is 25 characters")
    String fusername;

    @NotBlank(message = "Please enter a password")
    @Pattern(
            regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$",
            message =
                    "- at least 8 characters\n"
                            + "- must contain at least 1 uppercase letter, 1 lowercase letter, and 1 number\n"
                            + "- Can contain special characters")
    String fpassword;
   // private String fmemberImage;

    public Fmember(String name, String password){
        fusername = name;
        fpassword = password;
        fmedication = new ArrayList<>();
    }

    public void addMedication(Medication medication) {
        this.fmedication.add(medication);
    }

    @Override
    public String toString() {
        return "Fmember{" +
                " fusername='" + fusername + '\'' +
                ", fpassword='" + fpassword + '\'' +
                '}';
    }



}
