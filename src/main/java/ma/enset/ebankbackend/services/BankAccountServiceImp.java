package ma.enset.ebankbackend.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.ebankbackend.entities.BankAccount;
import ma.enset.ebankbackend.entities.CurrentAccount;
import ma.enset.ebankbackend.entities.Customer;
import ma.enset.ebankbackend.entities.SavingAccount;
import ma.enset.ebankbackend.repositories.AccountOperationRepository;
import ma.enset.ebankbackend.repositories.BankAccountRepository;
import ma.enset.ebankbackend.repositories.CustomerRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.slf4j.LoggerFactory.*;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class BankAccountServiceImp implements IBankAccountService {
    private CustomerRepository customerRepository;
    private BankAccountRepository bankAccountRepository;
    private AccountOperationRepository accountOperationRepository;
    //Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public Customer saveCustomer(Customer customer) {
        log.info("Saving new customer");
        Customer savedCustomer = customerRepository.save(customer);
        return savedCustomer;
    }

    @Override
    public BankAccount saveBankAccount(double initialBalance, String typeAccount, Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer==null){
            throw new RuntimeException("Customer not found");
        }
        BankAccount bankAccount;
        if (typeAccount.equals("CURR")){
            bankAccount = new CurrentAccount();
        } else  {
            bankAccount = new SavingAccount();
        }
        bankAccount.setIdBankAccount(UUID.randomUUID().toString());
        bankAccount.setCreatedAccount(new Date());
        bankAccount.setBalance(initialBalance);

        return null;
    }

    @Override
    public List<Customer> CUSTOMER_LIST() {
        return List.of();
    }

    @Override
    public BankAccount getBankAccount(String accountId) {
        return null;
    }

    @Override
    public void debit(String accountId, double amount, String description) {

    }

    @Override
    public void credit(String accountId, double amount, String description) {

    }

    @Override
    public void tranfer(String accountIdSource, String accountIdDestination, double amount) {

    }
}
