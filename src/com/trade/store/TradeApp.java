package com.trade.store;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class TradeApp {

	private static TradeStoreHelper helper = new TradeStoreHelper();

	public static void main(String[] args) {
		TradeApp app = new TradeApp();
		System.out.println("Trade Report App");
		System.out.println("\n\nReading trades from trades.csv");
		addTrades("trades.csv");
		System.out.println("\n\nTrades in system:");
		app.showAllTrades();
		System.out.println("\n\nTrade T1");
		app.showTrade("T1");
		System.out.println("\n\nUpdating Trade T1");
		app.updateTrade("T1");
		System.out.println("\n\nAfter update Trade T1");
		app.showTrade("T1");

	}

	private  void updateTrade(String tradeid) {
		List<Trade> trades = helper.getTradesById(tradeid);
		if(trades.size()>0) {
			Trade t1 = trades.get(0);
			//updating the content and version
			Trade updTrade = new Trade(t1.getId(),t1.getVersion()+1,"CP-22",
					"B22",
					t1.getMaturityDt()
					);
			helper.updateTrade(updTrade);
		}
	}

	private  void showTrade(String tradeid) {
		List<Trade> trades = helper.getTradesById(tradeid);
		printTrades(trades);
	}

	private void showAllTrades() {
		List<Trade> trades = helper.getAllTrades();
		printTrades(trades);
	}
	
	private void printTrades(List<Trade> trades) {
		String header = String.format("|%-10s|%-10s|%-10s|%-10s|%-15s|%-15s|%-10s|", 
				"Trade Id",
				"Version",
				"C-id",
				"Book Id",
				"Create Dt",
				"Maturity Dt",
				"Expired?");
		System.out.println(header);
		for (Trade t : trades) {
			String s = String.format("|%-10s|%-10d|%-10s|%-10s|%-15s|%-15s|%-10s|", 
					t.getId(),
					t.getVersion(),
					t.getCounterpartyId(),
					t.getBookId(),
					Utils.fromDate(t.getCreatedDt()),
					Utils.fromDate(t.getMaturityDt()),
					t.isExpired()?"True":"False");
			System.out.println(s);
		}
	}

	private static void addTrades(String filename) {// parsing a CSV file
		String line = "";
		String splitBy = ",";
		boolean flag = false;
		
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			while ((line = br.readLine()) != null) {

				if (flag == false) {
					flag = true;// don't parse the first row
					continue;
				}
				String[] data = line.split(splitBy);
				try {
					Trade trade = new Trade(data[0], Integer.parseInt(data[1]), data[2], data[3],
							Utils.getDate(data[4]));
					helper.addTrade(trade);
				} catch (TradeStoreException ex) {
					System.out.println(line + "\nTrade exception:\n" + ex.getMessage());
				} catch (Exception e) {
					System.out.println(line + "\nData exception:\n" + e.getMessage());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
