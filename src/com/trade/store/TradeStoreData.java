package com.trade.store;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

public class TradeStoreData<T extends ITrade> {

	ConcurrentSkipListMap<T, String> storage;
	Comparator<T> comparator = null;

	TradeStoreData(Comparator<T> c) {
		storage = new ConcurrentSkipListMap<>(c);
	}
	
	TradeStoreData() {
		storage = new ConcurrentSkipListMap<>();
	}

	public void save(T input) {
		if(input==null) {
			throw new TradeStoreException("Input data incorrect");
		}
		String id = input.getId();
		storage.put(input, id);
	}

	public List<T> getAll() {
		return new ArrayList<T>(storage.keySet());
	}

	public List<T> get(String id) {
		if(id==null) {
			throw new TradeStoreException("'Id' value incorrect");
		}
		return storage.keySet().stream()
				.filter(map -> map.getId().equals(id))
				.collect(Collectors.toList());
	}

}
