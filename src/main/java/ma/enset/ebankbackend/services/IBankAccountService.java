package ma.enset.ebankbackend.services;

import ma.enset.ebankbackend.entities.BankAccount;
import ma.enset.ebankbackend.entities.Customer;

import java.util.List;

public interface IBankAccountService {
    Customer saveCustomer(Customer customer);
    BankAccount saveBankAccount(double initialBalance, String typeAccount, Long customerId);
    List<Customer> CUSTOMER_LIST();
    BankAccount getBankAccount(String accountId);
    void debit(String accountId, double amount, String description);
    void credit(String accountId, double amount, String description);
    void tranfer(String accountIdSource, String accountIdDestination, double amount);
}
