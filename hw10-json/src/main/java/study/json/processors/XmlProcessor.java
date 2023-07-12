package study.json.processors;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.AllArgsConstructor;
import study.json.pojo.newstructure.ResultStructure;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

@AllArgsConstructor
public class XmlProcessor implements SerializationProcessor {

    @Override
    public boolean serialize(Serializable resultStructure, File resultFile) {
        try {
            XmlMapper objectMapper = new XmlMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(resultFile, resultStructure);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Serializable deserialize(File xmlFile) throws IOException {
        XmlMapper objectMapper = new XmlMapper();
        Serializable resultStructure = objectMapper.readValue(xmlFile, ResultStructure.class);
        System.out.println("deserialize: \n" + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultStructure));
        return resultStructure;
    }
}
