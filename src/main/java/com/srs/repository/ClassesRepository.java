package com.srs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srs.entity.ClassesEntity;

@Repository
public interface ClassesRepository extends JpaRepository<ClassesEntity, String>{

}
