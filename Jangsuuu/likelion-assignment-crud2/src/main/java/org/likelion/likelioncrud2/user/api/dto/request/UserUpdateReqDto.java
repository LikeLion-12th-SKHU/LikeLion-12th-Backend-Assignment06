package org.likelion.likelioncrud2.user.api.dto.request;

import org.likelion.likelioncrud2.user.domain.Part;

public record UserUpdateReqDto(String name, Part part) {
}
