package in.nareshit.aashish.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "grn_tab")
public class Grn {
	
	@Id
	@GeneratedValue
	@Column(name = "grn_id_col")
	private Integer id;
	
	@Column(name = "grn_code_col")
	private String code;
	@Column(name = "grn_type_col")
	private String type;
	@Column(name = "grn_desc_col")
	private String description;
	
	//integeration// association mapping
	//one Grn is connected to one PurchaseOrder
	//That means 1...1 relation, so we can write
	//1...1 = *...1 + unique condition
	//when we write unique=true, then ManyToOne behaves like OneToOne
	@ManyToOne
	@JoinColumn(name = "po_order_id_fk", unique = true)
	private PurchaseOrder po;  //HAS-A
}
