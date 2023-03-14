package sg.edu.nus.iss.paf_workshop23.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.paf_workshop23.repositories.OrderRepo;
import sg.edu.nus.iss.paf_workshop23.models.Order;
import sg.edu.nus.iss.paf_workshop23.models.SumOrder;

@RestController
@RequestMapping("/api/orders")
public class OrderAPIController {
    
    @Autowired
    OrderRepo orderRepo;

    @GetMapping("/{id}")
    public ResponseEntity<List<Order>> findOrderById(@PathVariable Integer id){

        List<Order> orders = orderRepo.findOrderById(id);

        if(orders.size() > 0){
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/total/{id}")
    public ResponseEntity<SumOrder> sumOrderById(@PathVariable Integer id){

        SumOrder sumOrder = orderRepo.sumOrderById(id);

        if(null != sumOrder.getOrderId()){
            return new ResponseEntity<>(sumOrder, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
