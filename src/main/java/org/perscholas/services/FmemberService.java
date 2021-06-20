package org.perscholas.services;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.dao.IFmemberRepo;
import org.perscholas.dao.IMedRepo;
import org.perscholas.models.Fmember;
import org.perscholas.models.Medication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Slf4j
@Service
@Transactional
public class FmemberService {

    private final IFmemberRepo fmemberRepo;
    private final IMedRepo medRepo;

    @Autowired
    public FmemberService(IFmemberRepo fmemberRepo,IMedRepo medRepo) {
        this.fmemberRepo = fmemberRepo;
        this.medRepo = medRepo;
    }
    public Iterable<Fmember> getAllFmembers(){return fmemberRepo.findAll();}

    public Fmember getFmemberByName(String name) {
        return fmemberRepo.findByFusername(name);
    }

    public boolean isValid(String name, String password) {
        return fmemberRepo.existsByFusernameAndFpassword(name, password);
    }


   /* public boolean addFmemberToMedication(Long fid, Long mid) {
        log.warn("addFmemberToMedication");
        Fmember fMember = fmemberRepo.getById(fid);
        List<Medication> medications = fMember.getFmedications();
        Medication medication = medRepo.getById(mid);
        if (medications.contains(medication)) {
            return false;
        }
        medications.add(medication);
        fMember.setFmedications(medications);
        fmemberRepo.save(fMember);
        return true;
    }*/
    public List<Medication> getFmemberMedications(Long fId) {
        return fmemberRepo.getById(fId).getFmedications();
    }

    public void addFmember(Fmember fmember) {
        log.warn("save addFmember");
        fmemberRepo.save(fmember);
    }
    public boolean checkIfFmemberExists(String fusername, String fpassword) {
        return fmemberRepo.existsByFusernameAndFpassword(fusername, fpassword);
    }

    public Fmember updateFmember(Fmember fmember) {
       // log.warn("before dbFmember:" );
        Fmember dbFmember = fmemberRepo.getById(fmember.getfId());
        //.warn("after dbFmember:" );
        //список добавленые лекарства
        List<Medication> addedMedications = fmember.getFmedications();
        //список лекарств из базы данных
        List<Medication> currentMedications = dbFmember.getFmedications();
        //добавить новые к существующим в один список
        currentMedications.addAll(addedMedications);
        //set list in view?
        dbFmember.setFmedications(currentMedications);
        //save in DB
        fmemberRepo.save(dbFmember);
        return dbFmember;
    }
    @Transactional
    public Fmember removeMedication(Fmember fmember) {
        Fmember dbFmember = fmemberRepo.getById(fmember.getfId());
        List<Medication> removedMedications = fmember.getFmedications();
        List<Medication> currentMedications = dbFmember.getFmedications();
        currentMedications.remove(removedMedications);
        dbFmember.setFmedications(currentMedications);
        fmemberRepo.save(dbFmember);
        return dbFmember;
    }
}
