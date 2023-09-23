package BusinessLayer.Transactions;

import BusinessLayer.Exceptions.BankException;
import lombok.Getter;
import lombok.Setter;

public abstract class Transaction {
    @Getter
    @Setter
    public boolean state = false;
    public abstract void Cancel() throws BankException;
}
