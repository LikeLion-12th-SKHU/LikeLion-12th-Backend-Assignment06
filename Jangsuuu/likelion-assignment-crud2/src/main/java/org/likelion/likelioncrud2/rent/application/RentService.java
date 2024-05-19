package org.likelion.likelioncrud2.rent.application;


import org.likelion.likelioncrud2.rent.api.dto.request.RentSaveReqDto;
import org.likelion.likelioncrud2.rent.api.dto.request.RentUpdateReqDto;
import org.likelion.likelioncrud2.rent.api.dto.response.RentInfoResDto;
import org.likelion.likelioncrud2.rent.api.dto.response.RentListResDto;
import org.likelion.likelioncrud2.rent.domain.Rent;
import org.likelion.likelioncrud2.rent.domain.repository.RentRepository;
import org.likelion.likelioncrud2.user.domain.User;
import org.likelion.likelioncrud2.user.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// 캡슐화된 상태 없이 모델에서 단독으로 독립된 인터페이스로 제공되는 작업(?)
@Service
// 실행 중 예외가 발생하면 해당 메서드를 실행하면서 수행한 쿼리들을 모두 롤백함.(읽기 전용)
@Transactional(readOnly = true)
public class RentService {
    private final UserRepository userRepository;
    private final RentRepository rentRepository;

    public RentService(UserRepository userRepository,RentRepository rentRepository) {
        this.userRepository = userRepository;
        this.rentRepository = rentRepository;
    }

    // 생성
    @Transactional
    public void rentSave(RentSaveReqDto rantSaveReqDto) {
        User user = userRepository.findById(rantSaveReqDto.userId())
                .orElseThrow(() -> new IllegalArgumentException("해당하는 학생이 없습니다."));

        Rent rent = Rent.builder()
                .rentTime(rantSaveReqDto.rentTime())
                .returnTime(rantSaveReqDto.returnTime())
                .bookName(rantSaveReqDto.bookName())
                .user(user)
                .build();

        rentRepository.save(rent);
    }

    // 전부 불러오기
    @Transactional
    public RentListResDto rentFindAll(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 사용자가 없습니다."));

        List<Rent> rents = rentRepository.findByUser(user);
        List<RentInfoResDto> rentInfoResDtoList = rents.stream()
                .map(RentInfoResDto::from).toList();

        return RentListResDto.from(rentInfoResDtoList);
    }

    // 업데이트
    @Transactional
    public void rentUpdate(Long rentId, RentUpdateReqDto rentUpdateReqDto) {
        Rent rent = rentRepository.findById(rentId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 사용자가 없습니다."));

        rent.updateRentInfo(rentUpdateReqDto);
    }

    // 삭제
    @Transactional
    public void rentDelete(Long rentId) {
        Rent rent = rentRepository.findById(rentId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 사용자가 없습니다."));

        rentRepository.delete(rent);
    }

    // 전부 삭제
    @Transactional
    public void rentDeleteAll(Long rentId) {
        rentRepository.findById(rentId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 사용자가 없습니다."));

        rentRepository.deleteAll();
    }
}
