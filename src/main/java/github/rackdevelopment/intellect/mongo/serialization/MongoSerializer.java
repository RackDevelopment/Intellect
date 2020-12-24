package github.rackdevelopment.intellect.mongo.serialization;

import org.bson.Document;

public interface MongoSerializer<T> {
    Document serialize(T object);
}
