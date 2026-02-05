package org.likelion.likelionassignmentcrud.game.api.dto.request;

import org.likelion.likelionassignmentcrud.game.domain.Platform;

public record GameUpdateReqDto(
        String name,
        String genre,
        Platform platform
) {
}
