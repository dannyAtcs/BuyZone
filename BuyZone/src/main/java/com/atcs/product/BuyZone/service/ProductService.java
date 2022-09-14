package com.atcs.product.BuyZone.service;

import com.atcs.product.BuyZone.dto.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class ProductService {

    @Autowired
    AuthService authService;
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);

    private final RestTemplate localApiClient;

    @Autowired
    public ProductService(RestTemplate localApiClient) {
        this.localApiClient = localApiClient;
    }

    public List<Product> getProduct(HttpServletRequest req, HttpServletResponse res, long id) throws IOException {

        if(authService.checkAuthToken(req))
        {
            String token = authService.extractToken(req);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", "Bearer " + token);
            HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);
            ResponseEntity<Product[]> pr = localApiClient.exchange("http://localhost:8081/product/all", HttpMethod.GET,entity, Product[].class);

            return Arrays.asList(pr.getBody());

        }
        else
        {
             res.sendRedirect("/error/access-denied");
        }

        return null;
    }

    public List<Product> getByParameter(HttpServletRequest req, HttpServletResponse res, String parameter) throws IOException {

        if (authService.checkAuthToken(req)) {
            String token = authService.extractToken(req);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", "Bearer " + token);
            HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);
            //  String str = "{/name}";
            ResponseEntity<Product[]> pr = localApiClient.exchange("http://localhost:8081/product/searchResults/" + parameter, HttpMethod.GET, entity, Product[].class);

            return Arrays.asList(pr.getBody());

        } else {
            res.sendRedirect("/error/access-denied");
        }

        return null;
    }



    public List<Product> getFilteredProducts(HttpServletRequest req, HttpServletResponse res, Double price1, Double price2) throws IOException {

        if(authService.checkAuthToken(req))
        {
            String token = authService.extractToken(req);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", "Bearer " + token);
            HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);
            //  String str = "{/name}";
            ResponseEntity<Product[]> pr = localApiClient.exchange("http://localhost:8081/product/filter/" + price1 + "/" + price2, HttpMethod.GET,entity, Product[].class);

            return Arrays.asList(pr.getBody());

        }
        else
        {
            res.sendRedirect("/error/access-denied");
        }

        return null;
    }

    public List<Product> getByParameterHome(HttpServletRequest req, HttpServletResponse res, String parameter) throws IOException {


        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);
        ResponseEntity<Product[]> pr = localApiClient.exchange("http://localhost:8081/product/searchResults/" + parameter, HttpMethod.GET, entity, Product[].class);
        log.info("pr{}",pr);
        return Arrays.asList(pr.getBody());

    }
    public List<Product> getAllProduct(HttpServletRequest req, HttpServletResponse res, long id) throws IOException {


        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);
        ResponseEntity<Product[]> pr = localApiClient.exchange("http://localhost:8081/product/all", HttpMethod.GET,entity, Product[].class);

        return Arrays.asList(pr.getBody());

    }

    public Product getByName(HttpServletRequest req, HttpServletResponse res, String name) throws IOException {

        if(authService.checkAuthToken(req))
        {
            String token = authService.extractToken(req);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", "Bearer " + token);
            HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);
          //  String str = "{/name}";
            ResponseEntity<Product> pr = localApiClient.exchange("http://localhost:8081/product/name/" + name, HttpMethod.GET,entity, Product.class);

            return pr.getBody();

        }
        else
        {
            res.sendRedirect("/error/access-denied");
        }

        return null;
    }

    public Product getByBrand(HttpServletRequest req, HttpServletResponse res, String name) throws IOException {

        if(authService.checkAuthToken(req))
        {
            String token = authService.extractToken(req);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", "Bearer " + token);
            HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);
            //  String str = "{/name}";
            ResponseEntity<Product> pr = localApiClient.exchange("http://localhost:8081/product/brand/" + name, HttpMethod.GET,entity, Product.class);

            return pr.getBody();

        }
        else
        {
            res.sendRedirect("/error/access-denied");
        }

        return null;
    }

    public Product getByCode(HttpServletRequest req, HttpServletResponse res, Long id) throws IOException {

        if(authService.checkAuthToken(req))
        {
            String token = authService.extractToken(req);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", "Bearer " + token);
            HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);
            //  String str = "{/name}";
            ResponseEntity<Product> pr = localApiClient.exchange("http://localhost:8081/product/code/" + id, HttpMethod.GET,entity, Product.class);

            return pr.getBody();

        }
        else
        {
            res.sendRedirect("/error/access-denied");
        }

        return null;
    }

    public Integer getExpectedDay(HttpServletRequest req, HttpServletResponse res, String pincode) throws IOException {

        if(authService.checkAuthToken(req))
        {
            String token = authService.extractToken(req);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", "Bearer " + token);
            HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);
            //  String str = "{/name}";
            ResponseEntity<Integer> pr = localApiClient.exchange("http://localhost:8081/product/service/" + pincode, HttpMethod.GET,entity, Integer.class);

            return pr.getBody();

        }
        else
        {
            res.sendRedirect("/error/access-denied");
        }

        return null;
    }

    public List<Long> getPrices(HttpServletRequest req, HttpServletResponse res, List<Long> ids) throws IOException {

        if(authService.checkAuthToken(req))
        {
            String token = authService.extractToken(req);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", "Bearer " + token);
            HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);
            //  String str = "{/name}";
            ResponseEntity<Long> pr = localApiClient.exchange("http://localhost:8081/product/prices", HttpMethod.GET,entity, Long.class);

            return Arrays.asList(pr.getBody());

        }
        else
        {
            res.sendRedirect("/error/access-denied");
        }

        return null;
    }
}
