package ma.enset.ebankbackend;

import ma.enset.ebankbackend.Enums.AccountStatus;
import ma.enset.ebankbackend.Enums.OperationType;
import ma.enset.ebankbackend.dtos.BankAccountDTO;
import ma.enset.ebankbackend.dtos.CurrentBankAccountDTO;
import ma.enset.ebankbackend.dtos.CustomerDTO;
import ma.enset.ebankbackend.dtos.SavingBankAccountDTO;
import ma.enset.ebankbackend.entities.*;
import ma.enset.ebankbackend.exceptions.BalanceNotSufficientException;
import ma.enset.ebankbackend.exceptions.BankAccountNotFoundException;
import ma.enset.ebankbackend.exceptions.CustomerNotFoundException;
import ma.enset.ebankbackend.repositories.AccountOperationRepository;
import ma.enset.ebankbackend.repositories.BankAccountRepository;
import ma.enset.ebankbackend.repositories.CustomerRepository;
import ma.enset.ebankbackend.services.IBankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankBackendApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(IBankAccountService iBankAccountService){
        return args -> {
            Stream.of("Nabil","Ayoub","Tawfiq").forEach(name->{
                CustomerDTO customerDTO = new CustomerDTO();
                customerDTO.setNameCustomer(name);
                customerDTO.setEmailCustomer(name+"@gmail.com");
                iBankAccountService.saveCustomer(customerDTO);
            });
            iBankAccountService.CUSTOMER_LIST().forEach(customer -> {
                try {
                    iBankAccountService.saveCurrentBankAccount(Math.random()*90000, customer.getIdCustomer(), 9000);
                    iBankAccountService.saveSavingBankAccount(Math.random()*120000, customer.getIdCustomer(), 5);

                } catch (CustomerNotFoundException e){ //| BankAccountNotFoundException | BalanceNotSufficientException e) {
                    e.printStackTrace();
                }
            });
            List<BankAccountDTO> bankAccountList = iBankAccountService.bankAccountList();
            for (BankAccountDTO bankAccount:bankAccountList) {
                for (int i = 0; i < 10; i++) {
                    String accountID;
                    if(bankAccount instanceof SavingBankAccountDTO){
                        accountID = ((SavingBankAccountDTO) bankAccount).getIdBankAccount();
                    } else {
                        accountID = ((CurrentBankAccountDTO) bankAccount).getIdBankAccount();
                    }
                    iBankAccountService.credit(accountID, 10000+Math.random()*120000,"Credit");
                    iBankAccountService.debit(accountID,1000+Math.random()*9000,"Debit");
                }
            }
        };
    }
    //@Bean
    CommandLineRunner start(CustomerRepository customerRepository,
                            BankAccountRepository bankAccountRepository,
                            AccountOperationRepository accountOperationRepository){
        return args -> {
            Stream.of("Nabil","Ayoub","Tawfiq").forEach(name->{
                Customer customer = new Customer();
                customer.setNameCustomer(name);
                customer.setEmailCustomer(name+"@gmail.com");
                customerRepository.save(customer);
            });
            customerRepository.findAll().forEach(customer -> {
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setIdBankAccount(UUID.randomUUID().toString().replace("-","").substring(0,5));
                currentAccount.setOverDraft(9000);
                currentAccount.setBalance(Math.random()*90000);
                currentAccount.setCreatedAccount(new Date());
                currentAccount.setAccountStatus(AccountStatus.CREATED);
                currentAccount.setCurrency("MAD");
                currentAccount.setCustomer(customer);
                bankAccountRepository.save(currentAccount);

                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setInterestRate(5);
                savingAccount.setIdBankAccount(UUID.randomUUID().toString().replace("-","").substring(0,5));
                savingAccount.setBalance(Math.random()*90000);
                savingAccount.setCreatedAccount(new Date());
                savingAccount.setAccountStatus(AccountStatus.CREATED);
                savingAccount.setCurrency("MAD");
                savingAccount.setCustomer(customer);
                bankAccountRepository.save(savingAccount);
            });
            bankAccountRepository.findAll().forEach(bankAccount -> {
                for (int i = 0; i < 10; i++) {
                    AccountOperation accountOperation = new AccountOperation();
                    accountOperation.setOperationType(Math.random()>0.5? OperationType.DEBIT:OperationType.CREDIT);
                    accountOperation.setOperationDate(new Date());
                    accountOperation.setAmount(Math.random()*120000);
                    accountOperation.setBankAccount(bankAccount);
                    accountOperationRepository.save(accountOperation);
                }
            });
        };
    }
}
