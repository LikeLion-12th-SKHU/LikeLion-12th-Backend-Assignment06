package org.likelion.likelionassignmentcrud.member.application;

import org.likelion.likelionassignmentcrud.member.api.dto.request.MemberSaveReqDto;
import org.likelion.likelionassignmentcrud.member.api.dto.request.MemberUpdateReqDto;
import org.likelion.likelionassignmentcrud.member.api.dto.response.MemberInfoResDto;
import org.likelion.likelionassignmentcrud.member.api.dto.response.MemberListResDto;
import org.likelion.likelionassignmentcrud.member.domain.Member;
import org.likelion.likelionassignmentcrud.member.domain.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //create
    @Transactional
    public void memberSave(MemberSaveReqDto memberSaveReqDto) {
        Member member = Member.builder()
                .name(memberSaveReqDto.name())
                .bizNum(memberSaveReqDto.bizNum())
                .companyName(memberSaveReqDto.companyName())
                .build();

        memberRepository.save(member);
    }

    //Read All
    public MemberListResDto memberFindAll() {
        List<Member> members = memberRepository.findAll();

        List<MemberInfoResDto> memberInfoResDtoList = members.stream()
                .map(MemberInfoResDto::from)
                .toList();

        return MemberListResDto.from(memberInfoResDtoList);
    }

    //Read one
    public MemberInfoResDto memberFindOne(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);

        return MemberInfoResDto.from(member);
    }

    //Update
    @Transactional
    public void memberUpdate(Long memberId, MemberUpdateReqDto memberUpdateReqDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);

        member.update(memberUpdateReqDto);
    }

    //Delete
    @Transactional
    public void memberDelete(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);

        memberRepository.delete(member);
    }
}
