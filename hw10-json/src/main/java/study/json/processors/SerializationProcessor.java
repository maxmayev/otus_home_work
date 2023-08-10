package study.json.processors;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public interface SerializationProcessor {

    default Serializable deserialize(File jsonFile) throws IOException {
        return "You can not deserialize this format";
    }

    boolean serialize(Serializable resultStructure, File resultFile);
}

