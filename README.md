# BankingService

Banking Service API is used for the for performing banking functions listed as below

/api/bank/withdrawMoney:
This api call is used to record the user withdrawal transaction based on the withdrawal amount and account number

/api/bank/depositMoney:
This api call is used to record the user deposit transaction based on the deposit amount and account number

/api/bank/getBalance
This api is used to get the current balance of the user based on account number


/api/bank/creditInterest
This api is used to calculate the credit interest based on the user account.It is assumed interest rate is 4% and is calculated on the outstanding balance in the account


/api/bank/debitInterest
This api is used to calculate the debit interest based on the user account in case of negative balance.It is assumed interest rate is 2% and is calculated on the outstanding
negative balance in the account


/api/bank/getTransactions
This api is used to fetch the last 20 transactions of the user based on the user account





