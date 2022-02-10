package com.company;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class Main {


    public static void main(String[] args) {
        while (true) {
        Scanner option = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello, Employee! Welcome to the hotel reservation system!");
        System.out.println("1: Create Room");
        System.out.println("2: Create Customer");
        System.out.println("3: Update Customer");
        System.out.println("4: View Taken Room With Customer");
        System.out.println("5: Delete Customer");
        Integer input = option.nextInt();
        if (input == 1) {
            System.out.println("Please insert the room type.");
            String choice = scan.nextLine();
            System.out.println("Please give the room number.");
            Integer number = option.nextInt();
            System.out.println("Please give the cost of the room per night.");
            Integer cost = option.nextInt();
            try (Connection conn = DriverManager.getConnection("jdbc:postgresql:reservation");
                 PreparedStatement stmt = conn.prepareStatement("INSERT INTO ROOMS VALUES (?, ?, ?)");
            ) {
                stmt.setString(1, choice);
                stmt.setInt(2, number);
                stmt.setInt(3, cost);
                stmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (input == 2) {
            System.out.println("Please insert the customers name");
            String name = scan.nextLine();
            System.out.println("Please give the room number they are staying in");
            Integer number = option.nextInt();
            System.out.println("Please give the number of days they are staying");
            Integer days = option.nextInt();
            try (Connection conn = DriverManager.getConnection("jdbc:postgresql:reservation");
                 PreparedStatement stmt = conn.prepareStatement("INSERT INTO CUSTOMERS (customer,room_number,days_staying) VALUES (?, ?, ?)");
            ) {
                stmt.setString(1, name);
                stmt.setInt(2, number);
                stmt.setInt(3, days);
                stmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (input == 3) {
            try (Connection conn = DriverManager.getConnection("jdbc:postgresql:reservation")) {
                Statement statement;
                System.out.println("What is the customer's id?");
                Integer id = option.nextInt();
                System.out.println("How many days are they staying?");
                Integer days = option.nextInt();
                System.out.println("What room number are they staying in now?");
                Integer number = option.nextInt();
                String query=String.format("update CUSTOMERS set days_staying='%s' where id='%s'",days, id);
                String q=String.format("update CUSTOMERS set room_number='%s' where id='%s'",number, id);
                statement=conn.createStatement();
                statement.executeUpdate(query);
                statement.executeUpdate(q);
                System.out.println("Data updated");
            } catch (Exception e){
                System.out.println(e);
            }
            } else if (input == 4) {
            try (Connection conn = DriverManager.getConnection("jdbc:postgresql:reservation");

            ) {
                String query = "SELECT ROOMS.room_number, CUSTOMERS.customer, CUSTOMERS.days_staying, CUSTOMERS.starting_day, CUSTOMERS.id FROM ROOMS INNER JOIN CUSTOMERS ON ROOMS.room_number = CUSTOMERS.room_number";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                System.out.println("Room | Customer | Days Staying | Start Date | ID");
                while (rs.next()) {
                    Integer room_number = rs.getInt("room_number");
                    String customer = rs.getString("customer");
                    Integer days = rs.getInt("days_staying");
                    Date date = rs.getDate("starting_day");
                    Integer id = rs.getInt("id");
                    System.out.println(room_number + "     " + customer+"        "+days + "            " + date + "        " + id);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        } else if (input == 5) {
            try (Connection conn = DriverManager.getConnection("jdbc:postgresql:reservation");

            ) {
                System.out.println("What is the customer's ID?");
                Integer id = option.nextInt();
                String query = String.format("DELETE FROM CUSTOMERS WHERE id = '%s'",id);
                Statement stmt = conn.createStatement();
                stmt.executeQuery(query);
                System.out.println("Data deleted!");
            } catch (SQLException ignored) {
                ;
            }
        }
        }
    }}
