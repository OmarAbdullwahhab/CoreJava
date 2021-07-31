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
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Omar Abdullwahhab
 */
public class MySqlConnectionManager {

    private static MySqlConnectionManager INSTANCE;
    //mysql props.
    private final String url = "jdbc:mysql://localhost:3306/tutor_db";
    private final String user = "root";
    private final String password = "root";

    private Connection dbConnection;

    public static synchronized MySqlConnectionManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MySqlConnectionManager();
        }
        return INSTANCE;
    }
    private void openConnection() {
        //connect to the required database.
         try {
            dbConnection
                    = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.err.println("Exception in connecting to mysql database");
            System.err.println(ex.getMessage());
        }
    }
    /**
     * closes the current connection.
     */
    public void closeConnection() {
        try {
            //check if the connection is not null close it and make it null.
            if (this.dbConnection != null) {
                this.dbConnection.close();
                this.dbConnection = null;
            }
        } catch (SQLException ex) {
            System.err.println("Exception in closing the connection");
            System.err.println(ex.getMessage());
        }
    }
    
    public Connection getConnection() {
        if(this.dbConnection == null)
        {
            this.openConnection();
        }
        return this.dbConnection;
    }
}
