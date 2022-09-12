package com.asgarov;

import java.util.List;

public class FindMaxProducts {
    public static void main(String[] args) {
        System.out.println(findMaxProducts(List.of(2, 9, 4, 7, 5, 2)));
    }

    public static long findMaxProducts(List<Integer> products) {
        long max = 0;
        for (int i = products.size() - 1; i >= 0; i--) {
            long currentMax = getMaxNumberOfProductsFromThisIndexDown(products.get(i), products, i-1);
            max = Math.max(currentMax, max);
        }
        return max;
    }

    private static long getMaxNumberOfProductsFromThisIndexDown(Integer currentResult, List<Integer> products, Integer currentMaxIndex) {
        long result = currentResult;
        int previous = currentResult;
        for (int i = currentMaxIndex; i >= 0 && products.get(i) > 0; i--) {
            if (previous == 0) {
                break;
            }

            int nextValue = products.get(i);
            int valueToAdd;
            if (nextValue >= previous) {
                valueToAdd = previous-1;
            } else {
                valueToAdd = nextValue;
            }

            result += valueToAdd;
            previous = valueToAdd;
        }
        return result;
    }
}
