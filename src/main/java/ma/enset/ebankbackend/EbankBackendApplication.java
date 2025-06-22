package ma.enset.ebankbackend;

import ma.enset.ebankbackend.Enums.AccountStatus;
import ma.enset.ebankbackend.entities.CurrentAccount;
import ma.enset.ebankbackend.entities.Customer;
import ma.enset.ebankbackend.entities.SavingAccount;
import ma.enset.ebankbackend.repositories.AccountOperationRepository;
import ma.enset.ebankbackend.repositories.BankAccountRepository;
import ma.enset.ebankbackend.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankBackendApplication.class, args);
    }
    @Bean
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
        };
    }
}
