package study.json.processors;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import study.json.pojo.newstructure.ResultStructure;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class YamlProcessor implements SerializationProcessor {
    @Override
    public boolean serialize(Serializable resultStructure, File resultFile) {
        try {
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(resultFile, resultStructure);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Serializable deserialize(File yamlFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        Serializable resultStructure = objectMapper.readValue(yamlFile, ResultStructure.class);
        System.out.println("deserialize: \n" + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultStructure));
        return resultStructure;
    }
}
