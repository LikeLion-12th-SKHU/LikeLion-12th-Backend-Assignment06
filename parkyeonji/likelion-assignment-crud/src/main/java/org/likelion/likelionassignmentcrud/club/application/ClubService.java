package org.likelion.likelionassignmentcrud.club.application;

import org.likelion.likelionassignmentcrud.club.api.dto.request.ClubSaveReqDto;
import org.likelion.likelionassignmentcrud.club.api.dto.request.ClubUpdateReqDto;
import org.likelion.likelionassignmentcrud.club.api.dto.response.ClubInfoResDto;
import org.likelion.likelionassignmentcrud.club.api.dto.response.ClubListResDto;
import org.likelion.likelionassignmentcrud.club.domain.Club;
import org.likelion.likelionassignmentcrud.club.domain.repository.ClubRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ClubService {
    private final ClubRepository clubRepository;

    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    // Create
    @Transactional
    public void clubSave(ClubSaveReqDto clubSaveReqDto) {
        Club club = Club.builder()
                .name(clubSaveReqDto.name())
                .clubType(clubSaveReqDto.clubType())
                .part(clubSaveReqDto.part())
                .build();

        clubRepository.save(club);
    }

    // Read All
    public ClubListResDto clubFindAll() {
        List<Club> clubs = clubRepository.findAll();

        List<ClubInfoResDto> clubInfoResDtoList = clubs.stream()
                .map(ClubInfoResDto::from)
                .toList();

        return ClubListResDto.from(clubInfoResDtoList);
    }

    // Read One
    public ClubInfoResDto clubFindOne(Long clubId) {
        Club club = clubRepository.findById(clubId).orElseThrow(IllegalArgumentException::new);

        return ClubInfoResDto.from(club);
    }

    // Update
    @Transactional
    public void clubUpdate(Long clubId, ClubUpdateReqDto clubUpdateReqDto) {
        Club club = clubRepository.findById(clubId).orElseThrow(IllegalArgumentException::new);
        club.update(clubUpdateReqDto);
    }

    // Delete
    @Transactional
    public void clubDelete(Long clubId) {
        Club club = clubRepository.findById(clubId).orElseThrow(IllegalArgumentException::new);

        clubRepository.delete(club);
    }
}