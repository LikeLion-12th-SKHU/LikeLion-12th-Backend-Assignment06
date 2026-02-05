// 테이블에 접근 하기 위한 클래스 = repository

package org.likelion.likelionassignmentcrud.product.domain.repository;

import org.likelion.likelionassignmentcrud.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// @Repository 필요 없음 : JpaRepository에 이미 있음 (빈 등록해줌)
public interface ProductRepository extends JpaRepository<Product, Long> {
}
