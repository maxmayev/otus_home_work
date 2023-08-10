package study.json;

import study.json.pojo.defaultstructure.ChatSession;
import study.json.pojo.defaultstructure.Member;
import study.json.pojo.defaultstructure.Message;
import study.json.pojo.defaultstructure.SmsMessages;
import study.json.pojo.newstructure.GroupedStructure;
import study.json.pojo.newstructure.NewStructure;
import study.json.pojo.newstructure.ResultStructure;
import study.json.processors.*;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Runner {
    public static void main(String[] args) {
        final String inFilePath = "build\\resources\\main\\sms.json";
        final String outJsonFilePath = "build\\resources\\main\\jsonResultSms.json";
        final String outXmlFilePath = "build\\resources\\main\\xmlResultSms.xml";
        final String outYamlFilePath = "build\\resources\\main\\yamlResultSms.yaml";
        final String outCSVFilePath = "build\\resources\\main\\csvResultSms.csv";

        File inFile = new File(inFilePath);


        try {
            SerializationProcessor jsonSerializationProcessor = new JsonProcessor();
            Serializable deserialize = jsonSerializationProcessor.deserialize(inFile);

            ResultStructure resultStructure = getResultStructure((SmsMessages) deserialize);

            if (!(new File(outJsonFilePath).exists())) new File(outJsonFilePath).createNewFile();
            File outJsonFile = new File(outJsonFilePath);
            jsonSerializationProcessor.serialize(resultStructure, outJsonFile);

            if (!(new File(outXmlFilePath).exists())) new File(outXmlFilePath).createNewFile();
            File outXmlFile = new File(outXmlFilePath);
            SerializationProcessor xmlSerializationProcessor = new XmlProcessor();
            xmlSerializationProcessor.serialize(resultStructure, outXmlFile);
            Serializable deserializeXml = xmlSerializationProcessor.deserialize(outXmlFile);

            if (!(new File(outYamlFilePath).exists())) new File(outYamlFilePath).createNewFile();
            File outYamlFile = new File(outYamlFilePath);
            SerializationProcessor yamlSerializationProcessor = new YamlProcessor();
            yamlSerializationProcessor.serialize(resultStructure, outYamlFile);
            Serializable deserializeYaml = yamlSerializationProcessor.deserialize(outYamlFile);


            if (!(new File(outCSVFilePath).exists())) new File(outCSVFilePath).createNewFile();
            File outCsvFile = new File(outCSVFilePath);

            SerializationProcessor csvSerializationProcessor = new CsvProcessor();
            csvSerializationProcessor.serialize(resultStructure, outCsvFile);
            Serializable deserializeCsv = csvSerializationProcessor.deserialize(outCsvFile);



        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static ResultStructure getResultStructure(SmsMessages deserialize) {

        List<NewStructure> newStructures = new ArrayList<>();

        for (ChatSession chatSession : deserialize.chatSessions
        ) {
            for (Member member : chatSession.members
            ) {
                for (Message message : chatSession.messages
                ) {
                    NewStructure newStructure = new NewStructure()
                            .withChatIdentifier(chatSession.chatIdentifier)
                            .withLast(member.last)
                            .withBelongNumber(message.belongNumber)
                            .withSendDate(message.sendDate)
                            .withText(message.text);
                    newStructures.add(newStructure);
                }
            }
        }
        Map<String, List<NewStructure>> newStructuresMap = newStructures.stream().distinct().collect(Collectors.groupingBy(NewStructure::getBelongNumber));
        List<GroupedStructure> groupedStructureList = new ArrayList<>();

        newStructuresMap.forEach((key, value) -> groupedStructureList.add(new GroupedStructure().withBelongNumber(key).withNewStructures(value)));

        return new ResultStructure().withGroupedStructures(groupedStructureList);
    }
}
