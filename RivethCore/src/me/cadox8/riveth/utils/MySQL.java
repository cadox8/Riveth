package me.cadox8.riveth.utils;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import me.cadox8.riveth.Riveth;
import me.cadox8.riveth.api.RUser;

import java.sql.*;
import java.util.UUID;

/**
 * Object for MySQL connexions
 *
 * @author Huskehhh, Cadiducho & Cadox8 update
 */

public class MySQL {

    private Riveth plugin = Riveth.getInstance();

    protected Connection connection;

    private final String user, database, password, port, hostname;

    public MySQL(String hostname, String port, String database, String username, String password) {
        this.hostname = hostname;
        this.port = port;
        this.database = database;
        this.user = username;
        this.password = password;
    }

    public boolean checkConnection() throws SQLException {
        return connection != null && !connection.isClosed();
    }

    public Connection getConnection() {
        return connection;
    }

    public boolean closeConnection() throws SQLException {
        if (connection == null) return false;
        connection.close();
        return true;
    }

    public Connection openConnection() throws SQLException, ClassNotFoundException {
        if (checkConnection()) return connection;

        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://"
                + this.hostname + ":" + this.port + "/" + this.database + "?autoReconnect=true", this.user, this.password);
        return connection;
    }

    // -----------------
    public void setupTable(RUser p) {
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                PreparedStatement statement = openConnection().prepareStatement("SELECT `id` FROM `riveth_data` WHERE `uuid` = ?");
                statement.setString(1, p.getUuid().toString());
                ResultSet rs = statement.executeQuery();

                if (!rs.next()) {
                    PreparedStatement inserDatos = openConnection().prepareStatement("INSERT INTO `riveth_data` (`uuid`, `name`) VALUES (?, ?)");
                    inserDatos.setString(1, p.getUuid().toString());
                    inserDatos.setString(2, p.getName());
                    inserDatos.executeUpdate();

                    p.sendMessage(plugin.getConfig().getString("JL.newbie"));
                }
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void saveUser(RUser u) {
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
            RUser.UserData data = u.getUserData();
            try {

                PreparedStatement statementDatos = openConnection().prepareStatement("UPDATE `riveth_data` SET `god`=?,`lastConnect`=?,`ip`=?,`nick`=? WHERE `uuid`=?");
                statementDatos.setInt(1, data.getGod() == null ? 0 : data.getGod());
                statementDatos.setTimestamp(2, new java.sql.Timestamp(new java.util.Date().getTime()));
                statementDatos.setString(3, data.getIp() == null ? "" : data.getIp().getAddress().getHostAddress());
                statementDatos.setString(4, data.getNickname() == null ? "" : data.getNickname());
                statementDatos.setString(5, u.getUuid().toString());
                statementDatos.executeUpdate();
            } catch (Exception ex) {
                Log.log(Log.Level.ERROR, "Error while saving data of " + u.getUuid());
                ex.printStackTrace();
            }
        });
    }

    public RUser.UserData loadUserData(UUID id) {
        RUser.UserData data = new RUser.UserData();
        try {

            //Datos
            PreparedStatement statementDatos = openConnection().prepareStatement("SELECT `god`,`nick`,`lastConnect` FROM `riveth_data` WHERE `uuid` = ?");
            statementDatos.setString(1, id.toString());
            ResultSet rsDatos = statementDatos.executeQuery();

            if (rsDatos.next()) {
                data.setGod(rsDatos.getInt("god"));
                data.setNickname(rsDatos.getString("nick"));
                data.setLastConnect(rsDatos.getLong("lastConnect"));
            }
        } catch (CommunicationsException ex) {
            Log.debugLog(ex.toString());
            try {
                closeConnection();
                openConnection();
                return loadUserData(id);
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        } catch (Exception ex) {
            Log.log(Log.Level.ERROR, "Error while loading data of " + id);
            ex.printStackTrace();
        }
        return data;
    }
}
