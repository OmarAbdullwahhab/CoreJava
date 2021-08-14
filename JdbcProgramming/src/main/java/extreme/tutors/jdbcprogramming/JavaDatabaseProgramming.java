/*
 * The MIT License
 *
 * Copyright 2021 Omar Abdullwahhab.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package extreme.tutors.jdbcprogramming;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Omar Abdullwahhab
 */
public class JavaDatabaseProgramming {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        Connection c = DerbyConnectionManager.getInstance().getConnection();
        try (Statement stmt = c.createStatement()) {
            printPersonsList(selectAll(stmt));
        }
        DerbyConnectionManager.getInstance().closeConnection();
        
    }

    static void printPersonsList(List<Person> persons) {
        for (Person p : persons) {
            System.out.println(p.toString());
            
        }
    }
    static List<Person> selectAll(Statement stmt) throws SQLException
    {
        List<Person> allPersons = new ArrayList<>();
        try (ResultSet persons = stmt.executeQuery("SELECT * FROM PERSONS")) {
           
            while (persons.next()) {
                Person p = new Person();
                p.setId(persons.getInt("ID"));
                p.setFirstName(persons.getString("FIRST_NAME"));
                p.setLastName(persons.getString("LAST_NAME"));
                p.setDateOfBirth(persons.getDate("DOB"));
                allPersons.add(p);
            }
        }
        return allPersons;
    }
    static void create(Statement stmt, Person p) throws SQLException
    {
        stmt.executeUpdate("insert into PERSONS(ID,FIRST_NAME,LAST_NAME,DOB)"
                + " VALUES (" + p.getId() + ",'" + p.getFirstName() + "',"
                + "'" + p.getLastName() + "','"
                + p.getDateOfBirth().toLocalDate()
                        .format(DateTimeFormatter.ISO_DATE) + "')");
    }
    static void update(Statement stmt,int id,Person p) throws SQLException
    {
        stmt.executeUpdate("update PERSONS set FIRST_NAME ="
                + "'" + p.getFirstName() + "',LAST_NAME="
                + "'" + p.getLastName() + "',DOB='"
                + p.getDateOfBirth().toLocalDate()
                   .format(DateTimeFormatter.ISO_DATE) + "' where ID = " + id);
    }
    static void delete(Statement stmt,int id) throws SQLException
    {
        stmt.executeUpdate("delete from PERSONS where ID = " + id);
    }
    

}
