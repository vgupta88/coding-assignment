package com.assignment.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.model.Bill;
import com.assignment.model.Discount;
import com.assignment.model.User;

@RestController
@RequestMapping("/assess")
public class AssignmentController {

	@PostMapping("/discount")
	public ResponseEntity<Discount> assessDiscount(@RequestBody Bill bill) {
		
		User u = bill.getUser();
		double discount=0.00;
		
		switch(u.getType()) {
		case "EMPLOYEE":
			discount=30.00;
			break;
		case "AFFILIATE": 
			discount=10.00;
			break;
		case "CUSTOMER": 
			if(LocalDate.now().minusYears(2).isAfter(u.getStartDate()))
				discount=5.00;
			break;
		default:
			discount=0.00;
			break;
		}
		
		Discount disc = new Discount();
		disc.setId(UUID.randomUUID().toString());
		disc.setBillId(bill.getId());
		BigDecimal pctDiscountedNonGroceriesBill = bill.getNonGroceriesAmount().multiply(BigDecimal.valueOf(discount).divide(new BigDecimal(100)));
		BigDecimal pctDiscountedTotalBill = bill.getNonGroceriesAmount().subtract(pctDiscountedNonGroceriesBill).add(bill.getGroceriesAmount()); 
		BigDecimal discountedTotalBill = pctDiscountedTotalBill.subtract(new BigDecimal((pctDiscountedTotalBill.intValue()/100)*5));
		disc.setDiscountedBill(toPrecision(discountedTotalBill.setScale(4,RoundingMode.HALF_DOWN), 2));
		disc.setUserId(bill.getUser().getId());
		
		return new ResponseEntity<>(disc, HttpStatus.OK);
	}
	
	private static BigDecimal toPrecision(BigDecimal dec, int precision) {
	    String plain = dec.movePointRight(precision).toPlainString();
	    return new BigDecimal(plain.substring(0, plain.indexOf('.'))).movePointLeft(precision);
	}
}


