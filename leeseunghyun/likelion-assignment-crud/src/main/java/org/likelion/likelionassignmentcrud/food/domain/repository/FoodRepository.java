package org.likelion.likelionassignmentcrud.food.domain.repository;

import org.likelion.likelionassignmentcrud.food.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;
public interface FoodRepository extends JpaRepository<Food, Long>{
}
