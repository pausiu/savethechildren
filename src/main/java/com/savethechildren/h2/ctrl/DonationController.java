package com.savethechildren.h2.ctrl;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savethechildren.CreditCardValidator;
import com.savethechildren.EmailValidator;
import com.savethechildren.InputChecker;
import com.savethechildren.h2.model.Donation;
import com.savethechildren.h2.serv.DonationService;

@RestController
public class DonationController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	// @Autowired annotation provides the automatic dependency injection.
	@Autowired
	DonationService service;

	// Save Donation entity in the h2 database.
	// @PostMapping annotation handles the http post request matched with the given
	// uri.
	// @RequestBody annotation binds the http request body to the domain object.
	// @Valid annotation validates a model after binding the user input to it.
	@PostMapping(value = "/donation/save")
	public ResponseEntity<String> save(final @RequestBody @Valid Donation donation) {
		log.info("Saving donation details in the database.");

		// malicious code check
		if (!InputChecker.validate(donation.getName()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid value on the name field");
		if (!InputChecker.validate(donation.getCreditCardNumber()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid value on credit card information");
		if (!InputChecker.validate(donation.getPhoneNo()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid value on phone no");
		if (!EmailValidator.validate(donation.getEmailAddress()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email address ");
		// number format check
		if (!InputChecker.validateNo(donation.getPhoneNo()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid number format on phone no information");
		if (!InputChecker.validateNo(donation.getCreditCardNumber()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid number format on credit card information");
		// credit card number check
		if (!CreditCardValidator.validitychk(donation.getCreditCardNumber()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid credit card no");
		// email address check
		if (!EmailValidator.validate(donation.getEmailAddress()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email address");
		service.save(donation);
		return ResponseEntity.status(HttpStatus.OK).body("Donation details saved in the database.");
	}

	// Get all Donations from the h2 database.
	// @GetMapping annotation handles the http get request matched with the given
	// uri.
	@GetMapping(value = "/donation/getall", produces = "application/vnd.jcg.api.v1+json")
	public List<Donation> getAll() {
		log.info("Getting Donation details from the database.");
		return service.getAll();
	}

	@RequestMapping("/hello")
	public String hello() {
		return "Hello World RESTful with Spring Boot";
	}
}