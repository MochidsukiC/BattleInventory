package jp.houlab.mochidsuki.battleinventory;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * メインクラス
 */
public final class Main extends JavaPlugin {
    public static Plugin plugin;
    public static FileConfiguration config;

    /**
     * 起動時の初期化処理
     */
    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        saveDefaultConfig();
        config = getConfig();

        getServer().getPluginManager().registerEvents(new Listener(),this);

        new InventoryController().runTaskTimer(this, 1L, 1L);

    }

    /**
     * 終了
     */
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
