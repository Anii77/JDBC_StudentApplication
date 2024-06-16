package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBC_APPLICATION {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/Anii";
        String user = "root";
        String password = "Aniket@77";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();

            // 1. User input --> fetch data.
            // 2. User input --> insert data.

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter 1 for fetching  data...");
            System.out.println("Enter 2 for inserting data...");
            System.out.println("Enetr 3 for multiple  data...");
            System.out.println("Eneter 4 for deleting data...");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    ResultSet fetchData = statement.executeQuery("select * from students");
                    Operations.fetchData(fetchData);
                    break;

                case 2:
                    ResultSet maxIdResultSet = statement.executeQuery("select MAX(st_id) as max_st_id from students");
                    int max_ID = 0;
                    while (maxIdResultSet.next()) {
                        max_ID = maxIdResultSet.getInt("max_st_id");
                        System.out.println("Max Student Id: " + max_ID);
                    }
                    max_ID++;
                    System.out.println("Enter the Name:");
                    String name = scanner.next();
                    System.out.println("Enter the email:");
                    String email = scanner.next();
                    System.out.println("Enter the Phone no:");
                    String phoneNo = scanner.next();

                    // Enclose string values in single quotes
                    String insertQuery = String.format("insert into students values (%d, '%s', '%s', '%s')", max_ID, name, email, phoneNo);
                    int rowCount = statement.executeUpdate(insertQuery);

                    if (rowCount > 0) {
                        System.out.println("Data Inserted..");
                    } else {
                        System.out.println("Data Insertion Failed..");
                    }
                    break;


                case 3:
                    System.out.println("Enter the number of records you want to insert:");
                    int recordCount = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    ResultSet maxIdResultSetBulk = statement.executeQuery("select MAX(st_id) as max_st_id from students");
                    int max_ID_Bulk = 0;
                    if (maxIdResultSetBulk.next()) {
                        max_ID_Bulk = maxIdResultSetBulk.getInt("max_st_id");
                    }
                    max_ID_Bulk++;

                    for (int i = 0; i < recordCount; i++) {


                        System.out.println("Enter the Name for record " + (i + 1) + ":");
                        String nameBulk = scanner.nextLine();
                        System.out.println("Enter the email for record " + (i + 1) + ":");
                        String emailBulk = scanner.nextLine();
                        System.out.println("Enter the Phone no for record " + (i + 1) + ":");
                        String phoneNoBulk = scanner.nextLine();

                        // Enclose string values in single quotes
                        String bulkInsertQuery = String.format("insert into students values (%d, '%s', '%s', '%s')", max_ID_Bulk, nameBulk, emailBulk, phoneNoBulk);
                        statement.addBatch(bulkInsertQuery);
                        max_ID_Bulk++;
                    }

                    int[] batchResult = statement.executeBatch();
                    System.out.println(batchResult.length + " records inserted.");
                    break;

















                    case 4:
                    System.out.println("Enter the id for the delete record..");
                    int id=scanner.nextInt();
                    int row=statement.executeUpdate("DELETE from students where st_id= "+ id);
                    if(row>0)
                    {
                        System.out.println("Data deleted Successfully of ID:"+id);

                    }
                    else {
                        System.out.println("Data Deletion failed");
                    }
                    break;
                default:
                    System.out.println("Enter Valid Input");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
