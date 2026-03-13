package com.rsm.purches;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/purchase")
public class PurchesController {

	@Autowired
	private PurchesService service;
	
	@GetMapping
	public List<PurchesTransaction> getAllPurchases()
	{
		return service.getAllPurchases();
	}
	
	@PostMapping
    public PurchesTransaction purchase(
            @RequestBody PurchesTransaction purchase) {
        return service.purchase(purchase);
    }
}
