/*
 * The MIT License
 *
 * Copyright 2012 Manuel Gauto.
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
package com.manuwebdev.grevert.MYSQL;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;

/**
 *
 * @author Manuel Gauto
 */
public class MYSQLActions {

    /**
     * Get MYSQL Connections
     */
    MYSQLInterface mysqlInterface;
    /**
     * Table Name
     */
    private String TABLE_NAME = "";
    /**
     * Logger
     */
    private Logger log;

    /**
     *
     * @param mysqlinterface
     */
    public MYSQLActions(MYSQLInterface mysqlinterface, Logger log,String TABLE) {
        this.mysqlInterface = mysqlinterface;
        this.log = log;
        TABLE_NAME=TABLE;
        createTableIfNeeded();
    }

    public void addDestroyedBlock(Block b) {
         /*
         * 1-Change Type(CHANGE_TYPE)   String 
         * 2-Block ID(BLOCK_ID)         int 
         * 3-Home X Coordinate(X)       int 
         * 4-Home Y Coordinate(Y)       int 
         * 5-Home Z Coordinate(Z)       int 
         * 6-Home World(WORLD)          String
         */
        final String QUERY = "INSERT INTO " + TABLE_NAME + " VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = (PreparedStatement) mysqlInterface.getMYSQLConnection().prepareStatement(QUERY);

            ps.setString(1, "DESTROY");
            ps.setInt(2, b.getTypeId());
            ps.setInt(3, b.getX());
            ps.setInt(4, b.getY());
            ps.setInt(5, b.getZ());
            ps.setString(6, b.getLocation().getWorld().getName());
            ps.execute();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, ChatColor.DARK_RED + "Internal MYSQL Error: addDestroyedBlock");
        }

    }

    public void addPlacedBlock(Block b) {
        /*
         * 1-Change Type(CHANGE_TYPE)   String 
         * 2-Block ID(BLOCK_ID)         int 
         * 3-Home X Coordinate(X)       int 
         * 4-Home Y Coordinate(Y)       int 
         * 5-Home Z Coordinate(Z)       int 
         * 6-Home World(WORLD)          String
         */
        final String QUERY = "INSERT INTO " + TABLE_NAME + " VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = (PreparedStatement) mysqlInterface.getMYSQLConnection().prepareStatement(QUERY);

            ps.setString(1, "PLACED");
            ps.setInt(2, b.getTypeId());
            ps.setInt(3, b.getX());
            ps.setInt(4, b.getY());
            ps.setInt(5, b.getZ());
            ps.setString(6, b.getLocation().getWorld().getName());
            ps.execute();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, ChatColor.DARK_RED + "Internal MYSQL Error: addPlacedBlock");
        }

    }

    public void removeChangeLog() {
        final String QUERY = "DELETE FROM " + TABLE_NAME;
        try {
            PreparedStatement ps = (PreparedStatement) mysqlInterface.getMYSQLConnection().prepareStatement(QUERY);
            ps.execute();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, ChatColor.DARK_RED + "Internal MYSQL Error: removeChangeLog");
        }

    }

    public boolean doesTableExist(String Table) {
        try {
            DatabaseMetaData dbm = mysqlInterface.getMYSQLConnection().getMetaData();
            // check if table is there
            ResultSet tables = dbm.getTables(null, null, Table, null);
            if (tables.next()) {
                // Table exists
                return true;
            } else {
                // Table does not exist
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MYSQLActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void createTableIfNeeded() {
         /*
         * 1-Change Type(CHANGE_TYPE)   String 
         * 2-Block ID(BLOCK_ID)         int 
         * 3-Home X Coordinate(X)       int 
         * 4-Home Y Coordinate(Y)       int 
         * 5-Home Z Coordinate(Z)       int 
         * 6-Home World(WORLD)          String
         */
        
        if (doesTableExist(TABLE_NAME) == false) {
            try {
                Statement stmt = mysqlInterface.getMYSQLConnection().createStatement();

                String sql = "CREATE TABLE " + TABLE_NAME + "("
                        + "CHANGE_TYPE      VARCHAR(254), "
                        + "BLOCK_ID         INTEGER, "
                        + "X                INTEGER, "
                        + "Y                INTEGER, "
                        + "Z                INTEGER, "
                        + "WORLD            VARCHAR(254))";

                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                //NOM NOM NOM
            }
        }
    }
}
