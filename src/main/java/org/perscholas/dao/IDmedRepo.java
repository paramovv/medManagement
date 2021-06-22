package org.perscholas.dao;

import org.perscholas.models.Mdetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDmedRepo extends JpaRepository<Mdetails,Long> {
Optional<Mdetails> findByDnotes(String dnotes);

}
