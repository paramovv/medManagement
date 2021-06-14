package org.perscholas.services;


import org.perscholas.dao.IFmemberRepo;
import org.perscholas.dao.IMedRepo;
import org.perscholas.models.Medication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class MedicationService {

    private final IMedRepo medRepo;

    @Autowired
    public MedicationService(IMedRepo medRepo) {
        this.medRepo = medRepo;
    }

    public List<Medication> getAllMedications() {
        return medRepo.findAll();
    }

    public Medication getCourseById(Long id) {
        return medRepo.getById(id);
    }
}
