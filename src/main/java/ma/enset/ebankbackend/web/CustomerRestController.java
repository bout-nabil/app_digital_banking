package ma.enset.ebankbackend.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.ebankbackend.dtos.CustomerDTO;
import ma.enset.ebankbackend.entities.Customer;
import ma.enset.ebankbackend.exceptions.CustomerNotFoundException;
import ma.enset.ebankbackend.services.IBankAccountService;
import org.springframework.web.bind.annotation.*;

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
    public CustomerDTO getCustomer(@PathVariable(name = "id") Long customerID) throws CustomerNotFoundException {
        return iBankAccountService.getCustomerDTO(customerID);
    }
    @PostMapping("/Customers")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        return iBankAccountService.saveCustomer(customerDTO);
    }
    @PutMapping("/Customers/{customerID}")
    public CustomerDTO updateCustomer(@PathVariable Long customerID,@RequestBody CustomerDTO customerDTO){
        customerDTO.setIdCustomer(customerID);
        return iBankAccountService.updateCustomer(customerDTO);
    }
    @DeleteMapping("/Customers/{id}")
    public void deleteCustomer(@PathVariable Long id){
        iBankAccountService.deleteCustomer(id);
    }
}
