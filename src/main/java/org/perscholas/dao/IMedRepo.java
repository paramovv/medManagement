package org.perscholas.dao;

import org.perscholas.models.Fmember;
import org.perscholas.models.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMedRepo extends JpaRepository<Medication,Long> {
    Optional<Medication> findByName(String name);


}
