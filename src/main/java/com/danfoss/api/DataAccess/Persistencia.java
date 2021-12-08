package com.danfoss.api.DataAccess;

import java.util.HashMap;

public class Persistencia {

    private String conName;

    public Persistencia(String conName)
    {
        this.conName = conName;
    }

    public Persistencia ()
    {
        this("ConexionPrincipal");
    }

//    public void SetAutoCommit(boolean autoCommit) throws Exception
//    {
//        DataAccess.GetInstancia(this.conName).SetAutoCommit(autoCommit);
//    }

//    public void Commit() throws Exception
//    {
//        DataAccess.GetInstancia(this.conName).Commit();
//    }

//    public void Rollback() throws Exception
//    {
//        DataAccess.GetInstancia(this.conName).Rollback();
//    }

    public DataTable Query(String store) throws Exception
    {
        return DataAccess.GetInstancia(this.conName).Query(store);
    }

    public DataTable Query(String store, HashMap<String, Object> params) throws Exception
    {
        return DataAccess.GetInstancia(this.conName).Query(store, params);
    }

//    public int Scalar(String sql, HashMap<String, Object> params) throws Exception
//    {
//        return DataAccess.GetInstancia(conName).Scalar(sql, params);
//    }

    public int ExceuteNonQuery(String sql, HashMap<String, Object> params) throws Exception
    {
        return DataAccess.GetInstancia(conName).ExecuteNonQuery(sql, params);
    }
}
