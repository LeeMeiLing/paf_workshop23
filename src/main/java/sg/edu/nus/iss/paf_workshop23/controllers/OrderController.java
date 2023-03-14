package sg.edu.nus.iss.paf_workshop23.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

import sg.edu.nus.iss.paf_workshop23.models.Order;
import sg.edu.nus.iss.paf_workshop23.models.SumOrder;
import sg.edu.nus.iss.paf_workshop23.payloads.SearchRequest;
import sg.edu.nus.iss.paf_workshop23.services.OrderRestTemplateService;

@Controller
@RequestMapping("/orders")
public class OrderController {
    
    @Autowired
    OrderRestTemplateService orts;

    @GetMapping
    public String searchOrder(Model model){
        
        SearchRequest sr = new SearchRequest();
        model.addAttribute("searchObject", sr);
        return "searchorder";
    }

    @PostMapping("/search")
    public String processSearch(@ModelAttribute("searchObject") SearchRequest sr, BindingResult result, Model model) throws Exception{

        try{
            List<Order> orders = orts.findOrderById(sr.getOrderId());
            model.addAttribute("orders",orders);
            return "orderlist";
        }catch(HttpClientErrorException ex){
            if(HttpStatus.NOT_FOUND == ex.getStatusCode()){
                ObjectError err = new ObjectError("notFound", "Order ID Not Found");
                result.addError(err);
                return "searchorder";
            }else{
                throw ex;
            }
        }
        
    }

    @GetMapping("/searchsum")
    public String searchSumOrder(Model model){
  
        return "searchsumorder";
    }

    @GetMapping("/total")
    public String processSumOrder(@RequestParam Integer orderId, Model model) throws Exception{
        
        try{
            SumOrder sumOrder = orts.sumOrderById(orderId);
            model.addAttribute("sumOrder", sumOrder); // sumOrder is not null if ID not found, but the fields are null
            return "sumorder";
        }catch(HttpClientErrorException ex){
            if(HttpStatus.NOT_FOUND == ex.getStatusCode()){
                model.addAttribute("notFound", "Order ID Not Found");
                return "searchsumorder";
            }else{
                throw ex;
            }
        }
    }

}
