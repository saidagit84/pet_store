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
	    private Long id;
	    private String employeeName; // Assuming this is the name of the employee
	    private String employeeEmail; // Assuming this is the email of the employee
	    @EqualsAndHashCode.Exclude
	    @ToString.Exclude
	    @ManyToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "pet_store_id")
	    private PetStore petStore;

}
