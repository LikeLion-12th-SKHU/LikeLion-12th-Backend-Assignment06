package org.likelion.likelionassignmentcrud.game.api.dto.request;

import org.likelion.likelionassignmentcrud.game.domain.Platform;

public record GameSaveReqDto(
        // 개발사 아이디, 이름, 장르, 플랫폼
        Long developerId,
        String name,
        String genre,
        Platform platform
) {
}
