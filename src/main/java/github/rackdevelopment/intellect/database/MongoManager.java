package github.rackdevelopment.intellect.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import github.rackdevelopment.intellect.Intellect;
import lombok.Getter;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.bson.Document;

public class MongoManager {

    @Getter
    private MongoClient mongoClient;
    @Getter
    private MongoDatabase mongoDatabase;
    private final Intellect intellect;

    public MongoManager(Intellect intellect) {
        this.intellect = intellect;
    }

    /**
     * Sets up all databases.
     */
    public void initialize() {
        System.out.println("[Intellect] Attempting to setup databases...");
        System.out.println("[Intellect] Attempting to establish MongoDB connection...");
        setupMongo();
    }

    /**
     * Sets up a new Mongo connection.
     */
    private void setupMongo() {
        String host = intellect.getConfig().getString("mongo.host", "localhost");
        int port = intellect.getConfig().getInt("mongo.port", 3306);
        String database = intellect.getConfig().getString("mongo.database", "Intellect");
        boolean authentication = intellect.getConfig().getBoolean("mongo.auth.enabled", false);
        String username = intellect.getConfig().getString("mongo.auth.username", "");
        String password = intellect.getConfig().getString("mongo.auth.password", "");

        try {
            if (authentication && username != null && password != null)
                mongoClient = MongoClients.create("mongodb://" + username + ":" + password + "@" + host + ":" + port + "/admin");
            else MongoClients.create("mongodb://" + host + ":" + port + "/admin");
            mongoDatabase = mongoClient.getDatabase(database);
            System.out.println("[Intellect] A MongoDB connection has been established!");
            System.out.println("[Intellect] MongoDB has been successfully setup!");
            System.out.println("[Intellect] Attempting to establish Redis connection...");
        } catch (Exception exception) {
            System.out.println("[Intellect] An error has occurred while attempting to setup the Mongo connection! (" + ExceptionUtils.getStackTrace(exception) + ")");
            return;
        }
    }

    /**
     * Retrieves a collection by it's name.
     *
     * @param name The name of the collection you want to get.
     * @return The specified collection.
     */
    public MongoCollection<Document> getCollection(String name) {
        return mongoDatabase.getCollection(name);
    }


}
