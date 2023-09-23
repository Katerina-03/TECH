package BusinessLayer.Transactions;

import BusinessLayer.Accounts.Account;
import BusinessLayer.Exceptions.BankException;

public class Transfer extends Transaction {
    private Account toAccount;
    private Account fromAccount;
    private float money;
    public Transfer(Account toThisAccount, Account fromThisAccount, float sum) throws BankException {
        toAccount = toThisAccount;
        fromAccount = fromThisAccount;
        money = sum;
        fromAccount.withdraw(money);
        toAccount.addSum(money);
    }
    @Override
    public void Cancel() throws BankException {
        if (state == true)
            throw new BankException("Transaction already canceled");
        toAccount.withdraw(money);
        fromAccount.addSum(money);
        state = true;
    }
}
