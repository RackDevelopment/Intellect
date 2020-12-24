package github.rackdevelopment.intellect.objects.commands;

import github.rackdevelopment.intellect.objects.commands.serialization.CommandLogDeserializer;
import github.rackdevelopment.intellect.objects.commands.serialization.CommandLogSerializer;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CommandLog {
    public static CommandLogSerializer SERIALIZER = new CommandLogSerializer();
    public static CommandLogDeserializer DESERIALIZER = new CommandLogDeserializer();
    private final UUID commandID, player;
    private final String command;
    private final long timestamp;

    public CommandLog(UUID commandID, UUID player, String command, long timestamp) {
        this.commandID = commandID;
        this.player = player;
        this.command = command;
        this.timestamp = timestamp;
    }
}
