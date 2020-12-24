package github.rackdevelopment.intellect.objects.commands.serialization;

import github.rackdevelopment.intellect.mongo.serialization.MongoDeserializer;
import github.rackdevelopment.intellect.objects.commands.CommandLog;
import org.bson.Document;

import java.util.UUID;

public class CommandLogDeserializer implements MongoDeserializer<CommandLog> {

    @Override
    public CommandLog deserialize(Document doc) {
        return new CommandLog(UUID.fromString(doc.getString("commandID")),
                UUID.fromString(doc.getString("executor")), doc.getString("command"), doc.getLong("timestamp"));
    }

}
