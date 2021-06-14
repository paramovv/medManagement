package org.perscholas.dao;

import org.perscholas.models.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMedRepo extends JpaRepository<Medication,Long> {
}
