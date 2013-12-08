package net.cubespace.LocaleTestPlugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerSettingsChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 * @date Last changed: 08.12.13 12:06
 */
public class LocaleTestPlugin extends JavaPlugin implements Listener {
    private ArrayList<Player> greeted = new ArrayList<Player>();

    public void onEnable() {
        getLocaleManager().setDefaultLocale(new java.util.Locale("en", "US"));
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerSettings(PlayerSettingsChangeEvent event) {
        if(!greeted.contains(event.getPlayer())) {
            event.getPlayer().sendMessage(getLocaleManager().translate(event.getPlayer(), "Welcome"));
            greeted.add(event.getPlayer());
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        greeted.remove(event.getPlayer());
    }
}
