package com.edugobeti.cursomc.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.edugobeti.cursomc.domain.TicketPayment;

@Service
public class ticketService {

	public void fillInTicket(TicketPayment tp, Date orderIntant) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(orderIntant);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		tp.setDueDate(cal.getTime());
	}
}
