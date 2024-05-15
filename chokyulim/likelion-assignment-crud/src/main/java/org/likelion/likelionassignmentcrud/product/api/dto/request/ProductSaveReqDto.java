// record 써서 간결화

package org.likelion.likelionassignmentcrud.product.api.dto.request;

import org.likelion.likelionassignmentcrud.product.domain.Part;

public record ProductSaveReqDto( // 포장 - 여러 변수 한 개로 받으려고
     // 이름, 나이, 파트
    String name,
    int price,
    Part part
) {
}
