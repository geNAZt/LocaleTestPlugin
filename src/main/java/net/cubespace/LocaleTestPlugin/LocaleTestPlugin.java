package net.cubespace.LocaleTestPlugin;

import org.bukkit.Locale;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerSettingsChangeEvent;
import org.bukkit.plugin.LocaleLoadFailedException;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.logging.Level;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 * @date Last changed: 08.12.13 12:06
 */
public class LocaleTestPlugin extends JavaPlugin implements Listener {
    private ArrayList<Player> greeted = new ArrayList<Player>();

    public void onEnable() {
        try {
            getLocaleManager().load(Locale.GERMAN, getResource("language/de_DE.yml"));
            getLocaleManager().load(Locale.ENGLISH, getResource("language/en_US.yml"));

            getLocaleManager().setDefaultLocale(Locale.ENGLISH);
        } catch (LocaleLoadFailedException e) {
            getLogger().log(Level.SEVERE, "Could not load languages", e);
            setEnabled(false);
        }

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
