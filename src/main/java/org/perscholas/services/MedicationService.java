package org.perscholas.services;
import org.perscholas.dao.IDmedRepo;
import org.perscholas.dao.IFmemberRepo;
import org.perscholas.dao.IMedRepo;
import org.perscholas.models.Fmember;
import org.perscholas.models.Mdetails;
import org.perscholas.models.Medication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class MedicationService {
    private final IMedRepo medRepo;
    private final IDmedRepo dmedRepo;
    private final IFmemberRepo fmemberRepo;
    private Object Medication;
    @Autowired
    public MedicationService(IFmemberRepo fmemberRepo,IMedRepo medRepo, IDmedRepo dmedRepo) {
        this.fmemberRepo = fmemberRepo;
        this.medRepo = medRepo;
        this.dmedRepo= dmedRepo;
    }

    public Medication updateDetails(Fmember fmember, long cid) {
        Long currentmid=cid;

        Medication dbMedication=medRepo.getById(cid);
        List <Mdetails> currentMdetails =dbMedication.getMdetails();
Medication local = fmember.getFmedications().get((int) cid);

        //список лекарств из базы данных
       // List<Medication> currentMedications = dbFmember.getFmedications();
        //добавить новые к существующим в один список
       // currentMedications.addAll(addedMedications);
        //set list in view?
       // dbFmember.setFmedications(currentMedications);
        //save in DB
       // fmemberRepo.save(dbFmember);
        return dbMedication;
    }

    /*   public List<Mdetails> getMedicationMdetails(Long id){
        return medRepo.getById(id).get*/
   // }
    public List<Medication> getAllMedications() {
        return medRepo.findAll();
    }
    public Medication medicationById(Long id) {
        return medRepo.getById(id);
    }
  /*  @Autowired
    public MedicationService(IMedRepo medRepo) {
        this.medRepo = medRepo;
    }
    public List<Medication> getAllMedications() {
        return medRepo.findAll();
    }
    public Medication medicationById(Long id) {
        return medRepo.getById(id);
    }*/

}
