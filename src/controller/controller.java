package controller;

import Connection.connectWithDatabase;

import java.sql.*;

public class controller {
    public static String findWord(String text) throws SQLException {
        Connection conn = connectWithDatabase.getConnection();
        assert conn != null;
        Statement stmt = conn.createStatement();
        String sql = "select detail from word where word = ?";

        try{
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, text);
            ResultSet rs = pstm.executeQuery();
            String Detail = "";
            while(rs.next()){
                Detail = rs.getString("detail");
            }
            rs.close();
            stmt.close();
            conn.close();
            return Detail;
        }
        catch (Exception e){
            System.out.println(e);
            return "";
        }
    }

    public static String[] suggestionWord(String text) throws SQLException {
        int dai;
        dai = text.length();
        String[] result = new String[30];
        Connection conn = connectWithDatabase.getConnection();
        assert conn != null;
        Statement stmt = conn.createStatement();
        String sql = "select word from word where substring(word, 1, ?) = ?" + " limit  ?";

        try{
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, dai);
            pstm.setString(2, text);
            pstm.setInt(3, 30);
            ResultSet rs = pstm.executeQuery();
            int i=0;
            while(rs.next()){
                result[i] = rs.getString("word");
                i++;
            }
            return result;
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

}
