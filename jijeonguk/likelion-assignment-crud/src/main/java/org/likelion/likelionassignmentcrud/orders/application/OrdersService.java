package org.likelion.likelionassignmentcrud.orders.application;

import org.aspectj.weaver.ast.Or;
import org.likelion.likelionassignmentcrud.orders.api.dto.request.OrdersSaveReqDto;
import org.likelion.likelionassignmentcrud.orders.api.dto.request.OrdersUpdateReqDto;
import org.likelion.likelionassignmentcrud.orders.api.dto.response.OrdersInfoResDto;
import org.likelion.likelionassignmentcrud.orders.api.dto.response.OrdersListResDto;
import org.likelion.likelionassignmentcrud.orders.domain.Orders;
import org.likelion.likelionassignmentcrud.orders.domain.repository.OrdersRepository;
import org.likelion.likelionassignmentcrud.users.domain.Users;
import org.likelion.likelionassignmentcrud.users.domain.repository.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)

public class OrdersService {
    private final UsersRepository usersRepository;
    private final OrdersRepository ordersRepository;

    public OrdersService(UsersRepository usersRepository, OrdersRepository ordersRepository) {
        this.usersRepository = usersRepository;
        this.ordersRepository = ordersRepository;
    }

    @Transactional
    public void ordersSave(OrdersSaveReqDto ordersSaveReqDto) {
        Users users = usersRepository.findById(ordersSaveReqDto.usersId()).orElseThrow(IllegalArgumentException::new);

        Orders orders = Orders.builder()
                .address(ordersSaveReqDto.address())
                .price(ordersSaveReqDto.price())
                .users(users)
                .build();

        ordersRepository.save(orders);
    }
        public OrdersListResDto ordersFindUsers(Long usersId){
        Users users = usersRepository.findById(usersId).orElseThrow(IllegalArgumentException::new);

            List<Orders> ordersList = ordersRepository.findByUsers(users);
            List<OrdersInfoResDto> ordersInfoResDtoList = ordersList.stream()
                    .map(OrdersInfoResDto::from)
                    .toList();

            return OrdersListResDto.from(ordersInfoResDtoList);
    }

    //Update
    //Transactional 어노테이션 다시 공부
    @Transactional
    public void ordersUpdate(Long ordersId, OrdersUpdateReqDto ordersUpdateReqDto) {
        Orders orders = ordersRepository.findById(ordersId).orElseThrow(IllegalArgumentException::new);

        orders.update(ordersUpdateReqDto);
    }

    //Delete
    @Transactional
    public void ordersDelete(Long ordersId) {
        Orders orders = ordersRepository.findById(ordersId).orElseThrow(IllegalArgumentException::new);

        ordersRepository.delete(orders);
    }
}
