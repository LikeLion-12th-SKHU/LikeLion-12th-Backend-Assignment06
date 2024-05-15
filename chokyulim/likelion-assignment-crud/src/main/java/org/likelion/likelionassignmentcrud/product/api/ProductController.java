// 서비스 요청 받아서

package org.likelion.likelionassignmentcrud.product.api;

import org.likelion.likelionassignmentcrud.product.api.dto.request.ProductUpdateReqDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final org.likelion.likelionassignmentcrud.product.application.ProductService productService;

    public ProductController(org.likelion.likelionassignmentcrud.product.application.ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> productSave(@RequestBody org.likelion.likelionassignmentcrud.product.api.dto.request.ProductSaveReqDto productSaveReqDto) {
        productService.productSave(productSaveReqDto);
        return new ResponseEntity<>("상품 저장!", HttpStatus.CREATED);
    }

    @GetMapping("/products")
    public ResponseEntity<org.likelion.likelionassignmentcrud.product.api.dto.response.ProductListResDto> productFindAll() {
        org.likelion.likelionassignmentcrud.product.api.dto.response.ProductListResDto productListResDto = productService.productFindAll();

        return new ResponseEntity<>(productListResDto, HttpStatus.OK);
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<String> productUpdate(@PathVariable Long productId,
                                                @RequestBody ProductUpdateReqDto productupdateReqDto) {
        productService.productUpdate(productId, productupdateReqDto);
        return new ResponseEntity<>("상품 수정!", HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> productDelete(@PathVariable Long productId) {
        productService.productDelete(productId);
        return new ResponseEntity<>("상품 삭제!", HttpStatus.OK);
    }
}
