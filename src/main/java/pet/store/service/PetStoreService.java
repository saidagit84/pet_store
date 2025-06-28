package pet.store.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import pet.store.dao.CustomerDao;
import pet.store.dao.EmployeeDao;
import pet.store.dao.PetStoreDao;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreData.PetStoreCustomer;
import pet.store.controller.model.PetStoreData.PetStoreEmployee;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PetStoreService {
	 @Autowired
	    private CustomerDao customerDao;
	 @Autowired
	    private EmployeeDao employeeDao;
    @Autowired
    private PetStoreDao petStoreDao;

    public PetStoreData savePetStore(PetStoreData data) {
        PetStore petStore = findOrCreatePetStore(data.id);

        petStore.storeName = data.storeName;
        petStore.storeLocation = data.storeLocation;

        PetStore saved = petStoreDao.save(petStore);
        return new PetStoreData(saved);
    }

    private PetStore findOrCreatePetStore(Long id) {
        if (id == null) {
            return new PetStore();
        } 
        return petStoreDao.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Pet store not found with ID: " + id));
    }
    
  

        private void copyEmployeeFields(Employee employee,
                                        PetStoreEmployee petStoreEmployee) {
        	
            employee.setEmployeeFirstName(petStoreEmployee.getEmployeeFirstName());
            employee.setEmployeeId(petStoreEmployee.getEmployeeId());
            employee.setEmployeeJobTitle(petStoreEmployee.getEmployeeJobTitle());
            employee.setEmployeeLastName(petStoreEmployee.getEmployeeLastName());
            employee.setEmployeePhone(petStoreEmployee.getEmployeePhone());
        }

        private void copyCustomerFields(Customer customer,
                                        PetStoreCustomer petStoreCustomer) {
            customer.setCustomerEmail(petStoreCustomer.getCustomerEmail());
            customer.setCustomerFirstName(petStoreCustomer.getCustomerFirstName());
             customer.setCustomerId(petStoreCustomer.getCustomerId());
            customer.setCustomerLastName(petStoreCustomer.getCustomerLastName());
        }

        private Employee findOrCreateEmployee(Long petStoreId, Long employeeId) {
            if (Objects.isNull(employeeId)) {
                return new Employee();
            }
            return findEmployeeById(petStoreId, employeeId);
        }

        private Customer findOrCreateCustomer(Long petStoreId, Long customerId) {
            if (Objects.isNull(customerId)) {
                return new Customer();
            }
            return findCustomerById(petStoreId, customerId);
        }
    

    
    private Employee findEmployeeById(Long petStoreId, Long employeeId) {
        Employee employee = employeeDao.findById(employeeId)
            .orElseThrow(() -> new NoSuchElementException(
                "Employee with ID=" + employeeId + " was not found."));

        if (employee.getPetStore().getPetStoreId() != petStoreId) {
            throw new IllegalArgumentException("The employee with ID=" + employeeId
                + " is not employed by the pet store with ID=" + petStoreId + ".");
        }

        return employee;
    }

    private Customer findCustomerById(Long petStoreId, Long customerId) {
        Customer customer = customerDao.findById(customerId)
            .orElseThrow(() -> new NoSuchElementException(
                "Customer with ID=" + customerId + " was not found."));

        boolean found = false;

        for (PetStore petStore : customer.getPetStores()) {
            if (petStore.getPetStoreId() == petStoreId) {
                found = true;
                break;
            }
        }

        if (!found) {
            throw new IllegalArgumentException("The customer with ID=" + customerId
                + " is not a member of the pet store with ID=" + petStoreId);
        }

        return customer;
    }
    @Transactional
    public PetStore findPetStoreById(Long petStoreId) {
        return petStoreDao.findById(petStoreId)
            .orElseThrow(() -> new NoSuchElementException("Pet store not found with ID: " + petStoreId));
    }

    @Transactional(readOnly = false)
    public PetStoreEmployee saveEmployee(Long petStoreId, 
                                         PetStoreEmployee petStoreEmployee) {
        PetStore petStore = findPetStoreById(petStoreId);
        Long employeeId = petStoreEmployee.getEmployeeId();
        Employee employee = findOrCreateEmployee(petStoreId, employeeId);

        copyEmployeeFields(employee, petStoreEmployee);

        employee.setPetStore(petStore);
        petStore.getEmployees().add(employee);

        Employee dbEmployee = employeeDao.save(employee);

        return new PetStoreEmployee(dbEmployee);
    }

    @Transactional
    public PetStoreCustomer saveCustomer(Long petStoreId, 
                                         PetStoreCustomer petStoreCustomer) {
        PetStore petStore = findPetStoreById(petStoreId);
        Long customerId = petStoreCustomer.getCustomerId();
        Customer customer = findOrCreateCustomer(petStoreId, customerId);

        copyCustomerFields(customer, petStoreCustomer);

        customer.getPetStores().add(petStore);
        petStore.getCustomers().add(customer);

        Customer dbCustomer = customerDao.save(customer);

        return new PetStoreCustomer(dbCustomer);
    }
    @Transactional
    public List<PetStoreData> retrieveAllPetStores() {
        List<PetStore> petStores = petStoreDao.findAll();
        return petStores.stream()
            .map(petStore -> new PetStoreData(petStore)) // Convert to DTO
            .collect(Collectors.toList());
    }
    @Transactional
    public PetStoreData retrievePetStoreById(Long petStoreId) {
        PetStore petStore = petStoreDao.findById(petStoreId)
            .orElseThrow(() -> new NoSuchElementException("Pet store not found with ID: " + petStoreId));
        return new PetStoreData(petStore);
    }
    @Transactional
    public void deletePetStoreById(Long petStoreId) {
        PetStore petStore = petStoreDao.findById(petStoreId)
            .orElseThrow(() -> new NoSuchElementException("Pet store not found with ID: " + petStoreId));
        petStoreDao.delete(petStore);
    }

}
