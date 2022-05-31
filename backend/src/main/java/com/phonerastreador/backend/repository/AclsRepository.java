package com.phonerastreador.backend.repository;

import com.phonerastreador.backend.model.Acls;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AclsRepository extends JpaRepository<Acls, Long> {
    
}
