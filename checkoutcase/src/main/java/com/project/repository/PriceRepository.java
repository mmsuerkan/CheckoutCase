package com.project.repository;

import java.util.HashMap;
import java.util.Map;

public class PriceRepository {

    private Map<String, Map<Integer, Double>> pricingTable = new HashMap<>();

    public PriceRepository() {
        Map<Integer, Double> sellerA = new HashMap<>();
        sellerA.put(1, 500.0);
        sellerA.put(2, 75.0);
        sellerA.put(3, 510000.0);
        sellerA.put(4, 200.0);
        sellerA.put(5, 750.0);
        sellerA.put(6, 55.0);
        sellerA.put(7, 15.0);
        sellerA.put(8, 90.0);
        sellerA.put(9, 3200.0);
        sellerA.put(10, 6000.0);
        sellerA.put(11, 2000.0);
        sellerA.put(12, 7800.0);
        sellerA.put(13, 6500.0);
        sellerA.put(14, 750.0);
        sellerA.put(15, 800.0);

        Map<Integer, Double> sellerB = new HashMap<>();
        sellerB.put(1, 60.0);
        sellerB.put(2, 800.0);
        sellerB.put(3, 250000.0);
        sellerB.put(4, 10.0);
        sellerB.put(5, 105.0);
        sellerB.put(6, 33.0);
        sellerB.put(7, 50.0);
        sellerB.put(8, 25.0);
        sellerB.put(9, 350.0);
        sellerB.put(10, 600.0);
        sellerB.put(11, 2000.0);
        sellerB.put(12, 12000.0);
        sellerB.put(13, 40000.0);
        sellerB.put(14, 4200.0);
        sellerB.put(15, 800.0);

        Map<Integer, Double> sellerC = new HashMap<>();
        sellerC.put(1, 50.0);
        sellerC.put(2, 75.0);
        sellerC.put(3, 25000.0);
        sellerC.put(4, 10.0);
        sellerC.put(5, 105.0);
        sellerC.put(6, 33.0);
        sellerC.put(7, 50.0);
        sellerC.put(8, 25.0);
        sellerC.put(9, 350.0);
        sellerC.put(10, 600.0);
        sellerC.put(11, 2000.0);
        sellerC.put(12, 12000.0);
        sellerC.put(13, 40000.0);
        sellerC.put(14, 4200.0);
        sellerC.put(15, 800.0);

        pricingTable.put("1", sellerA);
        pricingTable.put("2", sellerB);
        pricingTable.put("3", sellerC);
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
