package com.savethechildren.h2.serv;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.savethechildren.EncryptionTool;
import com.savethechildren.h2.model.Donation;
import com.savethechildren.h2.repo.DonationRepository;

@Service
public class DonationService {

	// @Autowired annotation provides the automatic dependency injection.
	@Autowired
	DonationRepository repository;

	// Save Donation entity in the h2 database.
	public void save(final Donation donation) {
		donation.setName(EncryptionTool.encrypt(donation.getName()));
		donation.setCreditCardNumber(EncryptionTool.encrypt(donation.getCreditCardNumber()));
		repository.save(donation);
	}

	// Get all Donations from the h2 database.
	public List<Donation> getAll() {
		final List<Donation> Donations = new ArrayList<>();
		repository.findAll().forEach(Donation -> Donations.add(Donation));

		for (Donation donation : Donations) {
			donation.setName(EncryptionTool.decrypt(donation.getName()));
			donation.setCreditCardNumber(EncryptionTool.decrypt(donation.getCreditCardNumber()));
		}

		return Donations;
	}
}