package github.rackdevelopment.intellect;

import co.aikar.commands.PaperCommandManager;
import github.rackdevelopment.intellect.database.MongoManager;
import github.rackdevelopment.intellect.logger.Logger;
import lombok.Getter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * <h1>Intellect Primary Class</h1>
 * The Intellect Spigot Plugin is an advanced logger for the everyday Spigot Server.
 * Intellect provides an efficient design that allows to use on all types of servers, without impacting any performance.
 *
 * @author RackDevelopment
 * @version 1.0.1
 */
public class Intellect extends JavaPlugin {

    @Getter
    private PaperCommandManager commandManager;
    private File messages;
    @Getter
    private FileConfiguration messagesConfiguration;
    @Getter
    private Logger logger;
    @Getter
    private MongoManager mongoManager;


    /**
     * The primary method that is called when the plugin is fully loaded by the server.
     *
     * @see org.bukkit.plugin.java.JavaPlugin
     */
    @Override
    public void onEnable() {
        logger = new Logger(this);
        registerDefaultConfiguration(); // The configuration must be registered first to do any checks in events or commands before the plugin is fully loaded.
        registerMessagesConfiguration();
        registerDatabases();
        registerEvents();
        registerCommands();
    }


    /**
     * This method will set up the primary configuration for the plugin.
     */
    private void registerDefaultConfiguration() {
        saveDefaultConfig();
    }

    /**
     * This method will set up the messages configuration.
     */
    private void registerMessagesConfiguration() {
        messages = new File(getDataFolder(), "messages.yml");
        if (!messages.exists()) {
            messages.getParentFile().mkdirs();
            saveResource("messages.yml", false);
        }
        messagesConfiguration = new YamlConfiguration();
        try {
            messagesConfiguration.load(messages);
        } catch (IOException | InvalidConfigurationException e) {
            getLogger().error(e);
        }
    }

    /**
     * This method will create the instance of MongoManager, and then make sure it connects.
     */
    private void registerDatabases() {
        mongoManager = new MongoManager(this);
    }

    /**
     * The method that will be called when to register all events that the plugin requires
     */
    private void registerEvents() {

    }

    /**
     * The method will be called to register all the commands through the framework.
     *
     * @see co.aikar.commands.PaperCommandManager
     */
    private void registerCommands() {
        commandManager = new PaperCommandManager(this);
    }

}
