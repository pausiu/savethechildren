package com.savethechildren.h2.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.savethechildren.h2.model.Donation;

@Repository
public interface DonationRepository extends CrudRepository<Donation, Integer> {

}