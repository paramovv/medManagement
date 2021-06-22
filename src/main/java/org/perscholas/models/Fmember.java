package org.perscholas.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;
import org.slf4j.Logger;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Slf4j
public class Fmember implements Serializable {
    static final long serialVersionUID = 6381462249347345007L;

    @Fetch(FetchMode.JOIN)
    @ManyToMany(targetEntity = Medication.class)
    List<Medication> fmedications;

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

    public static Logger getLog() {
        return log;
    }
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    public List<Medication> getFmedications() {
        return fmedications;
    }
    public String getFusername() {
        return fusername;
    }
    public String getFpassword() {
        return fpassword;
    }
    public Long getfId() {
        return fId;
    }

    public Fmember(String name, String password){
        fusername = name;
        fpassword = password;
        fmedications = new ArrayList<>();
    }

    public void addMedication(Medication medication) {
        log.warn("fmember addMedication");
        this.fmedications.add(medication);
    }

    public void setFmedications(List<Medication> fmedications) {
        this.fmedications = fmedications;
    }
    public void setfId(Long fId) {
        this.fId = fId;
    }
    public void setFusername(String fusername) {
        this.fusername = fusername;
    }
    public void setFpassword(String fpassword) {
        this.fpassword = fpassword;
    }
}
