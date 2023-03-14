package sg.edu.nus.iss.paf_workshop23.services;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import sg.edu.nus.iss.paf_workshop23.models.Order;
import sg.edu.nus.iss.paf_workshop23.models.SumOrder;

@Service
public class OrderRestTemplateService {
    
    RestTemplate restTemplate = new RestTemplate();

    private static final String GETORDERBYID_ENDPOINT_URL = "http://localhost:4000/api/orders/{id}";
    private static final String GETSUMORDERBYID_ENDPOINT_URL = "http://localhost:4000/api/orders/total/{id}";

    public List<Order> findOrderById(Integer id) throws Exception{

        try{
            ResponseEntity<List<Order>> results = restTemplate.exchange(GETORDERBYID_ENDPOINT_URL, HttpMethod.GET, 
            null, new ParameterizedTypeReference<List<Order>>() {},id);  
            return results.getBody();  
        }catch(HttpClientErrorException ex){
            throw ex;
        }

    }

    public SumOrder sumOrderById(Integer id) throws Exception{

        try{

            ResponseEntity<SumOrder> result = restTemplate.exchange(GETSUMORDERBYID_ENDPOINT_URL, HttpMethod.GET, 
            null, new ParameterizedTypeReference<SumOrder>() {},id);
            return result.getBody(); 

        }catch(HttpClientErrorException ex){
            throw ex;
        }
    }
}
