package org.likelion.likelionassignmentcrud.game.api.dto.response;

import lombok.Builder;
import org.likelion.likelionassignmentcrud.game.domain.Game;
import org.likelion.likelionassignmentcrud.game.domain.Platform;

@Builder
public record GameInfoResDto(
        String name,
        String genre,
        Platform platform,
        String developer
) {
    public static GameInfoResDto from(Game game) {
        return GameInfoResDto.builder()
                .name(game.getName())
                .genre(game.getGenre())
                .platform(game.getPlatform())
                .developer(game.getDeveloper().getName())
                .build();
    }
}
