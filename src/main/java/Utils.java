import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class Utils {
    static CloseableHttpClient httpClient = HttpClients.createDefault();
    static ObjectMapper mapper = new ObjectMapper();

    // Получить информацию о картинке дня
    public static String getUrl(String url) throws IOException {
        CloseableHttpResponse response = httpClient.execute(new HttpGet(url));
        Nasa answer = mapper.readValue(response.getEntity().getContent(), Nasa.class);
        return answer.getUrl();
    }
}
