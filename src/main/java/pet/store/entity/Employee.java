package pet.store.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Employee {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    public Long id;
	    public String employeeName; 
	    public String employeeEmail; 
	    @EqualsAndHashCode.Exclude
	    @ToString.Exclude
	    @ManyToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "pet_store_id")
	    private PetStore petStore;

}
