package com.srs.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "SCORE_GRADE")
public class ScoreGradeEntity {

	@Column(name = "SCORE")
	private Long score;
	
	@Column(name = "LGRADE")
	private String lGrade;
	
	
	
}
