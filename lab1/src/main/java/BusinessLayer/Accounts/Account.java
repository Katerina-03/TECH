package BusinessLayer.Accounts;

import BusinessLayer.Banks.Bank;
import BusinessLayer.Clients.Client;
import BusinessLayer.Exceptions.BankException;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import java.util.UUID;
@NonNull
public abstract class Account {
    private Client client;
    public Account(Bank thisBank, Client newClient){
        bank = thisBank;
        client = newClient;
        accountId = UUID.randomUUID();
        balance = 0;

    }
    @Getter
    public UUID accountId;
    @Getter
    protected Bank bank;
    @Getter
    @Setter
    public float balance;
    public abstract void addSum(float money) throws BankException;
    public abstract void withdraw (float money) throws BankException;
    public abstract void monthCharge() throws BankException;

}
