package BusinessLayer.Transactions;

import BusinessLayer.Accounts.Account;
import BusinessLayer.Exceptions.BankException;

public class TopUp extends Transaction{
    private final Account account;
    private final float money;
    public TopUp(Account thisAccount, float sum) throws BankException {
        account = thisAccount;
        money = sum;
        account.addSum(money);
    }

    @Override
    public void Cancel() throws BankException {
        if (state)
            throw new BankException("Transaction already canceled");
        account.withdraw(money);
        state = true;

    }
}
