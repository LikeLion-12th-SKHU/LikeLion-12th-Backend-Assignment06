package org.likelion.likelionassignmentcrud.food.api;

import org.likelion.likelionassignmentcrud.food.api.dto.request.FoodSaveReqDto;
import org.likelion.likelionassignmentcrud.food.api.dto.request.FoodUpdateReqDto;
import org.likelion.likelionassignmentcrud.food.api.dto.response.FoodInfoResDto;
import org.likelion.likelionassignmentcrud.food.api.dto.response.FoodListResDto;
import org.likelion.likelionassignmentcrud.food.application.FoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/food")
public class FoodController {
    private final FoodService foodService;

    public FoodController(FoodService foodService) {

        this.foodService = foodService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> foodSave(@RequestBody FoodSaveReqDto foodSaveReqDto) {
        foodService.foodSave(foodSaveReqDto);

        return new ResponseEntity<>("음식 저장!", HttpStatus.CREATED);
    }

    @GetMapping("/foods")
    public ResponseEntity<FoodListResDto> foodFindAll() {
        FoodListResDto foodListResDto = foodService.foodFindAll();
        return new ResponseEntity<>(foodListResDto, HttpStatus.OK);
    }

    @GetMapping("/{foodId}")
    public ResponseEntity<FoodInfoResDto> foodFindOne(@PathVariable("foodId") Long foodId) {
        FoodInfoResDto foodInfoResDto = foodService.foodFindOne(foodId);
        return new ResponseEntity<>(foodInfoResDto, HttpStatus.OK);
    }

    @PatchMapping("/{foodId}")
    public ResponseEntity<String> foodUpdate(@PathVariable("foodId") Long foodId,
                                             @RequestBody FoodUpdateReqDto foodUpdateReqDto) {
        foodService.foodUpdate(foodId, foodUpdateReqDto);
        return new ResponseEntity<>("음식 수정!", HttpStatus.OK);
    }
    @DeleteMapping("/{foodId}")
    public ResponseEntity<String> foodDelete(@PathVariable("foodId") Long foodId) {
        foodService.foodDelete(foodId);
        return new ResponseEntity<>("음식 삭제", HttpStatus.OK);
    }



}
