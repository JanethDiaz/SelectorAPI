package com.danfoss.api.DataAccess;

import com.danfoss.api.Utils.HttpHelper;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.*;

public class DataAccess {
    private Connection conection;

//    private static final String url      = "jdbc:mysql://localhost/SelectorAppDB_Dev?serverTimezone=UTC";
    private static final String url      = "jdbc:mysql://204.2.194.52/SelectorAppDB_Dev?serverTimezone=UTC";
    private static final String user     = "root";
    private static final String password = "UeV8zPxBi#";
//    private static final String password = "";

    private DataAccess() throws ClassNotFoundException, SQLException
    {
        this.conection = this.getConnection();
    }

    static DataAccess GetInstancia(String KeyConn) throws Exception
    {
        HttpServletRequest request = HttpHelper.getHttpServletRequest();

        assert request != null;
        if( request.getAttribute( KeyConn ) == null )
            request.setAttribute( KeyConn, new DataAccess() );

        return ( DataAccess ) request.getAttribute( KeyConn );
    }

    private Connection getConnection() throws SQLException, ClassNotFoundException
    {
        Class.forName( "com.mysql.cj.jdbc.Driver" );
        return DriverManager.getConnection( url, user, password);
    }

    private void openConecction() throws SQLException, ClassNotFoundException
    {
        if ( this.conection.isClosed() ) {
            this.conection = this.getConnection();
        }
    }


    int ExecuteNonQuery (String sql, HashMap<String, Object> params) throws  SQLException
    {
        int ret;
        CallableStatement command = null;
        try
        {
            command = preparedQuery(sql, params);
            ret =  command.executeUpdate();
        }
        finally
        {
            if (command != null) {
                try {
                    command.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return ret;
    }

    public  DataTable Query(String sql, HashMap<String, Object> params) throws SQLException
    {
        PreparedStatement ps = null;
        ResultSet         rs = null;
        DataTable dt;
        try
        {
            ps = preparedQuery( sql, params );
            rs = ps.executeQuery();

            dt = DataTable.Fill(rs);
        }
        finally
        {
            closeStatements(ps, rs);
        }

        return dt;
    }
    public DataTable Query(String store) throws SQLException
    {
        DataTable dt;
        PreparedStatement ps = null;
        ResultSet         rs = null;

        try
        {
            ps = preparedQuery(store, new HashMap<>());
            rs = ps.executeQuery();
            dt = DataTable.Fill(rs);
        }
        finally
        {
            closeStatements(ps, rs);
        }

        return dt;
    }

    private void closeStatements(PreparedStatement ps, ResultSet rs)
    {

        if (this.conection != null) {
            try {
                if ( !this.conection.isClosed() )
                {
                    this.conection.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs!= null) {
            try {
                rs.close();
            }
            catch (SQLException e) { e.printStackTrace(); }
        }
    }

    private CallableStatement preparedQuery(String sql, HashMap<String, Object> params ) {

        String sqlCommand = this.FormatStore(sql, params);
        CallableStatement command = null;

        try
        {
            this.openConecction();
            command = conection.prepareCall(sqlCommand);
            ArrayList<String> keys = new ArrayList<>(params.keySet());

            for ( int i = 1; i <=  keys.size(); i++ )
            {
                if ( params.get(String.valueOf(i)) == null )
                    command.setString( i, null );
                else
                    command.setString(i, String.valueOf(params.get(String.valueOf(i))));
            }
        }
        catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return command;
    }

    private String FormatStore(String sql, HashMap<String, Object> params)
    {
        StringJoiner myDate = new StringJoiner(",", "(", ")");

        for (Map.Entry<String, Object> ignored : params.entrySet())
        {
            myDate.add("?");
        }

        return String.format("{%s %s}", sql, myDate);
    }

}
