package org.perscholas.services;

import org.perscholas.dao.IDmedRepo;
import org.perscholas.dao.IFmemberRepo;
import org.perscholas.dao.IMedRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MdetailsService {
    private final IDmedRepo dmedRepo;
    @Autowired
    public MdetailsService(IDmedRepo dmedRepo) {
        this.dmedRepo = dmedRepo;

    }

}
