import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static java.util.Locale.filter;

public class Main {

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();
        HttpGet request = new HttpGet("https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats");
        CloseableHttpResponse response = httpClient.execute(request);

        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
        System.out.println(body);
       // String json = readString("");
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(body));
            JSONObject jsonObject = (JSONObject) obj;
            List<factInfo> factInfoList = new ArrayList<>();
            factInfoList.add(new factInfo(jsonObject));
            List<factInfo> filteredList = (List<factInfo>) factInfoList.stream()
                    .filter(value -> (value.getUpvotes() != null) && (value.getUpvotes() > 0));
            System.out.println(filteredList);
        } catch (IOException | ParseException | org.json.simple.parser.ParseException e){
            e.printStackTrace();
        }
    }
}
