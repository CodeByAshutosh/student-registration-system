package com.srs.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.srs.request.StudentsRequest;

class StudentRowMapper implements RowMapper<StudentsRequest> {
    @Override
    public StudentsRequest mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new StudentsRequest(
        		resultSet.getString("bNumber"),
        		resultSet.getString("firstName"),
				 resultSet.getString("lastName"),
				 resultSet.getString("stLevel"),
				 resultSet.getString("gpa"),
				 resultSet.getString("email"),
				 resultSet.getString("bdate")
        );
    }
}