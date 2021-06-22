package org.perscholas.models;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
@Slf4j
//@Data
@NoArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Component

public class Medication implements Serializable {
    static final long serialVersionUID = 6381462249347345007L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long mid;

    @NonNull
    @NotBlank(message = "Please enter a medication name")
    String name;
    String mdRecommendations;

    public void setMid(Long mid) {
        this.mid = mid;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setMdRecommendations(String mdRecommendations) {
        this.mdRecommendations = mdRecommendations;
    }
    public void setMedMember(List<Fmember> medMember) {
        this.medMember = medMember;
    }
    public static Logger getLog() {
        return log;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public String getMdRecommendations() {
        return mdRecommendations;
    }

    public List<Fmember> getMedMember() {
        return medMember;
    }

    public Long getMid() {
        return mid;
    }

    public Medication(String name, String mdRecommendations){
        log.warn("medication constructor");
        this.name= name;
        this.mdRecommendations = mdRecommendations;
    }
    @Fetch(FetchMode.JOIN)
    @ManyToMany(mappedBy = "fmedications", targetEntity = Fmember.class)
    private List<Fmember> medMember;

    @OneToMany(targetEntity = Mdetails.class)
    private List<Mdetails> mdetails;


    public Long getmid() {
        return this.mid;
    }
    public List<Mdetails> getMdetails(){return mdetails;}
    public void addFmember(Fmember fmember){
        log.warn("medication addFmember");this.medMember.add(fmember);}

}
