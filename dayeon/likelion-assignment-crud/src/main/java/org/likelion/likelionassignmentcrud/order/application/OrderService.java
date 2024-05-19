package org.likelion.likelionassignmentcrud.order.application;

import org.likelion.likelionassignmentcrud.member.domain.Member;
import org.likelion.likelionassignmentcrud.member.domain.repository.MemberRepository;
import org.likelion.likelionassignmentcrud.order.api.dto.request.OrderSaveReqDto;
import org.likelion.likelionassignmentcrud.order.api.dto.request.OrderUpdateReqDto;
import org.likelion.likelionassignmentcrud.order.api.dto.response.OrderInfoResDto;
import org.likelion.likelionassignmentcrud.order.api.dto.response.OrderListResDto;
import org.likelion.likelionassignmentcrud.order.domain.Order;
import org.likelion.likelionassignmentcrud.order.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderService {
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;

    public OrderService(MemberRepository memberRepository, OrderRepository orderRepository) {
        this.memberRepository = memberRepository;
        this.orderRepository = orderRepository;
    }

    //Create
    @Transactional
    public void orderSave(OrderSaveReqDto orderSaveReqDto) {
        Member member = memberRepository.findById(orderSaveReqDto.memberId()).orElseThrow(IllegalArgumentException::new);

        Order order = Order.builder()
                .date(orderSaveReqDto.date())
                .item(orderSaveReqDto.item())
                .quantity(orderSaveReqDto.quantity())
                .member(member)
                .build();

        orderRepository.save(order);
    }

    //Read
    public OrderListResDto orderFindMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);

        List<Order> orders = orderRepository.findByMember(member);
        List<OrderInfoResDto> orderInfoResDtoList = orders.stream()
                .map(OrderInfoResDto::from)
                .toList();

        return OrderListResDto.from(orderInfoResDtoList);
    }

    //Update
    @Transactional
    public void orderUpdate(Long orderId, OrderUpdateReqDto orderUpdateReqDto) {
        Order order = orderRepository.findById(orderId).orElseThrow(IllegalArgumentException::new);

        order.update(orderUpdateReqDto);
    }

    //Delete
    @Transactional
    public void orderDelete(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(IllegalArgumentException::new);

        orderRepository.delete(order);
    }
}
