package org.likelion.likelionassignmentcrud.orders.application;

import org.hibernate.query.Order;
import org.likelion.likelionassignmentcrud.orders.api.dto.request.OrdersSaveReqDto;
import org.likelion.likelionassignmentcrud.orders.api.dto.request.OrdersUpdateReqDto;
import org.likelion.likelionassignmentcrud.orders.api.dto.response.OrdersInfoResDto;
import org.likelion.likelionassignmentcrud.orders.api.dto.response.OrdersListResDto;
import org.likelion.likelionassignmentcrud.orders.domain.Orders;
import org.likelion.likelionassignmentcrud.orders.domain.repository.OrdersRepository;
import org.likelion.likelionassignmentcrud.product.domain.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final org.likelion.likelionassignmentcrud.product.domain.repository.ProductRepository productRepository;

    public OrdersService(OrdersRepository ordersRepository, org.likelion.likelionassignmentcrud.product.domain.repository.ProductRepository productRepository) {
        this.ordersRepository = ordersRepository;
        this.productRepository = productRepository;
    }


    // Create
    @Transactional
    public void ordersSave(OrdersSaveReqDto ordersSaveReqDto) {
        Product product = productRepository.findById(ordersSaveReqDto.productId())
                .orElseThrow(IllegalArgumentException::new);

        Orders orders = Orders.builder()
                .costName(ordersSaveReqDto.costName())
                .location(ordersSaveReqDto.location())
                .product(product)
                .build();

        ordersRepository.save(orders);
    }

    // 작성자에 따른 게시물 조회
    public OrdersListResDto ordersFindProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(null);
        List<Orders> ordersList =  ordersRepository.findByProduct(product);
        List<OrdersInfoResDto> ordersInfoResDtos =
        ordersList.stream()
                .map(OrdersInfoResDto::from)
                .toList();

        return OrdersListResDto.from(ordersInfoResDtos);
    }

    // 업데이트
    public void ordersUpdate(Long ordersId, OrdersUpdateReqDto ordersUpdateReqDto) {
        Orders orders = ordersRepository.findById(ordersId).orElseThrow
                (() -> new IllegalArgumentException("ordersId가 없다." + ordersId));
        orders.update(ordersUpdateReqDto);
    }

    // 삭제
    @Transactional
    public void ordersDelete(Long ordersId) {
        Orders orders = ordersRepository.findById(ordersId).orElseThrow(IllegalArgumentException::new);
        ordersRepository.delete(orders);
    }
}
