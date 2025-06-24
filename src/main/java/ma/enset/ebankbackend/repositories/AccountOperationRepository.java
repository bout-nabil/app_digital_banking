package ma.enset.ebankbackend.repositories;

import ma.enset.ebankbackend.entities.AccountOperation;
import ma.enset.ebankbackend.entities.BankAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {
    List<AccountOperation> ACCOUNT_OPERATION_LIST(String accountID);
    Page<AccountOperation> ACCOUNT_OPERATION_LIST(String accountID, Pageable pageable);
}
