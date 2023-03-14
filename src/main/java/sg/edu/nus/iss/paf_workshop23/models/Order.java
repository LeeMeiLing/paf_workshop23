package sg.edu.nus.iss.paf_workshop23.models;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    
    // select o.id as order_id, o.order_date, o.customer_id,
    // od.order_id, od.product_id, 
    // (quantity * unit_price * if(discount > 0, discount, 1)) as total_price,
    // (od.quantity * p.standard_cost) as cost_price

    private Integer orderId;

    private Date orderDate;

    private Integer customerId;

    private Integer productId;

    private Float totalPrice;

    private Float costPrice;

}
