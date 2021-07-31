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
 * @date : 30-07-2021 12:00 AM
 */
public class DerbyConnectionManager {

    private static DerbyConnectionManager INSTANCE;
    //the derby props
    private final String url = "jdbc:derby://localhost:1527/tutor_db";
    private final String user = "app";
    private final String password = "app";

    private Connection dbConnection;

    public static synchronized DerbyConnectionManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DerbyConnectionManager();
        }
        return INSTANCE;
    }

    private void openConnection() {
        //connect to the required database.
        try {
            dbConnection
                    = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.err.println("Exception in connecting to derby database");
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
        if (this.dbConnection == null) {
            this.openConnection();
        }
        return this.dbConnection;
    }
}
