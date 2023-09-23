package BusinessLayer.Banks;

import BusinessLayer.Exceptions.BankException;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@NonNull
public class CentralBank {
    private static final CentralBank instance = new CentralBank();
    private List<Bank> banks = new ArrayList<>();
    public static CentralBank getInstance(){
        return instance;
    }
    public boolean findBank(String name)
    {
        return banks.stream()
                .anyMatch(x -> x.bankName == name);
    }

    public Optional<Bank> getBank(String name)
    {
        return banks.stream()
                .filter(x -> x.bankName == name)
                .findFirst();
    }

    public void addBank(Bank bank) throws BankException {
        if (bank == null)
        throw new BankException("Invalid bank");
        if (!checkBank(bank))
            banks.add(bank);
    }

    public void DeleteBank(Bank bank, String bankName) throws BankException {
        if (bank == null)
        throw new BankException("Invalid bank");
        if (!checkBank(bank))
            throw new BankException("Bank doesn't exist");
        banks.remove(bank);
    }

    private boolean checkBank(Bank bank)
    {
        return banks.stream()
                .anyMatch(x -> x.bankName == bank.bankName);
    }


}
