package ma.enset.ebankbackend.services;

import ma.enset.ebankbackend.dtos.*;
import ma.enset.ebankbackend.exceptions.BalanceNotSufficientException;
import ma.enset.ebankbackend.exceptions.BankAccountNotFoundException;
import ma.enset.ebankbackend.exceptions.CustomerNotFoundException;

import java.util.List;

public interface IBankAccountService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, Long customerId, double overDraft) throws CustomerNotFoundException;
    SavingBankAccountDTO saveSavingBankAccount(double initialBalance, Long customerId, double interestRate) throws CustomerNotFoundException;
    List<CustomerDTO> CUSTOMER_LIST();
    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;
    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;
    void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;
    List<BankAccountDTO> bankAccountList();

    CustomerDTO getCustomerDTO(Long customerID) throws CustomerNotFoundException;

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long customerID);

    List<AccountOperationDTO> accountOperationDTOS(String accountID);

    AccountHistoryDTO getAccountHistory(String accountID, int page, int size) throws BankAccountNotFoundException;
}
