package com.chatApp.chatApp.interceptor;

import com.chatApp.chatApp.mapper.BearerTokenWrapper;
import com.chatApp.chatApp.mapper.MetaResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class ChatappInterceptor implements HandlerInterceptor {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${authentication.url}")
    private String authURL;


    private BearerTokenWrapper tokenWrapper;
    public ChatappInterceptor(BearerTokenWrapper tokenWrapper) {
        this.tokenWrapper = tokenWrapper;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info ("Pre Handle method is Calling");

            String headerAuth = request.getHeader("Authorization");
            if (headerAuth!=null && headerAuth.startsWith("Bearer ")) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.set("Authorization", request.getHeader("Authorization"));
                HttpEntity<String> entity = new HttpEntity<String>("", headers);
                ResponseEntity<MetaResponse> test = null;
                try {
                    test = restTemplate.exchange(authURL, HttpMethod.GET, entity, MetaResponse.class);
                    tokenWrapper.setToken(headerAuth);
                } catch (Exception e) {
                    log.info(String.valueOf(e));
                }
                if (test == null || test.getStatusCode() != HttpStatus.OK) {
                    JSONObject json = new JSONObject();
                    JSONObject metaJson = new JSONObject();

                    metaJson.put("msg", "FAILED");
                    metaJson.put("statusCode", HttpStatus.UNAUTHORIZED.value());
                    metaJson.put("error", "User not authenticated");
                    json.put("data", "User not authenticated");
                    json.put("meta", metaJson);

                    response.getWriter().write(json.toString());
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());

                    return false;
                }
            }else {
                JSONObject json = new JSONObject();
                JSONObject metaJson = new JSONObject();

                metaJson.put("msg", "FAILED");
                metaJson.put("statusCode", HttpStatus.UNAUTHORIZED.value());
                metaJson.put("error", "User not authenticated");
                json.put("data", "User not authenticated");
                json.put("meta", metaJson);

                response.getWriter().write(json.toString());
                response.setStatus(HttpStatus.UNAUTHORIZED.value());

                return false;
            }

        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {

        System.out.println("Post Handle method is Calling");
    }

    @Override
    public void afterCompletion
            (HttpServletRequest request, HttpServletResponse response, Object
                    handler, Exception exception) throws Exception {

        System.out.println("Request and Response is completed");
    }
}
