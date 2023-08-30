package com.clover.labs.java8;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapLookupTest {
	public static void main(String[] args) {

		Map<String, Integer> map = new HashMap<>();
		map.put("RepairInsufficientFunds", 1);
		map.put("RepairDebitAccountingFailure", 2);
		map.put("ApproveRateNotDetermined", 3);
		map.put("FourEVApproveSanctionsCheckResponse", 4);

		MapLookup lookup = new MapLookupImpl();

		List<Integer> values = lookup.lookup("^(Repair)", map);

		System.out.println(values);
	}
}
