package study.json.pojo.newstructure;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import study.json.pojo.defaultstructure.Member;

import java.io.Serializable;@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "chat_identifier",
        "member_last",
        "belong_number",
        "send_date",
        "text"
})


public class NewStructure implements Serializable {

    @JsonProperty("chat_identifier")
    public String chatIdentifier;
    @JsonProperty("member_last")
    public String last;
    @JsonProperty("belong_number")
    public String belongNumber;
    @JsonProperty("send_date")
    public String sendDate;
    @JsonProperty("text")
    public String text;
    private final static long serialVersionUID = -3208736668764535863L;

    public String getBelongNumber() {
        return belongNumber;
    }

    public NewStructure withChatIdentifier(String chatIdentifier) {
        this.chatIdentifier = chatIdentifier;
        return this;
    }

    public NewStructure withLast(String last) {
        this.last = last;
        return this;
    }

    public NewStructure withBelongNumber(String belongNumber) {
        this.belongNumber = belongNumber;
        return this;
    }

    public NewStructure withSendDate(String sendDate) {
        this.sendDate = sendDate;
        return this;
    }

    public NewStructure withText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Member.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("chatIdentifier");
        sb.append('=');
        sb.append(((this.chatIdentifier == null)?"<null>":this.chatIdentifier));
        sb.append(',');
        sb.append("last");
        sb.append('=');
        sb.append(((this.last == null)?"<null>":this.last));
        sb.append(',');
        sb.append("belongNumber");
        sb.append('=');
        sb.append(((this.belongNumber == null)?"<null>":this.belongNumber));
        sb.append(',');
        sb.append("sendDate");
        sb.append('=');
        sb.append(((this.sendDate == null)?"<null>":this.sendDate));
        sb.append(',');
        sb.append("text");
        sb.append('=');
        sb.append(((this.text == null)?"<null>":this.text));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.chatIdentifier == null)? 0 :this.chatIdentifier.hashCode()));
        result = ((result* 31)+((this.last == null)? 0 :this.last.hashCode()));
        result = ((result* 31)+((this.belongNumber == null)? 0 :this.belongNumber.hashCode()));
        result = ((result* 31)+((this.sendDate == null)? 0 :this.sendDate.hashCode()));
        result = ((result* 31)+((this.text == null)? 0 :this.text.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof NewStructure) == false) {
            return false;
        }
        NewStructure rhs = ((NewStructure) other);
        return (((((((((this.chatIdentifier == rhs.chatIdentifier)||((this.chatIdentifier!= null)&&this.chatIdentifier.equals(rhs.chatIdentifier)))&&((this.last == rhs.last)||((this.last!= null)&&this.last.equals(rhs.last))))&&((this.belongNumber == rhs.belongNumber)||((this.belongNumber!= null)&&this.belongNumber.equals(rhs.belongNumber))))&&((this.sendDate == rhs.sendDate)||((this.sendDate!= null)&&this.sendDate.equals(rhs.sendDate))))&&((this.text == rhs.text)||((this.text!= null)&&this.text.equals(rhs.text)))))));
    }

}
