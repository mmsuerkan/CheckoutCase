package com.project.repository;

import java.util.HashMap;
import java.util.Map;

public class PriceRepository {

    private Map<String, Map<Integer, Double>> pricingTable = new HashMap<>();

    public PriceRepository() {
        Map<Integer, Double> sellerAItemPrices = new HashMap<>();
        sellerAItemPrices.put(1, 50.0);
        sellerAItemPrices.put(2, 75.0);
        sellerAItemPrices.put(3, 510000.0);
        sellerAItemPrices.put(4, 200.0);
        sellerAItemPrices.put(5, 75.0);
        sellerAItemPrices.put(6, 55.0);
        sellerAItemPrices.put(7, 15.0);
        sellerAItemPrices.put(8, 90.0);
        sellerAItemPrices.put(9, 3200.0);
        sellerAItemPrices.put(10, 6000.0);
        sellerAItemPrices.put(11, 2000.0);
        sellerAItemPrices.put(12, 7800.0);
        sellerAItemPrices.put(13, 6500.0);
        sellerAItemPrices.put(14, 750.0);
        sellerAItemPrices.put(15, 800.0);

        Map<Integer, Double> sellerBItemPrices = new HashMap<>();
        sellerBItemPrices.put(1, 60.0);
        sellerBItemPrices.put(2, 80.0);
        sellerBItemPrices.put(3, 250000.0);
        sellerAItemPrices.put(4, 10.0);
        sellerAItemPrices.put(5, 105.0);
        sellerAItemPrices.put(6, 33.0);
        sellerAItemPrices.put(7, 50.0);
        sellerAItemPrices.put(8, 25.0);
        sellerAItemPrices.put(9, 350.0);
        sellerAItemPrices.put(10, 600.0);
        sellerAItemPrices.put(11, 2000.0);
        sellerAItemPrices.put(12, 12000.0);
        sellerAItemPrices.put(13, 40000.0);
        sellerAItemPrices.put(14, 4200.0);
        sellerAItemPrices.put(15, 800.0);

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
