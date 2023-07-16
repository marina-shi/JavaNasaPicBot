import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = Config.Api;
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000) // максимальное время ожидания подключения к серверу
                        .setSocketTimeout(30000) // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();

        ObjectMapper mapper = new ObjectMapper();

        // Получить информацию о картинке дня
        CloseableHttpResponse response = httpClient.execute(new HttpGet(url));
        Nasa answer = mapper.readValue(response.getEntity().getContent(), Nasa.class);

        // Получить название картинки дня по исходной ссылке
        String[] arr = answer.getUrl().split("/");
        String urlName = arr[arr.length - 1];

        // Скачать и сохранить картинку дня
        CloseableHttpResponse image = httpClient.execute(new HttpGet(answer.getUrl()));
        FileOutputStream fos = new FileOutputStream(urlName); // открыли файл на запись
        image.getEntity().writeTo(fos);

    }
}
