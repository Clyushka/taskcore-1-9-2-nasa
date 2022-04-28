import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Main {
    public static final String URI = "https://api.nasa.gov/planetary/apod";
    public static final String API_KEY_PARAM_NAME = "api_key";
    public static final String API_KEY_VALUE = "pvhKHLrQ8HpaiJRaBqCldNHWpboxGynefnLpqr6c";

    public static void main(String[] args) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            //read image
            HttpGet getRequest = new HttpGet(new URIBuilder(URI)
                    .setParameter(API_KEY_PARAM_NAME, API_KEY_VALUE)
                    .build());
            CloseableHttpResponse getResponse = client.execute(getRequest);
            String statusLine = getResponse.getStatusLine().toString();

            String mediaURL = "";

            if (statusLine.contains("200") || statusLine.contains("304")) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                NasaObject nasaObject = mapper.readValue(
                        new String(getResponse.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8),
                        NasaObject.class);

                mediaURL = nasaObject.getUrl();
            }
            getResponse.close();

            getRequest = new HttpGet(mediaURL);
            getResponse = client.execute(getRequest);
            String fileName = mediaURL.split("/")[mediaURL.split("/").length - 1];

            //write image
            File imageFile = new File(fileName);
            OutputStream outToFile = new FileOutputStream(imageFile);
            if (imageFile.isFile() && !imageFile.exists()) {
                imageFile.createNewFile();
            }
            byte[] imageBytes = getResponse.getEntity().getContent().readAllBytes();
            outToFile.write(imageBytes);

            //close resources
            outToFile.flush();
            outToFile.close();
            getResponse.close();

        } catch (IOException | URISyntaxException | ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getLocalizedMessage());
            Arrays.stream(e.getStackTrace()).forEach(System.out::println);
            System.exit(0);
        }

    }
}
