package BusinessLayer.Accounts;

import BusinessLayer.Banks.Bank;
import BusinessLayer.Clients.Client;
import BusinessLayer.Exceptions.BankException;
import java.time.LocalDate;

public class AccountDeposit extends Account {
    private float monthCommission;
    private int days;
    private LocalDate term;
    private float limit = 0;
    public AccountDeposit(Bank bank, Client client, float thisBalance, LocalDate finnish){
        super(bank, client);
        balance = thisBalance;
        days = 0;
        term = finnish;
    }


    @Override
    public void addSum(float money) throws BankException {
        if(money < limit) throw new BankException("Invalid sum");
        balance += money;
    }

    @Override
    public void withdraw(float money) throws BankException {
        if (money < limit) throw new BankException("Invalid sum");
        if (term != LocalDate.now()) throw  new BankException("Deposit account hasn't expired");
        balance -= money;

    }

    @Override
    public void monthCharge() {
        if (days != 30)
        {
            days++;
            monthCommission += balance * (bank.bankRate.depositPercent() / 365);
        }

        balance += monthCommission;
        monthCommission = 0;

    }
}
