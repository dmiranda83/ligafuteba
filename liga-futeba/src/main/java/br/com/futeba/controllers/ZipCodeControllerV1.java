package br.com.futeba.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.futeba.bean.ZipCodeResponse;
import br.com.futeba.services.impl.AddressServiceImpl;

@RestController
@RequestMapping("/api/v1/zipCode")
public class ZipCodeControllerV1 {

	@Autowired
	private AddressServiceImpl addressService;

	@GetMapping("/{zipCode}")
	public ZipCodeResponse listAddressByZipCode(@PathVariable String zipCode,
			Model model) {
		return addressService.getAddressByZipCode(zipCode);
	}

}
