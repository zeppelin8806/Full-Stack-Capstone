package com.techelevator.controller;

import com.techelevator.dao.OrdersCharactersDao;
import com.techelevator.dao.OrdersDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Orders;
import com.techelevator.model.OrdersCharacters;
import com.techelevator.model.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class OrdersCharacterController {
    private OrdersDao ordersDao;
    private OrdersCharactersDao ordersCharactersDao;

    public OrdersCharacterController(OrdersDao ordersDao, OrdersCharactersDao ordersCharactersDao) {
        this.ordersDao = ordersDao;
        this.ordersCharactersDao = ordersCharactersDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/orders", method = RequestMethod.GET)
    public List<Orders> listOrders(){
        try{
            return ordersDao.getOrders();
        } catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO Error - " + e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/orders/{id}", method = RequestMethod.GET)
    public Orders orderById(@PathVariable int id){
        try{
            return ordersDao.getOrderById(id);
        } catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO Error - " + e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/orders/{id}/characters", method = RequestMethod.GET)
    public List<OrdersCharacters> ordersAndCharacters(@PathVariable int id){
        try{
            return ordersCharactersDao.getOrderById(id);
        } catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO Error - " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/orders/{id}", method = RequestMethod.PUT)
    public Orders updateOrderById(@Valid @RequestBody Orders changedOrder, @PathVariable int id, Principal principal){
        try{
            User user = new User();
            user.setUsername(principal.getName());
            changedOrder.setOrderId(id);
            return ordersDao.updateOrders(changedOrder);
        } catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAP Error = " + e.getMessage());
        }
    }
}
