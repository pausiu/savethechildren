package com.savethechildren;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.savethechildren.h2.ctrl.DonationController;
import com.savethechildren.h2.model.Donation;
import com.savethechildren.h2.serv.DonationService;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class DonationApplicationTests {

	@InjectMocks
	DonationController donationController;

	@Mock
	DonationService donationService;

	@BeforeAll
	static void contextLoads() {
		System.out.println("test case start");
	}

	@Test
	public void testEncryption() {
		System.out.println("test encryption tool");
		String originalString = "howtodoinjava.com";
		String encryptedString = EncryptionTool.encrypt(originalString);
		String decryptedString = EncryptionTool.decrypt(encryptedString);
		assertEquals(originalString, decryptedString);
	}

	@Test
	public void testSaveDonation() {
		System.out.println("test save donation");
		Donation mockDonation = new Donation(1000, "Tom", "4659433483663190", 100, "98765432", "tpauxx@gmail.com");
		ResponseEntity<String> responseEntity = donationController.save(mockDonation);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void testMailicousCode() {
		System.out.println("test malicious on input");
		String maliciousCode = "<!DOCTYPE html><html><body><h1>Demo: alert()</h1><script>alert(\"your PC is hacked\");</script></body></html>;";
		Donation mockDonation = new Donation(1000, maliciousCode, "4659433483663190", 100, "98765432",
				"tpauxx@gmail.com");
		ResponseEntity<String> responseEntity = donationController.save(mockDonation);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
	}

	@Test
	public void testAmountInput() {
		System.out.println("test input amount with decimal places");
		Donation mockDonation = new Donation(1000, "Tom", "4659433483663190", 100.00001, "98765432",
				"tpauxx@gmail.com");
		ResponseEntity<String> responseEntity = donationController.save(mockDonation);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void testInvalidCreditCardNumber() {
		System.out.println("test invalid credit card number");
		Donation mockDonation = new Donation(1000, "Tom", "9999888877776666", 100, "98765432", "tpauxx@gmail.com");
		ResponseEntity<String> responseEntity = donationController.save(mockDonation);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
	}

	@Test
	public void testInvalidEmailAddress() {
		System.out.println("test invalid email address");
		Donation mockDonation = new Donation(1000, "Tom", "4659433483663190", 100, "98765432", "alice.example.com");
		ResponseEntity<String> responseEntity = donationController.save(mockDonation);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
	}
	
	/*
	 * @Test public void testGetDonationList() { Donation mockDonation = new
	 * Donation(1000, "Tom", "1111222233334444", 100, "98765432",
	 * "tpauxx@gmail.com"); ResponseEntity<String> responseEntity =
	 * donationController.save(mockDonation);
	 * 
	 * System.out.println("test get donation list"); List<Donation> donationList =
	 * donationController.getAll(); System.out.println(donationList);
	 * //assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200); }
	 */
}
