package org.perscholas.dao;

import org.perscholas.models.Fmember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFmemberRepo extends JpaRepository<Fmember,Long> {
    Fmember findByFusername(String name);

    boolean existsByFusernameAndFpassword(String name, String password);

  }
