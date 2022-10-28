package com.edugobeti.cursomc.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edugobeti.cursomc.domain.Order1;
import com.edugobeti.cursomc.domain.OrderItem;
import com.edugobeti.cursomc.domain.TicketPayment;
import com.edugobeti.cursomc.domain.enuns.PaymentStatus;
import com.edugobeti.cursomc.repository.Order1Repository;
import com.edugobeti.cursomc.repository.OrderItemRepository;
import com.edugobeti.cursomc.repository.PaymentRepository;
import com.edugobeti.cursomc.service.exception.ObjectNotFoundException;

@Service
public class Order1Service {

	@Autowired
	private Order1Repository repo;
	
	@Autowired
	private ticketService ticketService;
	
	@Autowired
	private PaymentRepository pr;
	
	@Autowired
	private OrderItemRepository oir;
	
	@Autowired
	private ProductService ps;
	
	public Order1 find(Integer id) {
		Optional<Order1> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Order1.class.getName()));
	}
	
	@Transactional
	public Order1 insert(Order1 obj) {
		obj.setId(null);
		obj.setInstant(new Date());
		obj.getPayment().setStatus(PaymentStatus.PENDENTE);
		obj.getPayment().setOrder(obj);
		if (obj.getPayment() instanceof TicketPayment) {
			TicketPayment tp = (TicketPayment) obj.getPayment();
			ticketService.fillInTicket(tp, obj.getInstant());
		}
		obj = repo.save(obj);
		pr.save(obj.getPayment());
		for (OrderItem oi : obj.getItens()) {
			oi.setOff(0.0);
			oi.setPrice(ps.find(oi.getProduct().getId()).getPrice());
			oi.setOrder(obj);
		}
		oir.saveAll(obj.getItens());
		return obj;
	}
}
