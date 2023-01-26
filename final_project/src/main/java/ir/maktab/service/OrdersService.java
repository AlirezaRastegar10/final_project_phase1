package ir.maktab.service;


import ir.maktab.entity.Orders;
import ir.maktab.repository.OrdersRepository;

import java.util.List;

public class OrdersService {

    private final OrdersRepository ordersRepository = new OrdersRepository();

    public void create(Orders orders) {
        ordersRepository.create(orders);
    }

    public Orders findById(Long id){
        return ordersRepository.findById(id);
    }

    public List<Orders> findAll(){
        return ordersRepository.findAll();
    }

    public void delete(Long id){
        ordersRepository.delete(id);
    }

    public List<Orders> findAllByCustomerId(Long id) {
        return ordersRepository.getOrdersByCustomerId(id);
    }
}
