package ma.enset.ebankbackend.dtos;

import lombok.Data;
import ma.enset.ebankbackend.Enums.AccountStatus;

import java.util.Date;

@Data
public class CurrentBankAccountDTO extends BankAccountDTO {
    private String idBankAccount;
    private double balance;
    private Date CreatedAccount;
    private AccountStatus accountStatus;
    private String currency;
    private CustomerDTO customerDTO;
    private double overDraft;
}
