package org.likelion.likelionassignmentcrud.orders.application;

import java.util.List;
import org.likelion.likelionassignmentcrud.food.domain.Food;
import org.likelion.likelionassignmentcrud.food.domain.repository.FoodRepository;
import org.likelion.likelionassignmentcrud.orders.api.dto.request.OrdersSaveReqDto;
import org.likelion.likelionassignmentcrud.orders.api.dto.request.OrdersUpdateReqDto;
import org.likelion.likelionassignmentcrud.orders.api.dto.response.OrdersInfoResDto;
import org.likelion.likelionassignmentcrud.orders.api.dto.response.OrdersListResDto;
import org.likelion.likelionassignmentcrud.orders.domain.Orders;
import org.likelion.likelionassignmentcrud.orders.domain.repository.OrdersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class OrdersService {
    private final FoodRepository foodRepository;
    private final OrdersRepository ordersRepository;

    public OrdersService(FoodRepository foodRepository, OrdersRepository ordersRepository) {
        this.foodRepository = foodRepository;
        this.ordersRepository = ordersRepository;
    }

    // Create
    @Transactional
    public void ordersSave(OrdersSaveReqDto ordersSaveReqDto) {
        Food food = foodRepository.findById(ordersSaveReqDto.foodId()).orElseThrow(IllegalArgumentException::new);

        Orders orders = Orders.builder()
                .shippingAddress(ordersSaveReqDto.shippingAddress())
                .paymentInfo(ordersSaveReqDto.paymentInfo())
                .food(food)
                .build();

        ordersRepository.save(orders);
    }

    public OrdersListResDto ordersFindFood(Long foodId) {
        Food food = foodRepository.findById(foodId).orElseThrow(IllegalArgumentException::new);

        List<Orders> orderss = ordersRepository.findByfood(food);
        List<OrdersInfoResDto> ordersInfoResDtoList = orderss.stream()
                .map(OrdersInfoResDto::from)
                .toList();

        return OrdersListResDto.from(ordersInfoResDtoList);
    }
    @Transactional
    public void ordersUpdate(Long ordersId, OrdersUpdateReqDto ordersUpdateReqDto) {
        Orders orders = ordersRepository.findById(ordersId).orElseThrow(IllegalArgumentException :: new);

        ordersRepository.delete(orders);
    }
    @Transactional
    public void ordersDelete(Long ordersId) {
        Orders orders = ordersRepository.findById(ordersId).orElseThrow(IllegalArgumentException::new);

        ordersRepository.delete(orders);
    }

}