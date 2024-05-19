package org.likelion.likelionassignmentcrud.order.api.dto;

import org.likelion.likelionassignmentcrud.order.api.dto.request.OrderSaveReqDto;
import org.likelion.likelionassignmentcrud.order.api.dto.request.OrderUpdateReqDto;
import org.likelion.likelionassignmentcrud.order.api.dto.response.OrderListResDto;
import org.likelion.likelionassignmentcrud.order.application.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> orderSave(@RequestBody OrderSaveReqDto orderSaveReqDto) {
        orderService.orderSave(orderSaveReqDto);

        return new ResponseEntity<>("발주 내역 저장!", HttpStatus.CREATED);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<OrderListResDto> myOrderFindAll(@PathVariable("memberId") Long memberId) {
        OrderListResDto orderListResDto = orderService.orderFindMember(memberId);

        return new ResponseEntity<>(orderListResDto, HttpStatus.OK);
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<String> orderUpdate(@PathVariable("orderId") Long orderId, @RequestBody OrderUpdateReqDto orderUpdateReqDto) {
        orderService.orderUpdate(orderId, orderUpdateReqDto);

        return new ResponseEntity<>("발주 내역 수정!", HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> orderDelete(@PathVariable("orderId") Long orderId) {
        orderService.orderDelete(orderId);

        return new ResponseEntity<>("발주 내역 삭제!", HttpStatus.OK);
    }
}
