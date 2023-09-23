package BusinessLayer.Banks;

import BusinessLayer.Accounts.AccountSettings;
import BusinessLayer.Exceptions.BankException;

import java.util.HashMap;
import java.util.Map;

public class BankRate {
    private Map<AccountSettings, Float> bankRate = new HashMap<AccountSettings, Float>();
    public BankRate(float depositPercent, float debitPercent, float commission){
        bankRate.put(AccountSettings.Commission, commission);
        bankRate.put(AccountSettings.DebitPercent, debitPercent);
        bankRate.put(AccountSettings.DepositPercent, depositPercent);
    }
    public Float debitPercent(){
        return bankRate.get(AccountSettings.DebitPercent);
    }
    public Float depositPercent(){
        return bankRate.get(AccountSettings.DepositPercent);
    }
    public Float commissionPercent(){
        return bankRate.get(AccountSettings.Commission);
    }
    public void changeRate(float newRate, AccountSettings accountSettings) throws BankException {
        if (newRate < 0 && newRate > 100) {
            throw new BankException("Invalid rate");
        }
        bankRate.replace(accountSettings, newRate);
    }
}
