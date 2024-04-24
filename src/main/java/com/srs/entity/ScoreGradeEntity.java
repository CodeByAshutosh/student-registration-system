package com.srs.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "SCORE_GRADE")
public class ScoreGradeEntity {

	@Id
	@Column(name = "SCORE")
	private Long score;
	
	@Column(name = "LGRADE")
	private String lGrade;
	
	
	
}
