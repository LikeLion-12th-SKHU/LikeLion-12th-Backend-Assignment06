package org.likelion.likelionassignmentcrud.orders.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.likelionassignmentcrud.orders.api.dto.request.OrdersUpdateReqDto;
import org.likelion.likelionassignmentcrud.product.domain.Product;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private Long ordersId;

    private String costName; // 주문자 이름
    private String location; // 배송 지역

    // 작성자
    @ManyToOne(fetch = FetchType.LAZY) // product와 연결
    @JoinColumn(name = "product_id") // product_id로 조인 받음
    private Product product;

    @Builder
    public Orders(String costName, String location, Product product) {
        this.costName = costName;
        this.location = location;
        this.product = product;
    }

    public void update(OrdersUpdateReqDto ordersUpdateReqDto) {
        this.costName = ordersUpdateReqDto.costName();
        this.location = ordersUpdateReqDto.location();
    }
}
