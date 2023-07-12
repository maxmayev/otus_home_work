
package study.json.pojo.defaultstructure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "chat_id",
    "chat_identifier",
    "display_name",
    "is_deleted",
    "members",
    "messages"
})
public class ChatSession implements Serializable
{

    @JsonProperty("chat_id")
    public long chatId;
    @JsonProperty("chat_identifier")
    public String chatIdentifier;
    @JsonProperty("display_name")
    public String displayName;
    @JsonProperty("is_deleted")
    public long isDeleted;
    @JsonProperty("members")
    public List<Member> members = new ArrayList<Member>();
    @JsonProperty("messages")
    public List<Message> messages = new ArrayList<Message>();
    private final static long serialVersionUID = -4900116508588422938L;

    public ChatSession withChatId(long chatId) {
        this.chatId = chatId;
        return this;
    }

    public ChatSession withChatIdentifier(String chatIdentifier) {
        this.chatIdentifier = chatIdentifier;
        return this;
    }

    public ChatSession withDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public ChatSession withIsDeleted(long isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public ChatSession withMembers(List<Member> members) {
        this.members = members;
        return this;
    }

    public ChatSession withMessages(List<Message> messages) {
        this.messages = messages;
        return this;
    }





    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ChatSession.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("chatId");
        sb.append('=');
        sb.append(this.chatId);
        sb.append(',');
        sb.append("chatIdentifier");
        sb.append('=');
        sb.append(((this.chatIdentifier == null)?"<null>":this.chatIdentifier));
        sb.append(',');
        sb.append("displayName");
        sb.append('=');
        sb.append(((this.displayName == null)?"<null>":this.displayName));
        sb.append(',');
        sb.append("isDeleted");
        sb.append('=');
        sb.append(this.isDeleted);
        sb.append(',');
        sb.append("members");
        sb.append('=');
        sb.append(((this.members == null)?"<null>":this.members));
        sb.append(',');
        sb.append("messages");
        sb.append('=');
        sb.append(((this.messages == null)?"<null>":this.messages));
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
        result = ((result* 31)+((int)(this.chatId^(this.chatId >>> 32))));
        result = ((result* 31)+((int)(this.isDeleted^(this.isDeleted >>> 32))));
        result = ((result* 31)+((this.displayName == null)? 0 :this.displayName.hashCode()));
        result = ((result* 31)+((this.members == null)? 0 :this.members.hashCode()));
        result = ((result* 31)+((this.messages == null)? 0 :this.messages.hashCode()));
        result = ((result* 31)+((this.chatIdentifier == null)? 0 :this.chatIdentifier.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ChatSession) == false) {
            return false;
        }
        ChatSession rhs = ((ChatSession) other);
        return ((((((this.chatId == rhs.chatId)&&(this.isDeleted == rhs.isDeleted))&&((this.displayName == rhs.displayName)||((this.displayName!= null)&&this.displayName.equals(rhs.displayName))))&&((this.members == rhs.members)||((this.members!= null)&&this.members.equals(rhs.members))))&&((this.messages == rhs.messages)||((this.messages!= null)&&this.messages.equals(rhs.messages))))&&((this.chatIdentifier == rhs.chatIdentifier)||((this.chatIdentifier!= null)&&this.chatIdentifier.equals(rhs.chatIdentifier))));
    }

}
