package org.perscholas.models;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.slf4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
@Slf4j
//@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Component
public class Mdetails implements Serializable {
    static final long serialVersionUID = 6381462249347345007L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long did;
     String dnotes;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dstart;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dend;
    Integer ddosage;
    Integer dfrequency;

    public Mdetails(String dnotes, Date dstart, Date dend, Integer ddosage, Integer dfrequency, Integer drefils, String ddosunit, Date dtime, Medication medisationD) {
        this.dnotes = dnotes;
        this.dstart = dstart;
        this.dend = dend;
        this.ddosage = ddosage;
        this.dfrequency = dfrequency;
        this.drefils = drefils;
        this.ddosunit = ddosunit;
        this.dtime = dtime;
        this.medicationD = medisationD;
    }

    Integer drefils;
    String ddosunit;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date dtime;

    @ManyToOne
    private Medication medicationD;

    // @JoinColumn(name="d_mid",referencedColumnName = "did")

    public Mdetails(String dnotes, Date dstart, Date dend, Integer ddosage, Integer dfrequency, Integer drefils, String ddosunit, Date dtime) {
        this.dnotes = dnotes;
        this.dstart = dstart;
        this.dend = dend;
        this.ddosage = ddosage;
        this.dfrequency = dfrequency;
        this.drefils = drefils;
        this.ddosunit = ddosunit;
        this.dtime = dtime;
    }

    public static Logger getLog() {
        return log;
    }
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    public Long getDid() {
        return did;
    }

    public String getDnotes() {
        return dnotes;
    }
    public Date getDstart() {
        return dstart;
    }
    public Date getDend() {
        return dend;
    }
    public Integer getDdosage() {
        return ddosage;
    }
    public String getDdosunit() {
        return ddosunit;
    }
    public Date getDtime() {
        return dtime;
    }
    public Integer getDfrequency() {
        return dfrequency;
    }
    public Integer getDrefils() {
        return drefils;
    }
    public void setDid(Long did) {
        this.did = did;
    }

    public void setDnotes(String dnotes) {
        this.dnotes = dnotes;
    }
    public void setDstart(Date dstart) {
        this.dstart = dstart;
    }
    public void setDend(Date dend) {
        this.dend = dend;
    }
    public void setDdosage(Integer ddosage) {
        this.ddosage = ddosage;
    }
    public void setDdosunit(String ddosunit) {
        this.ddosunit = ddosunit;
    }
    public void setDtime(Date dtime) {
        this.dtime = dtime;
    }
    public void setDfrequency(Integer dfrequency) {
        this.dfrequency = dfrequency;
    }
    public void setDrefils(Integer drefils) {
        this.drefils = drefils;
    }
}
