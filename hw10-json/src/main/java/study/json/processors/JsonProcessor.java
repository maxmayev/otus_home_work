package study.json.processors;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import study.json.pojo.defaultstructure.SmsMessages;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

@AllArgsConstructor
public class JsonProcessor implements SerializationProcessor {

    @Override
    public Serializable deserialize(File jsonFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Serializable smsMessages = objectMapper.readValue(jsonFile, SmsMessages.class);
        System.out.println("deserialize: \n" + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(smsMessages));
        return smsMessages;
    }

    @Override
    public boolean serialize(Serializable resultStructure, File resultFile) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(resultFile, resultStructure);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
