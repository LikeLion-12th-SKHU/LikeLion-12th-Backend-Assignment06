package org.likelion.likelioncrud2.student.api.dto.request;

import org.likelion.likelioncrud2.student.domain.Part;

public record StudentUpdateReqDto(String name, Part part) {
}
