package github.rackdevelopment.intellect.loggers.commands;

import github.rackdevelopment.intellect.Intellect;
import github.rackdevelopment.intellect.objects.commands.CommandLog;
import org.bson.Document;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.UUID;

public class CommandEvents implements Listener {

    private final Intellect intellect;

    public CommandEvents(Intellect intellect) {
        this.intellect = intellect;
    }

    @EventHandler(ignoreCancelled = true)
    private void onCommandPreProcess(PlayerCommandPreprocessEvent e) {
        intellect.getLogger().debug("Player " + e.getPlayer().getDisplayName() + ":" + e.getPlayer().getUniqueId() + " executed command " + e.getMessage() + " at " + System.currentTimeMillis());
        CommandLog log = new CommandLog(UUID.randomUUID(), e.getPlayer().getUniqueId(), e.getMessage(), System.currentTimeMillis());

    }

}
