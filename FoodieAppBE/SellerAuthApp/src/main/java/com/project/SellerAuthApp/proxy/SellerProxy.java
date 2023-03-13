package com.project.SellerAuthApp.proxy;

import com.project.SellerAuthApp.domain.SellerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="sellerServiceApp",url = "localhost:888")
public interface SellerProxy {
    @PostMapping("/sellerService/addSeller")
    public ResponseEntity<?> sendDataToSellerServiceApp(@RequestBody SellerDTO sellerDTO);
}
