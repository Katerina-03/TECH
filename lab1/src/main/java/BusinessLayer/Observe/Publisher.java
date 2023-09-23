package BusinessLayer.Observe;

import BusinessLayer.Exceptions.BankException;

public interface Publisher {
    void addObserver(Subscriber subscriber) throws BankException;
    void removeObserver(Subscriber subscriber) throws BankException;
    void inform();
}
