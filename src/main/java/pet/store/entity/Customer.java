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
	    public Long id;
	    public String customerName; 
	    public String customerEmail; 
	    @EqualsAndHashCode.Exclude
	    @ToString.Exclude
	    @ManyToMany(mappedBy = "customers", cascade = CascadeType.PERSIST)
	    private Set<PetStore> petStores;


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  
	}

}
