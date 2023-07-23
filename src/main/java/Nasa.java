import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
public class Nasa {
    private String copyright;
    private String date;
    private String explanation;
    private String hdurl;
    private String media_type;
    private String service_version;
    private String title;
    private String url;
}
