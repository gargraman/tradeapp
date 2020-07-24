package com.trade.store;

import java.util.Comparator;

public class TradeComparator implements Comparator<Trade> {

	@Override
	public int compare(Trade o1, Trade o2) {
		if(o1.getId().compareTo(o2.getId())==0) {
			return o2.getVersion()-o1.getVersion();
		}
		return o1.getId().compareTo(o2.getId());
	}

}
