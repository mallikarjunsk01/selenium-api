package com.example.framework.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbUtils {
    private final String url;
    private final String user;
    private final String pass;

    public DbUtils(String url, String user, String pass) {
        this.url = url; this.user = user; this.pass = pass;
    }

    public List<String[]> query(String sql) {
        try (Connection con = DriverManager.getConnection(url, user, pass);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            int cols = rs.getMetaData().getColumnCount();
            List<String[]> out = new ArrayList<>();
            while (rs.next()) {
                String[] row = new String[cols];
                for (int i = 1; i <= cols; i++) row[i-1] = rs.getString(i);
                out.add(row);
            }
            return out;
        } catch (SQLException e) {
            throw new RuntimeException("DB query failed", e);
        }
    }

    public int execute(String sql) {
        try (Connection con = DriverManager.getConnection(url, user, pass);
             Statement st = con.createStatement()) {
            return st.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException("DB execute failed", e);
        }
    }
}
