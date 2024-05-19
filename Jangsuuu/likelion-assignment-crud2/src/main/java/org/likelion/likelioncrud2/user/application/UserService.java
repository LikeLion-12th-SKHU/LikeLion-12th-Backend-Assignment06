package org.likelion.likelioncrud2.user.application;

import org.likelion.likelioncrud2.user.api.dto.request.UserSaveReqDto;
import org.likelion.likelioncrud2.user.api.dto.request.UserUpdateReqDto;
import org.likelion.likelioncrud2.user.api.dto.response.UserInfoResDto;
import org.likelion.likelioncrud2.user.api.dto.response.UserListResDto;
import org.likelion.likelioncrud2.user.domain.User;
import org.likelion.likelioncrud2.user.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// 캡슐화된 상태 없이 모델에서 단독으로 독립된 인터페이스로 제공되는 작업(?)
@Service
// 실행 중 예외가 발생하면 해당 메서드를 실행하면서 수행한 쿼리들을 모두 롤백함.(읽기 전용)
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 생성
    @Transactional
    public void userSave(UserSaveReqDto userSaveReqDto) {
        User user = User.builder()
                .name(userSaveReqDto.name())
                .part(userSaveReqDto.part()).build();

        userRepository.save(user);
    }

    // 한명 불러오기
    public UserInfoResDto userFindOne(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 사용자가 없습니다. ID : " + userId));

        return UserInfoResDto.from(user);
    }

    // 전부 불러오기
    public UserListResDto userFindAll() {
        List<User> users = userRepository.findAll();
        List<UserInfoResDto> userInfoResDtoList = users.stream()
                .map(UserInfoResDto::from).toList();

        return UserListResDto.from(userInfoResDtoList);
    }

    // 업데이트
    @Transactional
    public void userUpdate(Long userId, UserUpdateReqDto userUpdateReqDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 사용자가 없습니다. ID : " + userId));

        user.updateUserInfo(userUpdateReqDto);
    }

    // 삭제
    @Transactional
    public void userDelete(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 사용자가 없습니다. ID : " + userId));

        userRepository.delete(user);
    }
}
