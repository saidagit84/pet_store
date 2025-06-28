package pet.store.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

@Entity
@Data

public class Customer {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    public Long CustomerId;
	    private String CustomerFirstName;
	    private String CustomerLastName;
	    private String CustomerEmail;
	    @EqualsAndHashCode.Exclude
	    @ToString.Exclude
	    @ManyToMany(mappedBy = "customers", cascade = CascadeType.PERSIST)
	    private Set<PetStore> petStores;
	    public Long getCustomerId() { return CustomerId; }
	    public void setCustomerId(Long id) { this.CustomerId = id; }
	    public String getCustomerFirstName() { return CustomerFirstName; }
	    public void setCustomerFirstName(String CustomerFirstName) { this.CustomerFirstName = CustomerFirstName; }
	    public String getCustomerLastName() { return CustomerLastName; }
	    public void setCustomerLastName(String CustomerLastName) { this.CustomerLastName = CustomerLastName; }
	    public String getCustomerEmail() { return CustomerEmail; }
	    public void setCustomerEmail(String CustomerEmail) { this.CustomerEmail = CustomerEmail; }
	    public Set<PetStore> getPetStores() { return petStores; }
	    public void setPetStores(Set<PetStore> petStores) { this.petStores = petStores; }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  
	}

}
