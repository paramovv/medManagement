package org.perscholas.services;

import org.perscholas.dao.IFmemberRepo;
import org.perscholas.dao.IMedRepo;
import org.perscholas.models.Fmember;
import org.perscholas.models.Medication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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


    public boolean addFmemberToMedication(Long fid, Long mid) {
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
        Fmember dbFmember = fmemberRepo.getById(fmember.getFId());
        List<Medication> addedMedications = fmember.getFmedications();
        List<Medication> currentMedications = dbFmember.getFmedications();
        currentMedications.addAll(addedMedications);
        dbFmember.setFmedications(currentMedications);
        fmemberRepo.save(dbFmember);
        return dbFmember;
    }
}
