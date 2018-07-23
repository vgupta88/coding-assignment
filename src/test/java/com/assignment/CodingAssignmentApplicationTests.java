package com.assignment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.assignment.model.Bill;
import com.assignment.model.Discount;
import com.assignment.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)      
public class CodingAssignmentApplicationTests {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void testAssessEmployee1() {
		Bill bill=generateTestBill("EMPLOYEE", LocalDate.of(2015, 12, 1), new BigDecimal(100), new BigDecimal(100));
		ResponseEntity<Discount> responseEntity = restTemplate
				.postForEntity(createURLWithPort("/assess/discount"),bill, Discount.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertTrue(responseEntity.getBody().getDiscountedBill().compareTo(new BigDecimal(165)) == 0);
	}
	
	@Test
	public void testAssessEmployee2() {
		Bill bill=generateTestBill("EMPLOYEE", LocalDate.of(2015, 12, 1), new BigDecimal(200), new BigDecimal(100));
		ResponseEntity<Discount> responseEntity = restTemplate
				.postForEntity(createURLWithPort("/assess/discount"),bill, Discount.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertTrue(responseEntity.getBody().getDiscountedBill().compareTo(new BigDecimal(260)) == 0);
	}
	
	@Test
	public void testAssessAffiliate1() {
		Bill bill=generateTestBill("AFFILIATE", LocalDate.of(2015, 12, 1), new BigDecimal(150), new BigDecimal(150));
		ResponseEntity<Discount> responseEntity = restTemplate
				.postForEntity(createURLWithPort("/assess/discount"),bill, Discount.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertTrue(responseEntity.getBody().getDiscountedBill().compareTo(new BigDecimal(275)) == 0);
	}
	
	@Test
	public void testAssessAffiliate2() {
		Bill bill=generateTestBill("AFFILIATE", LocalDate.of(2015, 12, 1), new BigDecimal(150.90), new BigDecimal(130.50));
		ResponseEntity<Discount> responseEntity = restTemplate
				.postForEntity(createURLWithPort("/assess/discount"),bill, Discount.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertTrue(responseEntity.getBody().getDiscountedBill().compareTo(new BigDecimal("258.35")) == 0);
	}
	
	@Test
	public void testAssessCustomer1() {
		Bill bill=generateTestBill("CUSTOMER", LocalDate.of(2015, 12, 1), new BigDecimal(250.50), new BigDecimal(230.50));
		ResponseEntity<Discount> responseEntity = restTemplate
				.postForEntity(createURLWithPort("/assess/discount"),bill, Discount.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertTrue(responseEntity.getBody().getDiscountedBill().compareTo(new BigDecimal("449.47")) == 0);
	}
	
	@Test
	public void testAssessCustomer2() {
		Bill bill=generateTestBill("CUSTOMER", LocalDate.of(2017, 12, 1), new BigDecimal(250.50), new BigDecimal(230.50));
		ResponseEntity<Discount> responseEntity = restTemplate
				.postForEntity(createURLWithPort("/assess/discount"),bill, Discount.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertTrue(responseEntity.getBody().getDiscountedBill().compareTo(new BigDecimal(461)) == 0);
	}
	

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

	private Bill generateTestBill(String custType, LocalDate startDate, BigDecimal groceriesBill, BigDecimal nonGroceriesBill) {
		Bill bill=new Bill();
		User user=new User();
		user.setId(UUID.randomUUID().toString());
		user.setName("Test User");
		user.setStartDate(startDate);
		user.setType(custType);
		user.setActive(true);
		bill.setId(UUID.randomUUID().toString());
		bill.setGroceriesAmount(groceriesBill);
		bill.setNonGroceriesAmount(nonGroceriesBill);
		bill.setTotalAmount(bill.getGroceriesAmount().add(bill.getNonGroceriesAmount()));
		bill.setUser(user);
		return bill;

	}


}
