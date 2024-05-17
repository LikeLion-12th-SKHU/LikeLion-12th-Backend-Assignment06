package org.likelion.likelionassignmentcrud.food.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.likelionassignmentcrud.food.api.dto.request.FoodUpdateReqDto;
import org.likelion.likelionassignmentcrud.orders.domain.Orders;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private Long foodId;

    private String name; //음식 이름
    private int price ; //음식 가격
    private String type; // 음식 종류(중식 한식 일식)

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Orders> orders = new ArrayList<>();

    @Builder
    private Food(String name, int price , String type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }
    public void update(FoodUpdateReqDto foodUpdateReqDto) {
        this.name = foodUpdateReqDto.name();
        this.price = foodUpdateReqDto.price();
    }
}

