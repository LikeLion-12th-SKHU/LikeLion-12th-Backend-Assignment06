package org.likelion.likelionassignmentcrud.professor.application;

import org.likelion.likelionassignmentcrud.professor.api.dto.request.ProfessorSaveReqDto;
import org.likelion.likelionassignmentcrud.professor.api.dto.request.ProfessorUpdateReqDto;
import org.likelion.likelionassignmentcrud.professor.api.dto.response.ProfessorInfoResDto;
import org.likelion.likelionassignmentcrud.professor.api.dto.response.ProfessorListResDto;
import org.likelion.likelionassignmentcrud.professor.domain.Professor;
import org.likelion.likelionassignmentcrud.professor.domain.repository.ProfessorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service    //controller와 repository를 잇는 역할, 구체적인 작업 수행
@Transactional(readOnly = true)     //예외가 발생하면 해당 메서드를 실행하면서 수행한 쿼리들을 모두 롤백 //트랜잭션을 읽기전용으로 설정 insert, update, delete 실행할 때 예외 발생
public class ProfessorService {
    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    //create
    @Transactional
    public void professorSave(ProfessorSaveReqDto professorSaveReqDto) {     //교수 정보 저장 메서드 professorsave
        Professor professor = Professor.builder()       //이름, 나이, 학과 정보를 받아 professor 객체 생성
                .name(professorSaveReqDto.name())
                .age(professorSaveReqDto.age())
                .department(professorSaveReqDto.department())
                .build();

        professorRepository.save(professor); //생성된 professor 객체를 저장
    }

    //모든 교수 조회
    public ProfessorListResDto professorFindAll() {
        List<Professor> professors = professorRepository.findAll(); //findall 해서 리스트(professors)에 저장

        List<ProfessorInfoResDto> professorInfoResDtoList = professors.stream()  //교수 정보를 professorInfoResDto로 변환하고 리스트에 저장
                .map(ProfessorInfoResDto::from)    //메소드 참조 형식, professor를 professorInfoResDto로 매핑
                .toList();  //리스트로 변환

        return ProfessorListResDto.from(professorInfoResDtoList);
        //변환된 리스트 professorInfoResDtoList로 professorListResDto 객체 생성하여 리턴
    }

    //Read One
    public ProfessorInfoResDto professorFindOne(Long professorId) {
        Professor professor = professorRepository.findById(professorId).orElseThrow(IllegalArgumentException::new);

        return ProfessorInfoResDto.from(professor);
    }

    //교수 이름, 학과 update
    @Transactional
    public void professorUpdate(Long professorId, ProfessorUpdateReqDto professorUpdateReqDto) {
        Professor professor = professorRepository.findById(professorId).orElseThrow(IllegalArgumentException::new);

        professor.update(professorUpdateReqDto);
    }

    //교수 삭제
    @Transactional
    public void professorDelete(Long professorId) {
        Professor professor = professorRepository.findById(professorId).orElseThrow(IllegalArgumentException::new);

        professorRepository.delete(professor);
    }
}
