package com.dervan.module.rest;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dervan.module.payment.TeamPayment;
import com.dervan.module.util.dao.HibernateUtil;

@Path("/acceptTeamPay")
public class AcceptPaymentTeamController {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public Map<String, Object> getSuccessForPay(Map<String, String> inputData){
		Session session  = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		int teamId = null != inputData.get("captainID") ? TeamPayment.getTeamID(Integer.parseInt(inputData.get("captainID")), session, tx) : 0;
		int captainId = null != inputData.get("captainID") ? Integer.parseInt(inputData.get("captainID")) : 0;
		int amount = null != inputData.get("amt") ? Integer.parseInt(inputData.get("amt")) : 0;
		String user = null != inputData.get("user") ? inputData.get("user") : "";
		Map<String, Object> mapData = TeamPayment.getPayment(teamId, captainId, amount, user );		
		return mapData;
	}

	
}
