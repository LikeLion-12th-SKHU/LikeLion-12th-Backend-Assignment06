package org.likelion.likelionassignmentcrud.developer.application;

import org.likelion.likelionassignmentcrud.developer.api.dto.request.DeveloperSaveReqDto;
import org.likelion.likelionassignmentcrud.developer.api.dto.request.DeveloperUpdateReqDto;
import org.likelion.likelionassignmentcrud.developer.api.dto.response.DeveloperInfoResDto;
import org.likelion.likelionassignmentcrud.developer.api.dto.response.DeveloperListResDto;
import org.likelion.likelionassignmentcrud.developer.domain.Developer;
import org.likelion.likelionassignmentcrud.developer.domain.repository.DeveloperRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Pattern;

@Service
@Transactional(readOnly = true)
public class DeveloperService {
    private final DeveloperRepository developerRepository;

    // TODO : 정규 표현식 대신 @Valid 어노테이션을 사용하여 유효성 검사 구현
    private static final String NAME_PATTERN = "^[a-zA-Z0-9]*$";

    public DeveloperService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    // 개발사 저장
    @Transactional
    public void developerSave(DeveloperSaveReqDto developerSaveReqDto) {
        if (!Pattern.matches(NAME_PATTERN, developerSaveReqDto.name())) {
            throw new IllegalArgumentException("개발사 이름은 영문자와 숫자만 포함 가능합니다.");
        }

        Developer developer = Developer.builder()
                .name(developerSaveReqDto.name())
                .country(developerSaveReqDto.country())
                .establishedDate(developerSaveReqDto.establishedDate())
                .build();

        developerRepository.save(developer);
    }

    // 개발사 조회(모두)
    public DeveloperListResDto developerFindAll() {
        List<Developer> developers = developerRepository.findAll();

        List<DeveloperInfoResDto> developerInfoResDtoList = developers.stream()
                .map(DeveloperInfoResDto::from)
                .toList();

        return DeveloperListResDto.from(developerInfoResDtoList);
    }

    // 개발사 조회(하나)
    public DeveloperInfoResDto developerFindOne(Long developerId) {
        Developer developer = developerRepository.findById(developerId).orElseThrow(IllegalArgumentException::new);

        return DeveloperInfoResDto.from(developer);
    }

    // 개발사 업데이트(이름, 국가, 설립일)
    @Transactional
    public void developerUpdate(Long developerId, DeveloperUpdateReqDto developerUpdateReqDto) {
        Developer developer = developerRepository.findById(developerId).orElseThrow(IllegalArgumentException::new);

        developer.update(developerUpdateReqDto);
    }

    // 개발사 삭제
    @Transactional
    public void developerDelete(Long developerId) {
        Developer developer = developerRepository.findById(developerId).orElseThrow(IllegalArgumentException::new);

        developerRepository.delete(developer);
    }
}
