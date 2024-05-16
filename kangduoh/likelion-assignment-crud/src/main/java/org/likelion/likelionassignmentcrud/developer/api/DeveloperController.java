package org.likelion.likelionassignmentcrud.developer.api;

import org.likelion.likelionassignmentcrud.developer.api.dto.request.DeveloperSaveReqDto;
import org.likelion.likelionassignmentcrud.developer.api.dto.request.DeveloperUpdateReqDto;
import org.likelion.likelionassignmentcrud.developer.api.dto.response.DeveloperInfoResDto;
import org.likelion.likelionassignmentcrud.developer.api.dto.response.DeveloperListResDto;
import org.likelion.likelionassignmentcrud.developer.application.DeveloperService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/developer")
public class DeveloperController {
    private final DeveloperService developerService;

    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> developerSave(@RequestBody DeveloperSaveReqDto developerSaveReqDto) {
        developerService.developerSave(developerSaveReqDto);

        return new ResponseEntity<>("개발사 저장!", HttpStatus.CREATED);
    }

    @GetMapping("/developers")
    public ResponseEntity<DeveloperListResDto> developerFindAll() {
        DeveloperListResDto developerListResDto = developerService.developerFindAll();

        return new ResponseEntity<>(developerListResDto, HttpStatus.OK);
    }

    @GetMapping("/{developerId}")
    public ResponseEntity<DeveloperInfoResDto> developerFindOne(@PathVariable("developerId") Long developerId) {
        DeveloperInfoResDto developerInfoResDto = developerService.developerFindOne(developerId);

        return new ResponseEntity<>(developerInfoResDto, HttpStatus.OK);
    }

    @PatchMapping("/{developerId}")
    public ResponseEntity<String> developerUpdate(@PathVariable("developerId") Long developerId, @RequestBody DeveloperUpdateReqDto developerUpdateReqDto) {
        developerService.developerUpdate(developerId, developerUpdateReqDto);

        return new ResponseEntity<>("개발사 수정", HttpStatus.OK);
    }

    @DeleteMapping("/{developerId}")
    public ResponseEntity<String> developerDelete(@PathVariable("developerId") Long developerId) {
        developerService.developerDelete(developerId);

        return new ResponseEntity<>("개발사 삭제", HttpStatus.OK);
    }
}
