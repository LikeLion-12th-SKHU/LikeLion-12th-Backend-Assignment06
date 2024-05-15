package org.likelion.likelionassignmentcrud.orders.api;

import org.likelion.likelionassignmentcrud.orders.api.dto.request.OrdersSaveReqDto;
import org.likelion.likelionassignmentcrud.orders.api.dto.request.OrdersUpdateReqDto;
import org.likelion.likelionassignmentcrud.orders.api.dto.response.OrdersListResDto;
import org.likelion.likelionassignmentcrud.orders.application.OrdersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> ordersSave(@RequestBody OrdersSaveReqDto ordersSaveReqDto) {
        ordersService.ordersSave(ordersSaveReqDto);
        return new ResponseEntity<>("주문 저장!", HttpStatus.CREATED);
    }

    // 작성자에 따른 게시물리스트 불러오기
    @GetMapping("/{productId}")
    public ResponseEntity<OrdersListResDto> myOrdersFindAll(@PathVariable Long productId) {
        OrdersListResDto ordersListResDto =  ordersService.ordersFindProduct(productId);
        return new ResponseEntity<>(ordersListResDto, HttpStatus.OK);
    }

    // 업데이트
    @PatchMapping("/{ordersId}")
    public ResponseEntity<String> ordersUpdate(@PathVariable Long ordersId,
                                              @RequestBody OrdersUpdateReqDto ordersUpdateReqDto) {
        ordersService.ordersUpdate(ordersId, ordersUpdateReqDto);
        return new ResponseEntity<>("주문 수정!", HttpStatus.OK);
    }

    @DeleteMapping("/{ordersId}")
    public ResponseEntity<String> ordersDelete(@PathVariable Long ordersId) {
        ordersService.ordersDelete(ordersId);
        return new ResponseEntity<>("주문 삭제!", HttpStatus.OK);
    }

}
