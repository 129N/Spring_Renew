package org.mik.yftwrg.Component;

import io.jsonwebtoken.io.IOException;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
@Setter
//@Component
public class JwtInterceptor implements ClientHttpRequestInterceptor {
    private String token;


    private String tokenPrefix;

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request,
            byte[] body,
            ClientHttpRequestExecution execution)
            throws IOException, java.io.IOException {
        if (token!=null){
            request.getHeaders().
                    set(HttpHeaders.AUTHORIZATION,
                    tokenPrefix + " "+token);
        }

        return execution.execute(request, body);

    }

}
