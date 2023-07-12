package study.json.processors;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import study.json.pojo.newstructure.GroupedStructure;
import study.json.pojo.newstructure.NewStructure;
import study.json.pojo.newstructure.ResultStructure;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CsvProcessor implements SerializationProcessor {
    @Override
    public boolean serialize(Serializable resultStructure, File resultFile) {
        try {
            CsvMapper objectMapper = new CsvMapper();
            ResultStructure structure = (ResultStructure) resultStructure;
            try (FileOutputStream fileOutputStream = new FileOutputStream(resultFile, true)) {
                structure.groupedStructures
                        .forEach(groupedStructure -> {
                            groupedStructure.newStructures
                                    .forEach(newStructure -> {
                                        mapCsv(objectMapper, fileOutputStream, newStructure);
                                    });
                        });
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Serializable deserialize(File csvFile) throws IOException {
        CsvMapper objectMapper = new CsvMapper();
        ResultStructure resultStructure = new ResultStructure();
        List<NewStructure> newStructureList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile));) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                NewStructure newStructure = objectMapper.readerWithSchemaFor(NewStructure.class).readValue(line, NewStructure.class);
                newStructureList.add(newStructure);
            }
        }
        Map<String, List<NewStructure>> newStructuresMap = newStructureList.stream().collect(Collectors.groupingBy(NewStructure::getBelongNumber));
        newStructuresMap.forEach((key,value) -> {
            GroupedStructure groupedStructure = new GroupedStructure().withBelongNumber(key).withNewStructures(value);
            resultStructure.groupedStructures.add(groupedStructure);
        });
        System.out.println("Result from CSV");
        System.out.println(resultStructure);
        return resultStructure;
    }

    private void mapCsv(CsvMapper objectMapper, FileOutputStream fileOutputStream, NewStructure newStructure) {
        try {
            objectMapper
                    .writerWithSchemaFor(NewStructure.class)
                    .writeValues(fileOutputStream)
                    .write(newStructure);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
