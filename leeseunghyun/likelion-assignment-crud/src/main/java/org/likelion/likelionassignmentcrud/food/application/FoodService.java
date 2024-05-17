package org.likelion.likelionassignmentcrud.food.application;

import java.util.List;
import org.likelion.likelionassignmentcrud.food.api.dto.request.FoodSaveReqDto;
import org.likelion.likelionassignmentcrud.food.api.dto.request.FoodUpdateReqDto;
import org.likelion.likelionassignmentcrud.food.api.dto.response.FoodListResDto;
import org.likelion.likelionassignmentcrud.food.api.dto.response.FoodInfoResDto;
import org.likelion.likelionassignmentcrud.food.domain.Food;
import org.likelion.likelionassignmentcrud.food.domain.repository.FoodRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class FoodService {
    private final FoodRepository foodRepository;

    public FoodService(FoodRepository memberRepository) {
        this.foodRepository = memberRepository;
    }

    // Create
    @Transactional
    public void foodSave(FoodSaveReqDto foodSaveReqDto) {
        Food food = Food.builder()
                .name(foodSaveReqDto.name())
                .price(foodSaveReqDto.price())
                .type(foodSaveReqDto.type())
                .build();

        foodRepository.save(food);
    }

    // Read All
    public FoodListResDto foodFindAll() {
        List<Food> foods = foodRepository.findAll();

        List<FoodInfoResDto> foodInfoResDtoList = foods.stream()
                .map(FoodInfoResDto::from)
                .toList();

        return FoodListResDto.from(foodInfoResDtoList);
    }

    // Read One
    public FoodInfoResDto foodFindOne(Long foodId) {
        Food food = foodRepository.findById(foodId).orElseThrow(IllegalArgumentException::new);

        return FoodInfoResDto.from(food);
    }

    @Transactional
    public void foodUpdate(Long foodId, FoodUpdateReqDto foodUpdateReqDto) {
        Food food = foodRepository.findById(foodId).orElseThrow(IllegalArgumentException::new);

        food.update(foodUpdateReqDto);
    }

    // Delete
    @Transactional
    public void foodDelete(Long foodId) {
        Food food = foodRepository.findById(foodId).orElseThrow(IllegalArgumentException::new);

        foodRepository.delete(food);
    }

}



