package org.likelion.likelionassignmentcrud.users.api.dto.request;

import org.likelion.likelionassignmentcrud.users.domain.PayOption;

public record UsersSaveReqDto(
        String name,
        String phoneNumber,
        PayOption option
) {
}
