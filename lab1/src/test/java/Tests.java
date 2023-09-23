import BusinessLayer.Accounts.Account;
import BusinessLayer.Accounts.AccountType;
import BusinessLayer.Banks.Bank;
import BusinessLayer.Banks.BankRate;
import BusinessLayer.Banks.CentralBank;
import BusinessLayer.Clients.Client;
import BusinessLayer.Clients.PassportData;
import BusinessLayer.Clients.PassportDataBuilder;
import BusinessLayer.Exceptions.BankException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class Tests {

    @Test
    public void accountBuilding() throws BankException {
        CentralBank centralBank = new CentralBank();
        Bank bank = new Bank("Private", new BankRate(1,3,4));
        centralBank.addBank(bank);
        PassportDataBuilder passportDataBuilder = new PassportDataBuilder();
        PassportData passportData = passportDataBuilder.setName("Kate").setSurname("Vasileva").setPassportNumber("1234567890").create();
        Client client = new Client(passportData);
        bank.addClient(client);
        Account account = bank.createAccount(AccountType.Deposit, client, 100000, LocalDate.of(2023, 10, 9));
        Assertions.assertEquals(100000, client.findAccount(account.accountId).balance);
    }

    @Test
    public void TransferMoney() throws BankException {
        CentralBank centralBank = new CentralBank();
        Bank bank = new Bank("Private", new BankRate(2, 3, 5));
        PassportDataBuilder builder1 = new PassportDataBuilder();
        PassportData passportData1 = builder1.setName("Kate").setSurname("Vasileva").setPassportNumber("1234567890").create();
        PassportDataBuilder builder2 = new PassportDataBuilder();
        PassportData passportData2 = builder2.setName("Liza").setSurname("Fedorova").setPassportNumber("4561237890").create();
        Client client1 = new Client(passportData1);
        Client client2 = new Client(passportData2);
        bank.addClient(client1);
        bank.addClient(client2);
        Account account1 = bank.createAccount(AccountType.Debit, client1, 5000, LocalDate.now());
        Account account2 = bank.createAccount(AccountType.Debit, client2, 5000, LocalDate.now());
        Account account3 = bank.createAccount(AccountType.Deposit, client1, 100000, LocalDate.of(2023, 10, 4));
        Account account4 = bank.createAccount(AccountType.Deposit, client2, 100000, LocalDate.of(2023, 10, 4));
        bank.transferBetweenClients(account1, account2, 3000);
        Assertions.assertEquals(2000, client2.findAccount(account2.accountId).balance);
        Assertions.assertThrows(BankException.class, () -> bank.transferBetweenClients(account3, account4, 300));
    }
}
