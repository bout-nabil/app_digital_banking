package ma.enset.ebankbackend.services;

import ma.enset.ebankbackend.entities.BankAccount;
import ma.enset.ebankbackend.entities.CurrentAccount;
import ma.enset.ebankbackend.entities.SavingAccount;
import ma.enset.ebankbackend.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BankService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    public void consulter(){
        BankAccount bankAccount = bankAccountRepository.findById("0710b").orElse(null);
        System.out.println("***********************************************");
        System.out.println(bankAccount.getIdBankAccount());
        System.out.println(bankAccount.getBalance());
        System.out.println(bankAccount.getCreatedAccount());
        System.out.println(bankAccount.getAccountStatus());
        System.out.println(bankAccount.getCustomer().getNameCustomer());
        System.out.println(bankAccount.getClass().getSimpleName());
        if (bankAccount instanceof CurrentAccount){
            System.out.println("Over Draft: "+((CurrentAccount) bankAccount).getOverDraft());
        }else if (bankAccount instanceof SavingAccount){
            System.out.println("Rate: "+((SavingAccount) bankAccount).getInterestRate());
        }
        bankAccount.getAccountOperationList().forEach(accountOperation -> {
            System.out.println(accountOperation.getOperationType()+"\t"
                    +accountOperation.getAmount()+"\t"+accountOperation.getOperationDate());
        });
    }
}
