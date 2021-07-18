package com.sam.coronavirusreporter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sam.coronavirusreporter.services.CoronaVirusDataService;

@Controller
public class HomeController {
	
	@Autowired
	CoronaVirusDataService coronaVirusDataService;
	
	@GetMapping("/Sambeet-Report")
	public String home(Model model) {
		
		model.addAttribute("locationStats", coronaVirusDataService.getAllstats());
		
		return "home";
	}

}
