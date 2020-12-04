package com.binance.api.client.impl;

import static com.binance.api.client.impl.BinanceApiServiceGenerator.createService;
import static com.binance.api.client.impl.BinanceApiServiceGenerator.executeSync;

import java.util.List;

import com.binance.api.client.BinanceApiSwapRestClient;
import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.SwapRemoveType;
import com.binance.api.client.domain.account.Liquidity;
import com.binance.api.client.domain.account.LiquidityOperationRecord;
import com.binance.api.client.domain.account.Pool;
import com.binance.api.client.domain.account.SwapHistory;
import com.binance.api.client.domain.account.SwapQuote;
import com.binance.api.client.domain.account.SwapRecord;

/**
 * Implementation of Binance's SWAP REST API using Retrofit method calls.
 */
public class BinanceApiSwapRestClientImpl implements BinanceApiSwapRestClient {

    private final BinanceApiService binanceApiService;

    public BinanceApiSwapRestClientImpl(String apiKey, String secret) {
        binanceApiService = createService(BinanceApiService.class, apiKey, secret);
    }

    @Override
    public List<Pool> listAllSwapPools() {
        return executeSync(binanceApiService.listAllSwapPools());
    }

    @Override
    public Liquidity getPoolLiquidityInfo(String poolId) {
        long timestamp = System.currentTimeMillis();
        List<Liquidity> liquidities = executeSync(binanceApiService.getPoolLiquidityInfo(poolId,
            Long.valueOf(BinanceApiConstants.DEFAULT_RECEIVING_WINDOW), Long.valueOf(timestamp)));
        if (liquidities != null && !liquidities.isEmpty()) {
            return liquidities.get(0);
        }
        return null;
    }

    @Override
    public LiquidityOperationRecord addLiquidity(String poolId, String asset, String quantity) {
        long timestamp = System.currentTimeMillis();
        return executeSync(binanceApiService.addLiquidity(poolId,
                asset,
                quantity,
            Long.valueOf(BinanceApiConstants.DEFAULT_RECEIVING_WINDOW), Long.valueOf(timestamp)));
    }

    @Override
    public LiquidityOperationRecord removeLiquidity(String poolId, SwapRemoveType type, List<String> asset, String shareAmount) {
        long timestamp = System.currentTimeMillis();
        return executeSync(binanceApiService.removeLiquidity(poolId,
                type,
                asset,
                shareAmount,
            Long.valueOf(BinanceApiConstants.DEFAULT_RECEIVING_WINDOW), Long.valueOf(timestamp)));
    }

    @Override
    public List<LiquidityOperationRecord> getPoolLiquidityOperationRecords(String poolId, Integer limit) {
        long timestamp = System.currentTimeMillis();
        return executeSync(binanceApiService.getPoolLiquidityOperationRecords(
                poolId,
                limit,
            Long.valueOf(BinanceApiConstants.DEFAULT_RECEIVING_WINDOW), Long.valueOf(timestamp)));

    }

    @Override
    public LiquidityOperationRecord getLiquidityOperationRecord(String operationId) {
        long timestamp = System.currentTimeMillis();
        List<LiquidityOperationRecord> records = executeSync(binanceApiService.getLiquidityOperationRecord(
                operationId,
            Long.valueOf(BinanceApiConstants.DEFAULT_RECEIVING_WINDOW), Long.valueOf(timestamp)));
        if (records != null && !records.isEmpty()) {
            return records.get(0);
        }
        return null;
    }

    @Override
    public SwapQuote requestQuote(String quoteAsset,
                                  String baseAsset,
                                  String quoteQty) {
        long timestamp = System.currentTimeMillis();
        return executeSync(binanceApiService.requestQuote(quoteAsset, baseAsset, quoteQty,
            Long.valueOf(BinanceApiConstants.DEFAULT_RECEIVING_WINDOW), Long.valueOf(timestamp)));
    }

    @Override
    public SwapRecord swap(String quoteAsset, String baseAsset, String quoteQty) {
        long timestamp = System.currentTimeMillis();
        return executeSync(binanceApiService.swap(quoteAsset, baseAsset, quoteQty,
            Long.valueOf(BinanceApiConstants.DEFAULT_RECEIVING_WINDOW), Long.valueOf(timestamp)));
    }

    @Override
    public SwapHistory getSwapHistory(String swapId) {
        long timestamp = System.currentTimeMillis();
        List<SwapHistory> history = executeSync(binanceApiService.getSwapHistory(swapId,
            Long.valueOf(BinanceApiConstants.DEFAULT_RECEIVING_WINDOW), Long.valueOf(timestamp)));
        if (history != null && !history.isEmpty()) {
            return history.get(0);
        }
        return null;
    }
}