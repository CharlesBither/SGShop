package tech.secretgarden.sgshop;

import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.ArrayList;

public final class SGShop extends JavaPlugin {

    Database database = new Database();


    public static ArrayList<String> dbList = new ArrayList<>();
    public ArrayList<String> getDbList() {
        dbList.add(getConfig().getString("HOST"));
        dbList.add(getConfig().getString("PORT"));
        dbList.add(getConfig().getString("DATABASE"));
        dbList.add(getConfig().getString("USERNAME"));
        dbList.add(getConfig().getString("PASSWORD"));
        return dbList;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        if (getConfig().getString("HOST") != null) {
            try {
                getDbList();
                database.connect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Connected to database = " + database.isConnected());
        System.out.println("SGShop plugin has loaded");

        getCommand("shop").setExecutor(new ShopCommand());


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("SGShop has unloaded");
        database.disconnect();
    }
}
