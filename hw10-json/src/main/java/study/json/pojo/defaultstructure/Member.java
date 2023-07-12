
package study.json.pojo.defaultstructure;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "first",
    "handle_id",
    "image_path",
    "last",
    "middle",
    "phone_number",
    "service",
    "thumb_path"
})

public class Member implements Serializable
{

    @JsonProperty("first")
    public String first;
    @JsonProperty("handle_id")
    public long handleId;
    @JsonProperty("image_path")
    public String imagePath;
    @JsonProperty("last")
    public String last;
    @JsonProperty("middle")
    public String middle;
    @JsonProperty("phone_number")
    public String phoneNumber;
    @JsonProperty("service")
    public String service;
    @JsonProperty("thumb_path")
    public String thumbPath;
    private final static long serialVersionUID = -1249562785917046391L;

    public Member withFirst(String first) {
        this.first = first;
        return this;
    }

    public Member withHandleId(long handleId) {
        this.handleId = handleId;
        return this;
    }

    public Member withImagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    public Member withLast(String last) {
        this.last = last;
        return this;
    }

    public Member withMiddle(String middle) {
        this.middle = middle;
        return this;
    }

    public Member withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Member withService(String service) {
        this.service = service;
        return this;
    }

    public Member withThumbPath(String thumbPath) {
        this.thumbPath = thumbPath;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Member.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("first");
        sb.append('=');
        sb.append(((this.first == null)?"<null>":this.first));
        sb.append(',');
        sb.append("handleId");
        sb.append('=');
        sb.append(this.handleId);
        sb.append(',');
        sb.append("imagePath");
        sb.append('=');
        sb.append(((this.imagePath == null)?"<null>":this.imagePath));
        sb.append(',');
        sb.append("last");
        sb.append('=');
        sb.append(((this.last == null)?"<null>":this.last));
        sb.append(',');
        sb.append("middle");
        sb.append('=');
        sb.append(((this.middle == null)?"<null>":this.middle));
        sb.append(',');
        sb.append("phoneNumber");
        sb.append('=');
        sb.append(((this.phoneNumber == null)?"<null>":this.phoneNumber));
        sb.append(',');
        sb.append("service");
        sb.append('=');
        sb.append(((this.service == null)?"<null>":this.service));
        sb.append(',');
        sb.append("thumbPath");
        sb.append('=');
        sb.append(((this.thumbPath == null)?"<null>":this.thumbPath));
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
        result = ((result* 31)+((this.middle == null)? 0 :this.middle.hashCode()));
        result = ((result* 31)+((this.phoneNumber == null)? 0 :this.phoneNumber.hashCode()));
        result = ((result* 31)+((this.last == null)? 0 :this.last.hashCode()));
        result = ((result* 31)+((this.imagePath == null)? 0 :this.imagePath.hashCode()));
        result = ((result* 31)+((this.service == null)? 0 :this.service.hashCode()));
        result = ((result* 31)+((this.thumbPath == null)? 0 :this.thumbPath.hashCode()));
        result = ((result* 31)+((int)(this.handleId^(this.handleId >>> 32))));
        result = ((result* 31)+((this.first == null)? 0 :this.first.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Member) == false) {
            return false;
        }
        Member rhs = ((Member) other);
        return (((((((((this.middle == rhs.middle)||((this.middle!= null)&&this.middle.equals(rhs.middle)))&&((this.phoneNumber == rhs.phoneNumber)||((this.phoneNumber!= null)&&this.phoneNumber.equals(rhs.phoneNumber))))&&((this.last == rhs.last)||((this.last!= null)&&this.last.equals(rhs.last))))&&((this.imagePath == rhs.imagePath)||((this.imagePath!= null)&&this.imagePath.equals(rhs.imagePath))))&&((this.service == rhs.service)||((this.service!= null)&&this.service.equals(rhs.service))))&&((this.thumbPath == rhs.thumbPath)||((this.thumbPath!= null)&&this.thumbPath.equals(rhs.thumbPath))))&&(this.handleId == rhs.handleId))&&((this.first == rhs.first)||((this.first!= null)&&this.first.equals(rhs.first))));
    }

}
