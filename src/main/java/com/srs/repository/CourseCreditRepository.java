package com.srs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srs.entity.CourseCreditEntity;

@Repository
public interface CourseCreditRepository extends JpaRepository<CourseCreditEntity, Long>{

}
