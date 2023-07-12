
package study.json.pojo.defaultstructure;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "chat_sessions"
})
public class SmsMessages implements Serializable {
    @JsonProperty("chat_sessions")
    public List<ChatSession> chatSessions = new ArrayList<ChatSession>();
    private final static long serialVersionUID = 5601753557828218485L;

    public SmsMessages withChatSessions(List<ChatSession> chatSessions) {
        this.chatSessions = chatSessions;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SmsMessages.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("chatSessions");
        sb.append('=');
        sb.append(((this.chatSessions == null) ? "<null>" : this.chatSessions));
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
        result = ((result * 31) + ((this.chatSessions == null) ? 0 : this.chatSessions.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SmsMessages) == false) {
            return false;
        }
        SmsMessages rhs = ((SmsMessages) other);
        return ((this.chatSessions == rhs.chatSessions) || ((this.chatSessions != null) && this.chatSessions.equals(rhs.chatSessions)));
    }

}
