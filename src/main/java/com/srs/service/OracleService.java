package com.srs.service;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import oracle.jdbc.OracleTypes;

@Service
public class OracleService {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

   
    public String[] callProcedure(String packageProcedureCall) {
    	 String[] lines = null;
    	String procedureCall = "{ call ashukla4_proj2_package_spc." + packageProcedureCall + "}";
        
        try (Connection connection = DriverManager.getConnection(url, username, password);
             CallableStatement enableOutput = connection.prepareCall("BEGIN DBMS_OUTPUT.ENABLE(NULL); END;");
             CallableStatement callableStatement = connection.prepareCall(procedureCall);
             CallableStatement getBuffer = connection.prepareCall("BEGIN DBMS_OUTPUT.GET_LINES(?, ?); END;")) {
            
            // Enable server output
            enableOutput.execute();

            // Call the stored procedure
            callableStatement.execute();

            // Prepare to retrieve the server output
            getBuffer.registerOutParameter(1, OracleTypes.ARRAY, "DBMSOUTPUT_LINESARRAY");
            getBuffer.registerOutParameter(2, OracleTypes.INTEGER);
            getBuffer.execute();

            // Retrieve the output
            Array array = getBuffer.getArray(1);
            if (array != null) {
                 lines = (String[]) array.getArray();
                
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Consider using a logger here
        }
       
		return lines;
    }
    
    public static void main(String[] args) {
        String original = "First Name: John";
        int colonIndex = original.indexOf(':');  // Find the index of the colon

        if (colonIndex != -1) {
            // Extract substring after the colon (+1 to skip the colon itself) and trim any leading spaces
            String result = original.substring(colonIndex + 1).trim();
            System.out.println(result);
        } else {
            System.out.println("Colon not found in the string.");
        }
    }
}
