package org.likelion.likelionassignmentcrud.developer.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.likelionassignmentcrud.developer.api.dto.request.DeveloperUpdateReqDto;
import org.likelion.likelionassignmentcrud.game.domain.Game;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Developer {

    @Id
    @Column(name = "developer_name", nullable = false, unique = true)
    private String name;

    private String country;
    private String establishedDate;

    @OneToMany(mappedBy = "developer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Game> games = new ArrayList<>();

    @Builder
    private Developer(String name, String country, String establishedDate) {
        this.name = name;
        this.country = country;
        this.establishedDate = establishedDate;
    }

    public void update(DeveloperUpdateReqDto developerUpdateReqDto) {
        this.country = developerUpdateReqDto.country();
    }
}
