package org.perscholas.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
@Slf4j
@Data
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

    public Medication(String name,String mdRecommendations){
        log.warn("medication constructor");
        this.name= name;
        this.mdRecommendations = mdRecommendations;
    }
    @Fetch(FetchMode.JOIN)
    @ManyToMany(mappedBy = "fmedications", targetEntity = Fmember.class)
    private List<Fmember> medMember;

    public void addFmember(Fmember fmember){
        log.warn("medication addFmember");this.medMember.add(fmember);}





}
