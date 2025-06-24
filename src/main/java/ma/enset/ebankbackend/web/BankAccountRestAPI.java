package ma.enset.ebankbackend.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.ebankbackend.services.IBankAccountService;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@AllArgsConstructor
@Slf4j
public class BankAccountRestAPI {
    private IBankAccountService iBankAccountService;

    public BankAccountRestAPI(IBankAccountService iBankAccountService) {
        this.iBankAccountService = iBankAccountService;
    }


}
