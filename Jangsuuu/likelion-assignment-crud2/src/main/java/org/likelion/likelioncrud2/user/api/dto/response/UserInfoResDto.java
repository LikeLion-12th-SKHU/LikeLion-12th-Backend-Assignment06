package org.likelion.likelioncrud2.user.api.dto.response;

import lombok.Builder;
import org.likelion.likelioncrud2.user.domain.Part;
import org.likelion.likelioncrud2.user.domain.User;

@Builder
public record UserInfoResDto(String name, Part part) {
    public static UserInfoResDto from(User user) {
        return UserInfoResDto.builder()
                .name(user.getName())
                .part(user.getPart())
                .build();
    }
}
