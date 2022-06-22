package emsi.ma.ebankingbackend.web;

import emsi.ma.ebankingbackend.dtos.BankAccountDTO;
import emsi.ma.ebankingbackend.dtos.CustomerDTO;
import emsi.ma.ebankingbackend.entities.Customer;
import emsi.ma.ebankingbackend.exceptions.CustomerNotFoundException;
import emsi.ma.ebankingbackend.services.BankAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class CustomerRestController {
    private BankAccountService bankAccountService;
    @GetMapping("/customers")
    public List<CustomerDTO> customers(){
        return bankAccountService.listCustomer();
    }

    @GetMapping("/customers/search")
    public List<CustomerDTO> searchCustomers(@RequestParam(name = "keyword", defaultValue = "")String keyword ){
        return bankAccountService.searchCustomers(keyword);
    }


    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomer(@PathVariable(name="id") Long customerId) throws CustomerNotFoundException {
        return bankAccountService.getCustomer(customerId);
    }

    @PostMapping("/customers")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO request){
        return bankAccountService.saveCustomer(request);
    }

    @PutMapping("/customers/{customerId}")
    public CustomerDTO updateCustomer(@PathVariable Long customerId,@RequestBody CustomerDTO customerDTO){
        customerDTO.setId(customerId);
        return bankAccountService.updateCustomer(customerDTO);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id){
        bankAccountService.deleteCustomer(id);
    }

    @GetMapping("/customer-accounts/{id}")
    public List<BankAccountDTO> getCustomerAccounts(@PathVariable(name="id") Long customerId)throws CustomerNotFoundException{
        return bankAccountService.getCustomerAccounts(customerId);
    }
}