package ma.enset.ebankbackend.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.ebankbackend.Enums.OperationType;
import ma.enset.ebankbackend.entities.BankAccount;

import java.util.Date;

@Data
public class AccountOperationDTO {
    private Long idOperation;
    private Date operationDate;
    private double amount;
    private OperationType operationType;
    private String description;
}
