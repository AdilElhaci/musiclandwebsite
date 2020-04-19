
package dao;

import java.sql.Connection;
import util.Db;

public class Connector {
    
    private Db connector;
    private Connection con;
    
    public Db getConnector() {
        if(connector == null)
            connector = new Db();
        return connector;
    }

    public Connection getCon() {
        if(con == null)
            con = this.getConnector().getConnect();
        return con;
    }
}
