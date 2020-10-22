package me.bluedragonplayz2.dragonassistance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class MsSQL {
    private static final Logger LOGGER = LoggerFactory.getLogger(MsSQL.class);

    public void MsSql() {
        Connection conn = null;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
            conn = DriverManager.getConnection(Config.get("sql_config"));

            if (conn != null) {
                DatabaseMetaData dm = conn.getMetaData();
                LOGGER.info("Driver name: " + dm.getDriverName());
                LOGGER.info("Driver version: " + dm.getDriverVersion());
                LOGGER.info("Product name: " + dm.getDatabaseProductName());
                LOGGER.info("Product version: " + dm.getDatabaseProductVersion());
            }

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            LOGGER.info("SQL ERROR!!!!!!");
            LOGGER.info("shutting down immidiatly");
            System.exit(0);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    LOGGER.info("Sql closed");
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void SQL_test() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(Config.get("sql_config"));
        } catch (SQLException ex) {
            ex.printStackTrace();
            LOGGER.info("SQL ERROR!!!!!!");
            LOGGER.info("shutting down immidiatly");
            System.exit(0);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void SQL_ins(String key) {
        String[] ins = key.split(":");
        Connection conn;
        try {
            conn = DriverManager.getConnection(Config.get("sql_config"));
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO Dragonassistance_data (" + ins[0] + ") " + "VALUES (" + ins[1] + ")");
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void SQL_del(String key) {
        String[] del = key.split(":");
        Connection conn;
        try {
            conn = DriverManager.getConnection(Config.get("sql_config"));
            Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM Dragonassistance_data " + "WHERE " + del[0] + "= " + del[1]);
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static String SQL_sel(String key) {
        String[] sel = key.split(":");
        Connection conn;
        try {
            conn = DriverManager.getConnection(Config.get("sql_config"));
            Statement st = conn.createStatement();
            ResultSet rs;
            rs = st.executeQuery(
                        "SELECT " + sel[0] +
                                " FROM dragonassistance_data" +
                                " WHERE Guild_ID= " + sel[1]);

            rs.next();
            String re = rs.getString(sel[0]);
            conn.close();
            if(!re.isEmpty()) {
                return (re);
            }else{
                return ("unknown");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return key;
    }
    public static void SQL_upt(String key) {
        String[] upt = key.split(":");
        Connection conn;
        try {
            conn = DriverManager.getConnection(Config.get("sql_config"));
            Statement st = conn.createStatement();
            st.executeUpdate("UPDATE dragonassistance_data SET " + upt[0] + "= " + upt[1] + " WHERE Guild_ID= " + upt[2]);
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
