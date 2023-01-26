package ir.maktab.menu;

import ir.maktab.entity.Orders;
import ir.maktab.exceptions.OrderNotFoundException;
import ir.maktab.service.OrdersService;
import ir.maktab.util.DateFormatter;

import java.util.List;

public class PrintOrderList {
    private final OrdersService ordersService = new OrdersService();
    private final DateFormatter dateFormatter = new DateFormatter();

    public void getOrders(Long customerId) {
        List<Orders> list = ordersService.findAllByCustomerId(customerId);
        if (list == null) {
            throw new OrderNotFoundException("there are no orders.");
        } else {
            System.out.println("________________________________________________________________________________________________________________________________________________________________");
            System.out.println("| Id  |    Proposed Price    |            Description            |      Date And Time     |          Address          |   Order Status   |   Under Duty Name   |");
            System.out.println("________________________________________________________________________________________________________________________________________________________________");
            for (Orders orders : list) {
                System.out.printf("|  %-3s|   %-19s| %-34s|   %-21s| %-26s|  %-16s|  %-19s|%n",
                        orders.getId(),
                        orders.getProposedPrice(),
                        orders.getDescription(),
                        dateFormatter.formatter(orders.getDateAndTime()),
                        orders.getAddress(),
                        orders.getOrderStatus(),
                        orders.getUnderDuty().getName());
                System.out.println("________________________________________________________________________________________________________________________________________________________________");
            }
        }
    }
}
