package com.mitrran.msulearningapi.repository;

import com.mitrran.msulearningapi.model.Resources;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resources, Long> {
}
