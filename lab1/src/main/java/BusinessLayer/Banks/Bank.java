package BusinessLayer.Banks;

import BusinessLayer.Accounts.*;
import BusinessLayer.Clients.Client;
import BusinessLayer.Exceptions.BankException;
import BusinessLayer.Observe.Publisher;
import BusinessLayer.Observe.Subscriber;
import BusinessLayer.Transactions.Transaction;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Bank implements Publisher {
    private List<Account> bankAccounts = new ArrayList<Account>();
    private List<Client> clients = new ArrayList<Client>();
    private List<Subscriber> subscribers = new ArrayList<>();
    private List<Transaction> allTransactions = new ArrayList<>();

    @NonNull
    public Bank(String newBankName, BankRate newBankRate) {
        bankName = newBankName;
        bankRate = newBankRate;
    }

    @Getter
    public String bankName;
    @Getter
    public BankRate bankRate;

    @NonNull
    public Account createAccount(AccountType accountType, Client client, float balance, LocalDate term) throws BankException {
        Account newAcc = null;
        if (accountType == AccountType.Credit) {
            newAcc = new AccountCredit(this, client, balance);
            bankAccounts.add(newAcc);
            client.addAccount(newAcc);
        }

        if (accountType == AccountType.Debit) {
            newAcc = new AccountDebit(this, client, balance);
            bankAccounts.add(newAcc);
            client.addAccount(newAcc);
        }

        if (accountType == AccountType.Deposit) {
            newAcc = new AccountDeposit(this, client, balance, term);
            bankAccounts.add(newAcc);
            client.addAccount(newAcc);
        }

        if (!CheckClient(client)) {
            throw new BankException("Client already exist");
        }
        if (CheckClient(client)) {
            clients.add(client);
        }
        return newAcc;
    }

    public void deleteAccount(Account account, Client client) throws BankException {
        if (!CheckClient(client))
            throw new BankException("Client doesn't exist");
        bankAccounts.remove(account);
        client.deleteAccount(account);
        clients.remove(client);
    }

    @NonNull
    public void addClient(Client client) throws BankException {
        if (CheckClient(client)) {
            throw new BankException("Client already exist");
        }
        clients.add(client);
    }

    @NonNull
    public void deleteClient(Client client) throws BankException {
        if (!CheckClient(client)) {
            throw new BankException("Client doesn't exist");
        }
        clients.remove(client);
    }

    @NonNull
    public void transferBetweenClients(Account toAccount, Account fromAccount, float money) throws BankException {
        if (money < 0) {
            throw new BankException("Invalid value");
        }

        fromAccount.withdraw(money);
        toAccount.addSum(money);
    }

    @Override
    @NonNull
    public void addObserver(Subscriber subscriber) throws BankException {
        if (subscriber == null)
            throw new BankException("Subscriber doesn't exist");
        subscribers.add(subscriber);

    }

    @Override
    @NonNull
    public void removeObserver(Subscriber subscriber) throws BankException {
        subscribers.remove(subscriber);

    }

    @Override
    @NonNull
    public void inform() {
        for (Subscriber subscriber : subscribers) {
            subscriber.Update("Bank rate has changed");
        }

    }

    private boolean CheckClient(Client client) {
        return clients.stream()
                .anyMatch(x -> x.PassportData == client.PassportData);
    }
    private void addTransaction(Transaction transaction)
    {
        allTransactions.add(transaction);
    }
}



