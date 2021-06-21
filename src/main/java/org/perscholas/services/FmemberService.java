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
    private Object Medication;

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

    public List<Medication> getFmemberMedications(Long fId) {
        return fmemberRepo.getById(fId).getFmedications();
    }

    public void addFmember(Fmember fmember) {
        fmemberRepo.save(fmember);
    }
    public boolean checkIfFmemberExists(String fusername, String fpassword) {
        return fmemberRepo.existsByFusernameAndFpassword(fusername, fpassword);
    }
    public Fmember updateFmember(Fmember fmember) {
        Fmember dbFmember = fmemberRepo.getById(fmember.getfId());
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
    //@Transactional
    public Fmember removeMedication(Fmember fmember,long cid) {
       Long currentmid=cid;
        Medication www= null;
        Fmember dbFmember = fmemberRepo.getById(fmember.getfId());
        List<Medication> removedMedications = fmember.getFmedications();

        for(Medication remMedication : removedMedications) {
            if(remMedication.getMid().equals(currentmid)) {
                www = remMedication;
            }
        }
        List<Medication> currentMedications = dbFmember.getFmedications();
        removedMedications.remove(www);
        dbFmember.setFmedications(removedMedications);
        fmemberRepo.save(dbFmember);
        return dbFmember;
    }
}
