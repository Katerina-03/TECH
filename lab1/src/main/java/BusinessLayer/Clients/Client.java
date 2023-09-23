package BusinessLayer.Clients;


import BusinessLayer.Accounts.Account;
import BusinessLayer.Observe.Subscriber;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@NonNull
public class Client implements Subscriber {
private List<Account> accounts = new ArrayList<Account>();
public Client (PassportData clientPassportData){
    PassportData = clientPassportData;
}
@Getter
public PassportData PassportData;
@Getter
@Setter
private String info = " ";
public void addAccount (Account account){
    if (!checkAccount(account)){
        accounts.add(account);
    }
}
public void deleteAccount(Account account) {
    if (checkAccount(account)) {
        accounts.remove(account);
    }
}
public Account findAccount(UUID accountId){
    return accounts.stream()
            .filter(x -> x.accountId == accountId)
            .findFirst()
            .orElse(null);

}
private boolean checkAccount(Account account){
    return accounts.stream()
            .anyMatch(x -> x.accountId == account.accountId);
}


    @Override
    public void Update(String s) {
        info += s;
    }
}
