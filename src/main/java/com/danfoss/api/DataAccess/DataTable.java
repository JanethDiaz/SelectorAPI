package com.danfoss.api.DataAccess;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataTable {

    static List<String> Columns = new ArrayList<>();
    public List<Map<String, String>> Rows = new ArrayList<>();

    static synchronized DataTable Fill(ResultSet rs) throws SQLException
    {
        SetColumns(rs);

        DataTable dt = new DataTable();
        dt.Llenar(rs);

        return dt;
    }

    private static synchronized void SetColumns(ResultSet rs) throws SQLException
    {
        ResultSetMetaData mtd = rs.getMetaData();

        Columns = new  ArrayList<>(mtd.getColumnCount());

        for(int i = 1; i <= mtd.getColumnCount(); i++)
        {
            Columns.add(mtd.getColumnName(i));
        }
    }

    private synchronized void  Llenar(ResultSet rs) throws SQLException
    {
        Rows = new ArrayList<>();

        while( rs.next() )
        {
            Map<String, String> row = new HashMap<>(Columns.size());
            for(String col : Columns)
            {
                row.put(col, rs.getString(col));
            }

            Rows.add(row);
        }
    }
}
