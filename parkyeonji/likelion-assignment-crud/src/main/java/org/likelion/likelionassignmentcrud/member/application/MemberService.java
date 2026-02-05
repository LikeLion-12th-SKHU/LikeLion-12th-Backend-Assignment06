package org.likelion.likelionassignmentcrud.member.application;

import org.likelion.likelionassignmentcrud.club.domain.Club;
import org.likelion.likelionassignmentcrud.club.domain.repository.ClubRepository;
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
    private final ClubRepository clubRepository;
    private final MemberRepository memberRepository;

    public MemberService(ClubRepository clubRepository, MemberRepository memberRepository) {
        this.clubRepository = clubRepository;
        this.memberRepository = memberRepository;
    }

    // Create
    @Transactional
    public void memberSave(MemberSaveReqDto memberSaveReqDto) {
        Club club = clubRepository.findById(memberSaveReqDto.clubId()).orElseThrow(IllegalArgumentException::new);

        Member member = Member.builder()
                .name(memberSaveReqDto.name())
                .age(memberSaveReqDto.age())
                .club(club)
                .build();

        memberRepository.save(member);
    }

    // Read
    public MemberListResDto memberFindClub(Long clubId) {
        Club club = clubRepository.findById(clubId).orElseThrow(IllegalArgumentException::new);

        List<Member> members = memberRepository.findByClub(club);
        List<MemberInfoResDto> memberInfoResDtoList = members.stream()
                .map(MemberInfoResDto::from)
                .toList();

        return MemberListResDto.from(memberInfoResDtoList);
    }

    // update
    @Transactional
    public void memberUpdate(Long memberId, MemberUpdateReqDto memberUpdateReqDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);

        member.update(memberUpdateReqDto);
    }
    // delete
    @Transactional
    public void memberDelete(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);

        memberRepository.delete(member);
    }
}
