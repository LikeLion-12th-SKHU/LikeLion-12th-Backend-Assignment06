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
    public ResponseEntity<String> postSave(@RequestBody OrdersSaveReqDto ordersSaveReqDto) {
        ordersService.ordersSave(ordersSaveReqDto);
        return new ResponseEntity<>("주문 저장!", HttpStatus.CREATED);
    }

    @GetMapping("/{usersId}")
    public ResponseEntity<OrdersListResDto> myPostFindAll(@PathVariable("usersId") Long usersId) {
        OrdersListResDto ordersListResDto = ordersService.ordersFindUsers(usersId);
        return new ResponseEntity<>(ordersListResDto, HttpStatus.OK);
    }

    // 주문 수정
    @PatchMapping("/{ordersId}")
    public ResponseEntity<String> ordersUpdate(@PathVariable("ordersId") Long ordersId,
                                               @RequestBody OrdersUpdateReqDto ordersUpdateReqDto) {
        ordersService.ordersUpdate(ordersId, ordersUpdateReqDto);
        return new ResponseEntity<>("주문 수정", HttpStatus.OK);
    }

    //주문 삭제
    @DeleteMapping("/{ordersId}")
    public ResponseEntity<String> ordersDelete(@PathVariable("ordersId") Long ordersId){
        ordersService.ordersDelete(ordersId);
        return new ResponseEntity<>("주문 삭제", HttpStatus.OK);
    }

}