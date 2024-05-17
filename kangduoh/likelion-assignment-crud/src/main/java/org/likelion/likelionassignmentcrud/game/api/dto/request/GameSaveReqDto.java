package org.likelion.likelionassignmentcrud.game.api.dto.request;

import org.likelion.likelionassignmentcrud.game.domain.Platform;

public record GameSaveReqDto(
        // 개발사명, 게임명, 장르, 플랫폼
        String developerName,
        String name,
        String genre,
        Platform platform
) {
}
