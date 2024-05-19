package org.likelion.likelioncrud2.user.api.dto;

import org.likelion.likelioncrud2.user.api.dto.request.UserSaveReqDto;
import org.likelion.likelioncrud2.user.api.dto.request.UserUpdateReqDto;
import org.likelion.likelioncrud2.user.api.dto.response.UserInfoResDto;
import org.likelion.likelioncrud2.user.api.dto.response.UserListResDto;
import org.likelion.likelioncrud2.user.application.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> userSave(@RequestBody UserSaveReqDto userSaveReqDto) {
        userService.userSave(userSaveReqDto);
        return new ResponseEntity<>("사용자 정보 저장(신규)!", HttpStatus.CREATED);
    }

    // 한명 불러오기
    @GetMapping("/{userId}")
    public ResponseEntity<UserInfoResDto> userFindOne(@PathVariable("userId") Long userId) {
        UserInfoResDto userInfoResDto = userService.userFindOne(userId);
        return new ResponseEntity<>(userInfoResDto, HttpStatus.OK);
    }

    // 전부 조회
    @GetMapping("/users")
    public ResponseEntity<UserListResDto> userFindAll() {
        UserListResDto userListResDto = userService.userFindAll();
        return new ResponseEntity<>(userListResDto, HttpStatus.OK);
    }

    // 수정하기
    @PatchMapping("/{userId}")
    public ResponseEntity<String> userUpdate(@PathVariable("userId")Long userId,
                                             @RequestBody UserUpdateReqDto userUpdateReqDto) {
        userService.userUpdate(userId, userUpdateReqDto);
        return new ResponseEntity<>("사용자 정보 수정!", HttpStatus.OK);
    }

    // 삭제하기
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> userDelete(@PathVariable("userId")Long userId) {
        userService.userDelete(userId);
        return new ResponseEntity<>("사용자 정보 삭제!", HttpStatus.OK);
    }
}
