package net.cubespace.LocaleTestPlugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerSettingsChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.localization.ResourceLoadFailedException;
import org.bukkit.plugin.localization.ResourceNotLoadedException;

import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 * @date Last changed: 08.12.13 12:06
 */
public class LocaleTestPlugin extends JavaPlugin implements Listener {
    private ArrayList<Player> greeted = new ArrayList<Player>();

    public void onEnable() {
        try {
            getLocaleManager().load(new Locale("en", "US"), "lang/en_US.yml");
            getLocaleManager().load(new java.util.Locale("de", "DE"), "lang/de_DE.yml");

            getLocaleManager().setDefaultLocale(new java.util.Locale("en", "US"));
        } catch (ResourceLoadFailedException e) {
            getLogger().log(Level.SEVERE, "There was an error in loading the Locales", e);
            setEnabled(false);
        }

        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerSettings(PlayerSettingsChangeEvent event) throws ResourceNotLoadedException {
        if(!greeted.contains(event.getPlayer())) {
            event.getPlayer().sendMessage(getLocaleManager().translate(event.getPlayer(), "Welcome", event.getPlayer().getWorld().getName(), getServer().getOnlinePlayers().length));
            greeted.add(event.getPlayer());
        }

        event.getPlayer().sendMessage(getLocaleManager().translate(event.getPlayer(), "Changed locale"));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        greeted.remove(event.getPlayer());
    }
}
