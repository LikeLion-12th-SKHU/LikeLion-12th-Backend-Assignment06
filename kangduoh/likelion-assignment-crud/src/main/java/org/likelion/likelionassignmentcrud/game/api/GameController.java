package org.likelion.likelionassignmentcrud.game.api;

import org.likelion.likelionassignmentcrud.game.api.dto.request.GameSaveReqDto;
import org.likelion.likelionassignmentcrud.game.api.dto.request.GameUpdateReqDto;
import org.likelion.likelionassignmentcrud.game.api.dto.response.GameListResDto;
import org.likelion.likelionassignmentcrud.game.application.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> gameSave(@RequestBody GameSaveReqDto gameSaveReqDto) {
        gameService.gameSave(gameSaveReqDto);

        return new ResponseEntity<>("게임 저장!", HttpStatus.CREATED);
    }

    // 개발사에 따른 게임 리스트 불러오기
    @GetMapping("/developer/{developerName}")
    public ResponseEntity<GameListResDto> myGameFindAllByDeveloper(@PathVariable("developerName") String developerName) {
        GameListResDto gameListResDto = gameService.gameFindDeveloperByName(developerName);

        return new ResponseEntity<>(gameListResDto, HttpStatus.OK);
    }

    @PatchMapping("/{gameId}")
    public ResponseEntity<String> gameUpdate(@PathVariable("gameId") Long gameId, @RequestBody GameUpdateReqDto gameUpdateReqDto) {
        gameService.gameUpdate(gameId, gameUpdateReqDto);

        return new ResponseEntity<>("게임 수정", HttpStatus.OK);
    }

    @DeleteMapping("/{gameId}")
    public ResponseEntity<String> gameDelete(@PathVariable("gameId") Long gameId) {
        gameService.gameDelete(gameId);

        return new ResponseEntity<>("게임 삭제", HttpStatus.OK);
    }
}
