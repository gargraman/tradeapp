**Trade Store **

Store for thousands of trades.

Operations:

addTrade(Trade t): Add trade 't' to store. Raise exception "TradeStoreException" if data is invalid
getTrades(): List the trades in the store. By default sorted by tradeId
updateTrade(Trade t): update the trade. Raise exception 'TradeStoreException' if data is invalid


Main files:
TradeApp -> main class to call various operations like add/get/update
trades.csv -> input file for data input
