package ma.enset.ebankbackend.web;

import lombok.extern.slf4j.Slf4j;
import ma.enset.ebankbackend.dtos.AccountHistoryDTO;
import ma.enset.ebankbackend.dtos.AccountOperationDTO;
import ma.enset.ebankbackend.dtos.BankAccountDTO;
import ma.enset.ebankbackend.exceptions.BankAccountNotFoundException;
import ma.enset.ebankbackend.services.IBankAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@AllArgsConstructor
@Slf4j
public class BankAccountRestAPI {
    private IBankAccountService iBankAccountService;

    public BankAccountRestAPI(IBankAccountService iBankAccountService) {
        this.iBankAccountService = iBankAccountService;
    }

    @GetMapping("/Accounts/{accountID}")
    public BankAccountDTO getBankAccount(@PathVariable String accountID) throws BankAccountNotFoundException {
        BankAccountDTO bankAccountDTO = iBankAccountService.getBankAccount(accountID);
        return bankAccountDTO;
    }

    @GetMapping("/Accounts")
    public List<BankAccountDTO> listBankAccountDTOS(){
        return iBankAccountService.bankAccountList();
    }

    @GetMapping("/Accounts/{accountID}/Operations")
    public List<AccountOperationDTO> accountOperationDTOS(@PathVariable String accountID){
        return iBankAccountService.accountOperationDTOS(accountID);
    }

    @GetMapping("/Accounts/{accountID}/pageOperations")
    public AccountHistoryDTO accountOperationDTOList(
            @PathVariable String accountID,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) throws BankAccountNotFoundException {
        return iBankAccountService.getAccountHistory(accountID,page,size);
    }
}
