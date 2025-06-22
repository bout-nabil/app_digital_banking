package ma.enset.ebankbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.ebankbackend.Enums.OperationType;

import java.util.Date;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class AccountOperation {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idOperation;
    private Date operationDate;
    private double amount;
    private OperationType operationType;
    @ManyToOne
    private BankAccount bankAccount;
}
