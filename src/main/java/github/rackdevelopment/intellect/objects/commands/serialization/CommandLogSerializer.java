package github.rackdevelopment.intellect.objects.commands.serialization;

import github.rackdevelopment.intellect.mongo.serialization.MongoSerializer;
import github.rackdevelopment.intellect.objects.commands.CommandLog;
import org.bson.Document;

public class CommandLogSerializer implements MongoSerializer<CommandLog> {

    @Override
    public Document serialize(CommandLog command) {
        Document doc = new Document();
        doc.put("commandID", command.getCommandID());
        doc.put("executor", command.getPlayer());
        doc.put("command", command.getCommand());
        doc.put("timestamp", command.getTimestamp());
        return doc;
    }

}
