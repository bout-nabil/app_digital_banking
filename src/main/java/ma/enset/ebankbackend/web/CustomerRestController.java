package ma.enset.ebankbackend.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.ebankbackend.dtos.CustomerDTO;
import ma.enset.ebankbackend.entities.Customer;
import ma.enset.ebankbackend.services.IBankAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class CustomerRestController {
    private IBankAccountService iBankAccountService;
    @GetMapping("/Customers")
    public List<CustomerDTO> customerList(){
        return iBankAccountService.CUSTOMER_LIST();
    }
    @GetMapping("/Customers/{id}")
    public CustomerDTO getCustomer(@PathVariable(name = "id") Long customerID){
        return;
    }
}
