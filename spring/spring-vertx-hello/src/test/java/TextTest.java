import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedInputStream;
import java.io.IOException;

public class TextTest {
    public static void main(String[] args) {
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String json = "{\n" +
                "  \"lang\": \"zh-tw\",\n" +
                "  \"userToken\": \"6b455559-5911-411e-b3d6-d65c1cb0051f\",\n" +
                "  \"isSmartMode\": true,\n" +
                "  \"query\": \"幫我找黃致彥\"\n" +
                "} ";
        HttpEntity<String> request =
                new HttpEntity<>(json, headers);
        template.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                System.out.println(response.getRawStatusCode());
                return response.getStatusCode() != HttpStatus.OK;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                System.out.println(response.getStatusCode());
                System.out.println(response.getStatusText());
                BufferedInputStream bufferedInputStream = new BufferedInputStream(response.getBody());
                byte[] buffer = new byte[1024];
                StringBuffer result = new StringBuffer();
                int length = 0;
                length = bufferedInputStream.read(buffer);
                while(length != -1) {
                    result.append(new String(buffer));
                    length = bufferedInputStream.read(buffer);
                }
                System.out.println("result:" + result);
            }
        });
        ResponseEntity<String> responseEntity = template.postForEntity("https://textdms.deltaww.com/dialogue_system/interaction", request, String.class);
        System.out.println(responseEntity.getBody());
    }
}
