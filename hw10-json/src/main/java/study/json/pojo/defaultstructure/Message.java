
package study.json.pojo.defaultstructure;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "ROWID",
    "attributedBody",
    "belong_number",
    "date",
    "date_read",
    "guid",
    "handle_id",
    "has_dd_results",
    "is_deleted",
    "is_from_me",
    "send_date",
    "send_status",
    "service",
    "text"
})
public class Message implements Serializable
{

    @JsonProperty("ROWID")
    public long rowid;
    @JsonProperty("attributedBody")
    public String attributedBody;
    @JsonProperty("belong_number")
    public String belongNumber;
    @JsonProperty("date")
    public long date;
    @JsonProperty("date_read")
    public long dateRead;
    @JsonProperty("guid")
    public String guid;
    @JsonProperty("handle_id")
    public long handleId;
    @JsonProperty("has_dd_results")
    public long hasDdResults;
    @JsonProperty("is_deleted")
    public long isDeleted;
    @JsonProperty("is_from_me")
    public long isFromMe;
    @JsonProperty("send_date")
    public String sendDate;
    @JsonProperty("send_status")
    public long sendStatus;
    @JsonProperty("service")
    public String service;
    @JsonProperty("text")
    public String text;
    private final static long serialVersionUID = -8208736568664435863L;

    public Message withRowid(long rowid) {
        this.rowid = rowid;
        return this;
    }

    public Message withAttributedBody(String attributedBody) {
        this.attributedBody = attributedBody;
        return this;
    }

    public Message withBelongNumber(String belongNumber) {
        this.belongNumber = belongNumber;
        return this;
    }

    public Message withDate(long date) {
        this.date = date;
        return this;
    }

    public Message withDateRead(long dateRead) {
        this.dateRead = dateRead;
        return this;
    }

    public Message withGuid(String guid) {
        this.guid = guid;
        return this;
    }

    public Message withHandleId(long handleId) {
        this.handleId = handleId;
        return this;
    }

    public Message withHasDdResults(long hasDdResults) {
        this.hasDdResults = hasDdResults;
        return this;
    }

    public Message withIsDeleted(long isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public Message withIsFromMe(long isFromMe) {
        this.isFromMe = isFromMe;
        return this;
    }

    public Message withSendDate(String sendDate) {
        this.sendDate = sendDate;
        return this;
    }

    public Message withSendStatus(long sendStatus) {
        this.sendStatus = sendStatus;
        return this;
    }

    public Message withService(String service) {
        this.service = service;
        return this;
    }

    public Message withText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Message.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("rowid");
        sb.append('=');
        sb.append(this.rowid);
        sb.append(',');
        sb.append("attributedBody");
        sb.append('=');
        sb.append(((this.attributedBody == null)?"<null>":this.attributedBody));
        sb.append(',');
        sb.append("belongNumber");
        sb.append('=');
        sb.append(((this.belongNumber == null)?"<null>":this.belongNumber));
        sb.append(',');
        sb.append("date");
        sb.append('=');
        sb.append(this.date);
        sb.append(',');
        sb.append("dateRead");
        sb.append('=');
        sb.append(this.dateRead);
        sb.append(',');
        sb.append("guid");
        sb.append('=');
        sb.append(((this.guid == null)?"<null>":this.guid));
        sb.append(',');
        sb.append("handleId");
        sb.append('=');
        sb.append(this.handleId);
        sb.append(',');
        sb.append("hasDdResults");
        sb.append('=');
        sb.append(this.hasDdResults);
        sb.append(',');
        sb.append("isDeleted");
        sb.append('=');
        sb.append(this.isDeleted);
        sb.append(',');
        sb.append("isFromMe");
        sb.append('=');
        sb.append(this.isFromMe);
        sb.append(',');
        sb.append("sendDate");
        sb.append('=');
        sb.append(((this.sendDate == null)?"<null>":this.sendDate));
        sb.append(',');
        sb.append("sendStatus");
        sb.append('=');
        sb.append(this.sendStatus);
        sb.append(',');
        sb.append("service");
        sb.append('=');
        sb.append(((this.service == null)?"<null>":this.service));
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
        result = ((result* 31)+((int)(this.date^(this.date >>> 32))));
        result = ((result* 31)+((this.sendDate == null)? 0 :this.sendDate.hashCode()));
        result = ((result* 31)+((int)(this.hasDdResults^(this.hasDdResults >>> 32))));
        result = ((result* 31)+((this.belongNumber == null)? 0 :this.belongNumber.hashCode()));
        result = ((result* 31)+((this.attributedBody == null)? 0 :this.attributedBody.hashCode()));
        result = ((result* 31)+((int)(this.rowid^(this.rowid >>> 32))));
        result = ((result* 31)+((int)(this.isDeleted^(this.isDeleted >>> 32))));
        result = ((result* 31)+((this.service == null)? 0 :this.service.hashCode()));
        result = ((result* 31)+((this.guid == null)? 0 :this.guid.hashCode()));
        result = ((result* 31)+((int)(this.isFromMe^(this.isFromMe >>> 32))));
        result = ((result* 31)+((int)(this.sendStatus^(this.sendStatus >>> 32))));
        result = ((result* 31)+((this.text == null)? 0 :this.text.hashCode()));
        result = ((result* 31)+((int)(this.handleId^(this.handleId >>> 32))));
        result = ((result* 31)+((int)(this.dateRead^(this.dateRead >>> 32))));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Message) == false) {
            return false;
        }
        Message rhs = ((Message) other);
        return ((((((((((((((this.date == rhs.date)&&((this.sendDate == rhs.sendDate)||((this.sendDate!= null)&&this.sendDate.equals(rhs.sendDate))))&&(this.hasDdResults == rhs.hasDdResults))&&((this.belongNumber == rhs.belongNumber)||((this.belongNumber!= null)&&this.belongNumber.equals(rhs.belongNumber))))&&((this.attributedBody == rhs.attributedBody)||((this.attributedBody!= null)&&this.attributedBody.equals(rhs.attributedBody))))&&(this.rowid == rhs.rowid))&&(this.isDeleted == rhs.isDeleted))&&((this.service == rhs.service)||((this.service!= null)&&this.service.equals(rhs.service))))&&((this.guid == rhs.guid)||((this.guid!= null)&&this.guid.equals(rhs.guid))))&&(this.isFromMe == rhs.isFromMe))&&(this.sendStatus == rhs.sendStatus))&&((this.text == rhs.text)||((this.text!= null)&&this.text.equals(rhs.text))))&&(this.handleId == rhs.handleId))&&(this.dateRead == rhs.dateRead));
    }

}
