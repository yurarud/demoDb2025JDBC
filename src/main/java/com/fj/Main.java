package com.fj;


import java.sql.*;

public class Main {
    private static final String url = "jdbc:postgresql://localhost:5432/test_db";
    private static final String user = "***";
    private static final String password = "***";

    private static Connection con ;
    private static Statement stmt ;
    private static ResultSet rs ;

    public static void main(String[] args) {
        System.out.println("Hello world!");
        String query = "select * from books" ;
        try {
            con = DriverManager.getConnection ( url , user , password );
            stmt = con.createStatement ();
            rs = stmt.executeQuery ( query );
            while (rs.next( )) {
                int count = rs.getInt ( "id" );
                String title = rs.getString ( "title" );
                int author_id = rs.getInt ( "author_id" );
                System.out.println ( "id record: " + count+", title: " + title + ", author_id: " + author_id );
            }
            rs.close();
            stmt.close();
            System.out.println("_______________________");
            String str1 = "Select * from books \n" +
                    "where author_id=?" ;
            PreparedStatement pstmt = con.prepareStatement(str1);
            pstmt.setInt(1,1);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int count = rs.getInt ( "id" );
                String title = rs.getString ( "title" );
                int author_id = rs.getInt ( "author_id" );
                System.out.println ( "id record: " + count+", title: " + title + ", author_id: " + author_id );
            }
            rs.close();
            pstmt.close();
            System.out.println("_______________________");
            String str2= "Select authors.name, books.title from authors,books\n" +
                    "where books.author_id=authors.id" ;
            rs = con.prepareStatement(str2).executeQuery();
            while (rs.next()) {
                String name = rs.getString ( "name" );
                String title = rs.getString ( "title" );
                System.out.println ( "author: " + name + ", title: " + title );
            }
        } catch ( SQLException sqlEx ) {
            sqlEx.printStackTrace ();
        } finally {
            try { con . close (); } catch ( SQLException se ) { /*can't do anything */ }
            try { stmt . close (); } catch ( SQLException se ) { /*can't do anything */ }
            try { rs . close (); } catch ( SQLException se ) { /*can't do anything */ }
        }


    }
}
