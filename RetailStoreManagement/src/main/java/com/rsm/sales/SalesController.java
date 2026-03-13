package com.rsm.sales;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sales")
public class SalesController {

	@Autowired
	private SalesService service;
	
	@GetMapping
	public List<SalesTransaction> getAllSales()
	{
		return service.getAllSales();
	}
	
	@PostMapping
    public SalesTransaction sell(
            @RequestBody SalesTransaction sale) {
        return service.sell(sale);
    }
}
