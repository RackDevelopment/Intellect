package github.rackdevelopment;

import github.rackdevelopment.managers.CommandManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * <h1>Intellect Primary Class</h1>
 * The Intellect Spigot Plugin is an advanced logger for the everyday Spigot Server.
 * Intellect provides an efficient design that allows to use on all types of servers, without impacting any performance.
 *
 * @author RackDevelopment
 * @version 1.0
 */
public class Intellect extends JavaPlugin {

    private CommandManager commandManager;

    /**
     * The primary method that is called when the plugin is fully loaded by the server.
     *
     * @see org.bukkit.plugin.java.JavaPlugin
     */
    @Override
    public void onEnable() {
        registerConfiguration(); // The configuration must be registered first to do any checks in events or commands before the plugin is fully loaded.
        registerEvents();
        registerCommands();
    }

    /**
     * This method will set up the entire configuration method we will need for the entire plugin.
     */
    private void registerConfiguration() {

    }

    /**
     * The method that will be called when to register all events that the plugin requires
     */
    private void registerEvents() {

    }

    /**
     * The method will be called to register all the commands through the framework.
     *
     * @see github.rackdevelopment.managers.CommandManager
     */
    private void registerCommands() {
        commandManager = new CommandManager(this);
    }

}
