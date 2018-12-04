/**
 * Assignment:  Program #4
 * Author:  Alex Swindle (aswindle@email.arizona.edu)
 * Grader:  Terrence Lim
 *
 * Course: CSC 460
 * Instructor: L. McCann
 * Due Date: 12/5/2018
 *
 * Description: Creates SQL insert statements from CSV files
 *
 *
 * Language: Java 8
 * External Packages: None
 *
 * Deficiencies: None
 */

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateInserts {

    private static String processField(String field) {
        // Don't change NULLs
        if (field.equals("NULL")) {
            return field;
        }
        // Strings need to be enclosed in quotes, dates need to be converted
        for (int i = 0; i < field.length(); i++) {
            // String
            if (Character.isLetter(field.charAt(i)) || Character.isSpaceChar(field.charAt(i))) {
                return "\'" + field + "\'";
            }
            // Date
            else if (field.charAt(i) == '/') {
                SimpleDateFormat f = new SimpleDateFormat("M/d/yyyy");
				try {
					Date d = f.parse(field);
					long milliseconds = d.getTime();
					return String.valueOf(milliseconds);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				//return "TO_DATE('" + field + "', 'MM/DD/YYYY')";
            }
        }
        // Numbers are left unchanged
        return field;
    }

    private static void createData(String tablename) {
        System.out.println(tablename);
        // Open file
        File input = new File(tablename + ".csv");
        File output = new File(tablename + ".sql");

        BufferedReader br = null;
        PrintWriter writer = null;
        try {
            br = new BufferedReader(new FileReader(input));
            writer = new PrintWriter(output);
            String curLine;
            // Skip header row
            br.readLine();
            // Process each field within each line and put them in an INSERT INTO statement
            while ((curLine = br.readLine()) != null) {
                writer.print("INSERT INTO aswindle." + tablename + " VALUES(");
                String[] fields = curLine.split(",");
                // Separate each field with a comma
                writer.print(processField(fields[0]));
                for (int i = 1; i < fields.length; i++) {
                    writer.print("," + processField(fields[i]));
                }
                writer.print(");\n");
            }
            br.close();
            writer.close();
        }
        catch (IOException e) {
            System.out.println("Error reading lines.");
            System.exit(-1);
        }

    }

    public static void main(String[] args) {
        createData("Patient");
        createData("Doctor");
        createData("Nurse");
        createData("Pharmacist");
        createData("Staff");
        createData("Building");
        createData("Department");
        createData("Room");
        createData("Appointment");
        createData("Pharm_Data");
        createData("Recept_Data");
        createData("Cash_Data");
    }
}
