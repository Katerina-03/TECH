package BusinessLayer.Accounts;

import BusinessLayer.Banks.Bank;
import BusinessLayer.Clients.Client;
import BusinessLayer.Exceptions.BankException;

public class AccountDebit extends Account {
    private float monthCommission;
    private int days;
    private float limit = 0;
    public AccountDebit(Bank bank, Client client, float thisBalance){
        super(bank, client);
        days = 0;
        balance = thisBalance;
    }

    @Override
    public void addSum(float money) throws BankException {
        if(money < limit) throw new BankException("Invalid sum");
        balance += money;

    }

    @Override
    public void withdraw(float money) throws BankException {
        if(money < limit) throw new BankException("Invalid sum");
        if(money > balance) throw new BankException("Can't go negative");
        balance -= money;
    }

    @Override
    public void monthCharge() {
        if (days != 30) {
            days++;
            monthCommission += balance * (bank.bankRate.debitPercent() / 365);
        }

        balance += monthCommission;
        monthCommission = 0;

    }
}
