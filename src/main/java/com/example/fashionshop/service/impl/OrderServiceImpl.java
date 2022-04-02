package com.example.fashionshop.service.impl;

import com.example.fashionshop.model.Order;
import com.example.fashionshop.model.dto.requestDto.OrderUpdateReqDto;
import com.example.fashionshop.repository.OrderRepository;
import com.example.fashionshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    /***
     *
     * @param order the product that would be added in DB
     * @return new product which has added
     */
    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    /***
     *
     * @param id with the help of it will find the object from DB.
     * @return returns founded object or throws @ResponseStatusException(BAD_REQUEST).
     */
    @Override
    public List<Order> getAllById(String id) {
        return orderRepository
                .getAllByUserId(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Orders with user_id:" + id + "  not found in database")
                );
    }

    /***
     *
     * @return all data from DB, if there is not any data will return empty List.
     */
    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }


    @Transactional
    @Override
    public Order update(String id, OrderUpdateReqDto reqDto) {
//        Order fromDb = orderRepository
//                .findById(id)
//                .orElseThrow(() -> new ResponseStatusException(
//                        HttpStatus.BAD_REQUEST,
//                        "product with id:" + id + "  not found in database")
//                );
//        fromDb.setCount(reqDto.getCount());
//        fromDb.setOrderStatus(reqDto.getOrderStatus());
//
//        return fromDb;
        return null;
    }

    @Override
    public void delete(String id) {

    }
}


