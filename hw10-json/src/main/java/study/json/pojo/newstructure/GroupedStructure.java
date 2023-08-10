package study.json.pojo.newstructure;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "belong_number",
        "new_structures"

})
public class GroupedStructure implements Serializable {

    @JsonProperty("belong_number")
    public String belongNumber;
    @JsonProperty("new_structures")
    public List<NewStructure> newStructures = new ArrayList<NewStructure>();

    public GroupedStructure withBelongNumber(String belongNumber) {
        this.belongNumber = belongNumber;
        return this;
    }

    public GroupedStructure withNewStructures(List<NewStructure> newStructures) {
        this.newStructures = newStructures;
        return this;
    }

    @Override
    public String toString() {
        return "GroupedStructure{" +
                "belongNumber='" + belongNumber + '\'' +
                ", newStructures=" + newStructures +
                '}';
    }
}
