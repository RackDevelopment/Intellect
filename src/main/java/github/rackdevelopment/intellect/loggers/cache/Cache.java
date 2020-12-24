package github.rackdevelopment.intellect.loggers.cache;

import github.rackdevelopment.intellect.objects.commands.CommandLog;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class Cache {

    private final Set<CommandLog> commandCache;

    public Cache() {
        commandCache = new HashSet<CommandLog>();
    }

}
