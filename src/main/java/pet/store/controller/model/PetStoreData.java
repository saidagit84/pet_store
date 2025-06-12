package pet.store.controller.model;

import java.util.Set;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

@Data 
@NoArgsConstructor
public class PetStoreData {
	
	    public Long id;
	    public String storeName;
	    public String storeLocation; 
	    @EqualsAndHashCode.Exclude
	    @ToString.Exclude
	    @ManyToMany(cascade = CascadeType.PERSIST)
	    @JoinTable(name = "pet_store_customer",
	            joinColumns = @JoinColumn(name = "pet_store_id"),
	            inverseJoinColumns = @JoinColumn(name = "customer_id"))
	    private Set<PetStoreCustomer> customers;
	    @EqualsAndHashCode.Exclude
	    @ToString.Exclude
	    @OneToMany(mappedBy = "petStore", cascade = CascadeType.ALL, orphanRemoval = true)
	    private Set<PetStoreEmployee> employees;
	    public PetStoreData(PetStore petStore) {
	        this.id = petStore.id;
	        this.storeName = petStore.storeName;
	        this.storeLocation = petStore.storeLocation;
	        if(petStore.customers != null) {
	            this.customers = petStore.customers.stream()
	                .map(PetStoreCustomer::new)
	                .collect(Collectors.toSet());
	        }
	        if(petStore.employees != null) {
	            this.employees = petStore.employees.stream()
	                .map(PetStoreEmployee::new)
	                .collect(Collectors.toSet());
	        }
	    }
	    @Data
	    @NoArgsConstructor
	    public static class PetStoreCustomer {
	        private Long id;
	        private String name;
	        public PetStoreCustomer(Customer customer) {
	            this.id = customer.id;
	            this.name = customer.customerName;
	        }
	    }
	    @Data
	    @NoArgsConstructor
	    public static class PetStoreEmployee {
	        private Long id;
	        private String name;
	        public PetStoreEmployee(Employee employee) {
	            this.id = employee.id;
	            this.name = employee.employeeName;
	        }
	    }
}