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
	    public Long EmployeeId;
	    private String EmployeeFirstName;
	    private String EmployeeLastName;
	    private String EmployeeJobTitle;
	    private String EmployeePhone;
	    @EqualsAndHashCode.Exclude
	    @ToString.Exclude
	    @ManyToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "pet_store_id")
	    private PetStore petStore;
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
	    public PetStore getPetStore() { return petStore; }
	    public void setPetStore(PetStore petStore) { this.petStore = petStore; }

}
