package BusinessLayer.Transactions;

import BusinessLayer.Accounts.Account;
import BusinessLayer.Exceptions.BankException;

public class Withdraw extends Transaction {
    private Account account;
    private float money;
    public Withdraw(Account thisAccount, float sum) throws BankException {
        account = thisAccount;
        money = sum;
        account.withdraw(money);
    }

    @Override
    public void Cancel() throws BankException {
        if (state == true)
            throw new BankException("Transaction already canceled");
        account.withdraw(money);
        state = true;

    }
}
