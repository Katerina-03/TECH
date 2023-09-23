package BusinessLayer.Accounts;

import BusinessLayer.Banks.Bank;
import BusinessLayer.Clients.Client;
import BusinessLayer.Exceptions.BankException;


public class AccountCredit extends Account {
    private float limit = 0;
    public AccountCredit(Bank bank, Client client, float thisBalance){
        super(bank, client);
        balance = thisBalance;
    }


    @Override
    public void addSum(float money) {
        if (money < limit){
            balance += money - bank.bankRate.commissionPercent();
        }
        balance += money;

    }

    @Override
    public void withdraw(float money) {
        if (balance < limit){
            balance -= money - bank.bankRate.commissionPercent();
        }
        balance -= money;

    }

    @Override
    public void monthCharge() throws BankException {
        throw new BankException("Month charges for credit account doesn't exist");
    }
}
