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
	    	private Long CustomerId; 
	        private String CustomerFirstName; 
	        private String CustomerLastName; 
	        private String CustomerEmail; 
	        public PetStoreCustomer(Customer customer) {
	            this.CustomerId = customer.CustomerId;
	            this.CustomerFirstName = customer.getCustomerFirstName();
	            this.CustomerLastName = customer.getCustomerLastName();
	            this.CustomerEmail = customer.getCustomerEmail();
	        }
	        public Long getCustomerId() { return CustomerId; }
		    public void setCustomerId(Long id) { this.CustomerId = id; }
		    public String getCustomerFirstName() { return CustomerFirstName; }
		    public void setCustomerFirstName(String CustomerFirstName) { this.CustomerFirstName = CustomerFirstName; }
		    public String getCustomerLastName() { return CustomerLastName; }
		    public void setCustomerLastName(String CustomerLastName) { this.CustomerLastName = CustomerLastName; }
		    public String getCustomerEmail() { return CustomerEmail; }
		    public void setCustomerEmail(String CustomerEmail) { this.CustomerEmail = CustomerEmail; }
	    }
	    
	    @Data
	    @NoArgsConstructor
	    public static class PetStoreEmployee {
	        private Long EmployeeId; 
	        private String EmployeeFirstName;
	        private String EmployeeLastName; 
	        private String EmployeeJobTitle; 
	        private String EmployeePhone;

	        public PetStoreEmployee(Employee employee) {
	            this.EmployeeId = employee.EmployeeId;
	            this.EmployeeFirstName = employee.getEmployeeFirstName();
	            this.EmployeeLastName = employee.getEmployeeLastName();
	            this.EmployeeJobTitle = employee.getEmployeeJobTitle();
	            this.EmployeePhone = employee.getEmployeePhone();
	        }
	        public Long getEmployeeId() {
		        return EmployeeId;
		    }
		    public void setEmployeeId(Long EmployeeId) { this.EmployeeId = EmployeeId; }
		    public String getEmployeeFirstName() { return EmployeeFirstName; }
		    public void setEmployeeFirstName(String EmployeeFirstName) { this.EmployeeFirstName = EmployeeFirstName; }
		    public String getEmployeeLastName() { return EmployeeLastName; }
		    public void setEmployeeLastName(String EmployeeLastName) { this.EmployeeLastName = EmployeeLastName; }
		    public String getEmployeeJobTitle() { return EmployeeJobTitle; }
		    public void setEmployeeJobTitle(String EmployeeJobTitle) { this.EmployeeJobTitle = EmployeeJobTitle; }
		    public String getEmployeePhone() { return EmployeePhone; }
		    public void setEmployeePhone(String EmployeePhone) { this.EmployeePhone = EmployeePhone; }
		   

			
	    }

}