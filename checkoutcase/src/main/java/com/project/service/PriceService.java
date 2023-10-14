package com.project.service;

import java.util.HashMap;
import java.util.Map;

public class PriceService {

    private Map<String, Map<Integer, Double>> pricingTable = new HashMap<>();

    public PriceService() {

        Map<Integer, Double> sellerAItemPrices = new HashMap<>();
        sellerAItemPrices.put(1, 50.0);
        sellerAItemPrices.put(2, 75.0);
        sellerAItemPrices.put(3, 510000.0);

        Map<Integer, Double> sellerBItemPrices = new HashMap<>();
        sellerBItemPrices.put(1, 60.0);
        sellerBItemPrices.put(2, 80.0);
        sellerBItemPrices.put(3, 250000.0);

        pricingTable.put("1", sellerAItemPrices);
        pricingTable.put("2", sellerBItemPrices);
    }

    public double getPriceBySellerAndItemId(String sellerId, int itemId) {
        if (pricingTable.containsKey(sellerId)) {
            Map<Integer, Double> sellerPrices = pricingTable.get(sellerId);
            if (sellerPrices.containsKey(itemId)) {
                return sellerPrices.get(itemId);
            }
        }

        throw new IllegalArgumentException("Invalid sellerId or itemId");
    }
}
