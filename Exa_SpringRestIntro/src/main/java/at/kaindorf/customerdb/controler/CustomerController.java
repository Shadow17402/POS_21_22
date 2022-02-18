package at.kaindorf.customerdb.controler;

import at.kaindorf.customerdb.data.CustomerRepository;
import at.kaindorf.customerdb.pojos.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.lang.reflect.Field;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "/customer")
@CrossOrigin(origins ="*")
public class CustomerController {

    @Autowired
    private CustomerRepository cusRepo;

    @GetMapping("/all")
    public ResponseEntity<Iterable<Customer>> getAllCustomers(@RequestParam("page") int pageNo,
                                                              @RequestParam("size") int pageSize){
        Pageable page =  PageRequest.of(pageNo, pageSize, Sort.by("lastname").descending());
        return ResponseEntity.of(Optional.of(cusRepo.findAll(page)));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id){
//        Optional<Customer> custOpt = cusRepo.findById(id);
//
//        if(custOpt.isPresent()){
//            return ResponseEntity.ok(custOpt.get());
//        }
//        return ResponseEntity.notFound().build();

        return ResponseEntity.of(cusRepo.findById(id));
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        try {
            customer = cusRepo.save(customer);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(customer.getCustomer_id()).toUri();
            return ResponseEntity.created(location).build();
        }catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){
        if(cusRepo.existsById(customer.getCustomer_id())){
            cusRepo.save(customer);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Customer> patchCustomer(@PathVariable("id") long id, @RequestBody Customer patchCustomer){
        if(cusRepo.existsById(id)){
            Customer customer = cusRepo.getById(id);
            for(Field field : Customer.class.getDeclaredFields()){
                field.setAccessible(true);
                Object value = ReflectionUtils.getField(field,patchCustomer);
                if(value != null){
                    ReflectionUtils.setField(field,customer,value);
                }
            }
            cusRepo.save(customer);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") long id){
        if(cusRepo.existsById(id)){
            cusRepo.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
