package io.github.Nyg404.PGCORE.DataBase;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import ru.thisistails.tailslib.Tools.YAMLManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    private static DataBase instance = null;

    static{
        instance = new DataBase();
    }

    public static DataBase getInstance() {
        return instance;
    }

    private DataBase(){
        connection();
    }

    static FileConfiguration configuration = YAMLManager.require("RPGCORE", "config.yml");

    private static final String type = configuration.getString("type");
    private static final String jdbc = configuration.getString("MySql.jdbc");
    private static final String username = configuration.getString("MySql.username");
    private static final String password = configuration.getString("MySql.password");


    public Connection connection(){
        if(type.equals("Sqlite")){
            try {
                return DriverManager.getConnection("jdbc:sqlite:database.db");
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        } else if (type.equals("MySql")) {
            try {
                return DriverManager.getConnection(jdbc, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public void close(){
        if(connection() != null){
            close();
        }
    }

    public void createTables(){
        String Player = "CREATE TABLE IF NOT EXISTS players("
                +"uuid VARCHAR(36) PRIMARY KEY,"
                +"player_name VARCHAR(36),"
                +");";
        String playerStats = "CREATE TABLE IF NOT EXISTS player_stats ("
                + "uuid VARCHAR(36) PRIMARY KEY, " // Добавляем автоинкрементный идентификатор
                + "level INT, "
                + "current_xp INT, "
                + "required_xp INT, "
                + "strength INT, "
                + "stamina INT, " // Новая колонка для выносливости
                + "wisdom INT, " // Новая колонка для мудрости
                + "agility INT, " // Новая колонка для ловкости
                + "attack_speed INT, " // Новая колонка для скорости атаки
                + "speed INT, " // Новая колонка для скорости
                + "FOREIGN KEY (uuid) REFERENCES players(uuid) ON DELETE CASCADE" // Ссылка на uuid игрока
                + ");";

    }


}
