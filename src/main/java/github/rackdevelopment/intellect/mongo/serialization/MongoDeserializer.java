package github.rackdevelopment.intellect.mongo.serialization;

import org.bson.Document;

public interface MongoDeserializer<T> {
    T deserialize(Document document);
}
