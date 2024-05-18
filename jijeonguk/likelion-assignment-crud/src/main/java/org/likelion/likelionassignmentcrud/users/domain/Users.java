package org.likelion.likelionassignmentcrud.users.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.likelionassignmentcrud.orders.domain.Orders;
import org.likelion.likelionassignmentcrud.users.api.dto.request.UsersUpdateReqDto;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long usersId;

    private String name;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private PayOption option;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Orders> ordersList = new ArrayList<>();

    @Builder
    public Users(String name, String phoneNumber, PayOption option) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.option = option;
    }

    public void update(UsersUpdateReqDto usersUpdateReqDto) {
        this.name = usersUpdateReqDto.name();
        this.phoneNumber = usersUpdateReqDto.phoneNumber();
    }
}