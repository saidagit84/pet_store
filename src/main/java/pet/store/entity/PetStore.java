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
	    private Long id;
	    private String storeName; // Assuming this is the name of the pet store
	    private String storeLocation; // Assuming this is the location of the pet store
	    @EqualsAndHashCode.Exclude
	    @ToString.Exclude
	    @ManyToMany(cascade = CascadeType.PERSIST)
	    @JoinTable(name = "pet_store_customer",
	            joinColumns = @JoinColumn(name = "pet_store_id"),
	            inverseJoinColumns = @JoinColumn(name = "customer_id"))
	    private Set<Customer> customers;
	    @EqualsAndHashCode.Exclude
	    @ToString.Exclude
	    @OneToMany(mappedBy = "petStore", cascade = CascadeType.ALL, orphanRemoval = true)
	    private Set<Employee> employees;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
