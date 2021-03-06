package com.api.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.ecommerce.exception.ResourceNotFoundException;
import com.api.ecommerce.model.Tax;
import com.api.ecommerce.repository.TaxRepository;

@RestController
@RequestMapping("/tax")
public class TaxController {

	@Autowired
	TaxRepository taxRepository;

	/**
	 * Returns all taxes
	 *
	 * @return - List<Tax>
	 */
	@GetMapping("")
	public List<Tax> getAllTaxes() {
		List<Tax> list = taxRepository.findAll();
		if (list.isEmpty()) {
			throw new ResourceNotFoundException("USR_02", "The field example is empty.", "example", "500");
		}
		return list;
	}

	/**
	 * Returns tax by id
	 *
	 * @param taxId - Tax Id
	 * @return - Tax
	 */
	@GetMapping("/{tax_id}")
	public Tax getTaxById(@PathVariable(value = "tax_id", required = true) Long taxId) {
		return taxRepository.findById(taxId).orElseThrow(
				() -> new ResourceNotFoundException("USR_02", "The field example is empty.", "example", "500"));
	}

}
