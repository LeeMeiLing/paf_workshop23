package sg.edu.nus.iss.paf_workshop23.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.iss.paf_workshop23.models.Order;
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
    public String processSearch(@ModelAttribute("searchObject") SearchRequest sr, BindingResult result, Model model){

        List<Order> orders = orts.findOrderById(sr.getOrderId());
        model.addAttribute("orders",orders);
        return "orderlist";
    }

}
