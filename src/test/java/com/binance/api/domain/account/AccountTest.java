package com.binance.api.domain.account;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.binance.api.client.domain.account.Account;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Samarone Lopes <samarone@gmail.com>
 *
 */
public class AccountTest {

	/**
	 * API v3 return additional field updateTime
	 */
	@Test
	public void testUnmarshallingAccountObjectFromJson() throws JsonParseException, JsonMappingException, IOException {
		final String strAccount = 	"{"+
				"		\"updateTime\":\"1519059417212\","+
				"		\"makerCommission\":10,"+				
				"		\"takerCommission\":10,"+
				"		\"buyerCommission\":0,"+
				"		\"sellerCommission\":0,"+
				"		\"canTrade\":true,"+
				"		\"canWithdraw\":true,"+
				"		\"canDeposit\":true,"+
				"		\"balances\":["+
				"		    {"+
				"		        \"asset\":\"BTC\",\"free\":\"1.00000000\",\"locked\":\"0.00000000\""+
				"		    }"+
				"		]"+
				"		}"+
				"}";
		
		ObjectMapper mapper = new ObjectMapper();
		final Account obj = mapper.readValue(strAccount, Account.class);
		
		assertNotNull(obj);
		
		assertTrue(obj.getBalances().size() == 1);
		
		assertTrue(obj.getUpdateTime() == (long) 1519059417212L);
		
	}
}
	
