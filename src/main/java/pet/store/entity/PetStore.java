package pet.store.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;
@Entity
@Data
public class PetStore {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		public Long id;
	    public String storeName;
	    public String storeLocation; 
	    @EqualsAndHashCode.Exclude
	    @ToString.Exclude
	    @ManyToMany(cascade = CascadeType.PERSIST)
	    @JoinTable(name = "pet_store_customer",
	            joinColumns = @JoinColumn(name = "pet_store_id"),
	            inverseJoinColumns = @JoinColumn(name = "customer_id"))
		public Set<Customer> customers;
	    @EqualsAndHashCode.Exclude
	    @ToString.Exclude
	    @OneToMany(mappedBy = "petStore", cascade = CascadeType.ALL, orphanRemoval = true)
		public Set<Employee> employees;
	    
	    public Long getPetStoreId() { return id; }
	    public void setPetStoreId(Long id) { this.id = id; }
	    public String getName() { return storeName; }
	    public void setName(String name) { this.storeName = name; }
	    public String getLocation() { return storeLocation; }
	    public void setLocation(String location) { this.storeLocation = location; }
	    public Set<Employee> getEmployees() { return employees; }
	    public void setEmployees(Set<Employee> employees) { this.employees = employees; }
	    public Set<Customer> getCustomers() { return customers; }
	    public void setCustomers(Set<Customer> customers) { this.customers = customers; }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
