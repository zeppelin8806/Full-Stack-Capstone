package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Orders;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcOrdersDao implements OrdersDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcOrdersDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    //getOrdersByID needs to be filled out
    @Override
    public Orders getOrderById(int orderId) {
        Orders orders = null;
        String sql = "SELECT * FROM orders WHERE order_id = ?;";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, orderId);
            if(results.next()){
                orders = mapRowToOrders(results);
                //return mapRowToOrders(results);
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database");
        } catch (DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation", e);
        }
        return orders;
    }
    //getOrders() needs to be filled out
    @Override
    public List<Orders> getOrders() {
        List<Orders> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders ORDER BY order_id;";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()){
                Orders order = mapRowToOrders(results);
                orders.add(order);
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database");
        } catch (DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation", e);
        }
        return orders;
    }

    @Override
    public Orders updateOrders(Orders order) {
        Orders updatedOrder = null;
        String sql = "UPDATE orders SET order_name = ?, second_ideal = ?, third_ideal = ?, fourth_ideal = ?, fifth_ideal = ?, glyph = ?"
                    + " WHERE order_id = ?;";

        try{
            int numberOfRows = jdbcTemplate.update(sql, order.getName(), order.getSecondIdeal(), order.getThirdIdeal(), order.getFourthIdeal(), order.getFifthIdeal(), order.getGlyph(), order.getOrderId());
            if(numberOfRows ==0){
                throw new DaoException("Zero rows affected, expected at least one");
            }else{
                updatedOrder = getOrderById(order.getOrderId());
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database");
        } catch (DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation", e);
        }
        return updatedOrder;
    }

    private Orders mapRowToOrders(SqlRowSet results){
        Orders orders = new Orders();
        orders.setOrderId(results.getInt("order_id"));
        orders.setName(results.getString("order_name"));
        orders.setSecondIdeal(results.getString("second_ideal"));
        orders.setThirdIdeal(results.getString("third_ideal"));
        orders.setFourthIdeal(results.getString("fourth_ideal"));
        orders.setFifthIdeal(results.getString("fifth_ideal"));
        orders.setGlyph(results.getString("glyph"));
        return orders;
    }
}
