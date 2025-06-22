package ma.enset.ebankbackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.ebankbackend.Enums.AccountStatus;

import java.util.Date;
import java.util.List;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class BankAccount {
    @Id
    private String idBankAccount;
    private double balance;
    private Date CreatedAccount;
    private AccountStatus accountStatus;
    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "bankAccount")
    private List<AccountOperation> accountOperationList;
}
