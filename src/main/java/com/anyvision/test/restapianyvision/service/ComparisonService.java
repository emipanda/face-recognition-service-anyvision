package com.anyvision.test.restapianyvision.service;

import com.anyvision.test.restapianyvision.repository.PersonRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class ComparisonService {
    static int n = 256;
    public static int dotProd(Vector<Integer> vector1, List<Integer> vector2){
        int product = 0;
        // Loop for calculate cot product
        for (int i = 0; i < n; i++)
            product = product + vector1.get(i)+vector2.get(i);
        return product;
    }
}
