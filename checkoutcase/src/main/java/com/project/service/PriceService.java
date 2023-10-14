package com.project.service;

import com.project.repository.PriceRepository;

import java.util.HashMap;
import java.util.Map;

public class PriceService {

    private PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public double getPriceBySellerAndItemId(String sellerId, int itemId) {
        return priceRepository.getPriceBySellerAndItemId(sellerId, itemId);
    }
}
