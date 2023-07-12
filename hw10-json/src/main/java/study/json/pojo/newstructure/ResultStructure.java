package study.json.pojo.newstructure;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "grouped_structures"
})

public class ResultStructure implements Serializable {
    @JsonProperty("grouped_structures")
    public List<GroupedStructure> groupedStructures = new ArrayList<GroupedStructure>();
    private final static long serialVersionUID = 5601553457828217484L;

    public ResultStructure withGroupedStructures(List<GroupedStructure> groupedStructures) {
        this.groupedStructures = groupedStructures;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ResultStructure.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("groupedStructures");
        sb.append('=');
        sb.append(((this.groupedStructures == null) ? "<null>" : this.groupedStructures));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result * 31) + ((this.groupedStructures == null) ? 0 : this.groupedStructures.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ResultStructure) == false) {
            return false;
        }
        ResultStructure rhs = ((ResultStructure) other);
        return ((this.groupedStructures == rhs.groupedStructures) || ((this.groupedStructures != null) && this.groupedStructures.equals(rhs.groupedStructures)));
    }

}
