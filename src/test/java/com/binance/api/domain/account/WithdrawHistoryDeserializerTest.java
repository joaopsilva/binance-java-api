package com.binance.api.domain.account;

import com.binance.api.client.domain.account.Withdraw;
import com.binance.api.client.domain.account.WithdrawHistory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

/**
 * Test deserialization of a withdraw/deposit history.
 */
public class WithdrawHistoryDeserializerTest {

  @Test
  public void testWithdrawHistoryDeserialziation() {
    String withdrawHistoryJson = "{\"withdrawList\":\n" +
        "[{\"amount\":0.1,\"address\":\"0x456\",\"successTime\":\"2017-10-13 21:20:09\",\n" +
        "\"txId\":\"0x123\",\"id\":\"1\",\"asset\":\"ETH\",\"applyTime\":\"2017-10-13 20:59:38\",\"userId\":\"1\",\"status\":6}],\n" +
        "\"success\":true}";
    ObjectMapper mapper = new ObjectMapper();
    try {
      WithdrawHistory withdrawHistory = mapper.readValue(withdrawHistoryJson, WithdrawHistory.class);
      assertTrue(withdrawHistory.isSuccess());
      List<Withdraw> withdrawList = withdrawHistory.getWithdrawList();
      assertEquals(withdrawHistory.getWithdrawList().size(), 1);
      Withdraw withdraw = withdrawList.get(0);
      assertEquals(withdraw.getAmount(), "0.1");
      assertEquals(withdraw.getAddress(), "0x456");
      assertEquals(withdraw.getAsset(), "ETH");
      assertEquals(withdraw.getApplyTime(), "2017-10-13 20:59:38");
      assertEquals(withdraw.getSuccessTime(), "2017-10-13 21:20:09");
      assertEquals(withdraw.getTxId(), "0x123");
      assertEquals(withdraw.getId(), "1");
    } catch (IOException e) {
      fail(e.getMessage());
    }
  }
}
