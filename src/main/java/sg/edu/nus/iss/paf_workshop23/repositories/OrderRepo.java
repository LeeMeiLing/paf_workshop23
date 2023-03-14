package sg.edu.nus.iss.paf_workshop23.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.paf_workshop23.models.Order;

@Repository
public class OrderRepo {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final static String FIND_ORDERBYID_SQL = 
                "select o.id as order_id, o.order_date, o.customer_id," +
                " od.product_id," +
                " (quantity * unit_price * if(discount > 0, discount, 1)) as total_price," +
                " (od.quantity * p.standard_cost) as cost_price" +
                " from order_details as od" +
                " inner join products as p" +
                " on od.product_id = p.id" +
                " inner join orders as o" +
                " on od.order_id = o.id" +
                " where o.id = ?";

    public List<Order> findOrderById(Integer id){

        // return jdbcTemplate.queryForList(FIND_ORDERBYID_SQL, Order.class, id); // !!! check if this only works for result of 1 column
        return jdbcTemplate.query(FIND_ORDERBYID_SQL, BeanPropertyRowMapper.newInstance(Order.class), id); // mapping sql date works in this method
    }
}
