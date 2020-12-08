package in.nareshit.aashish.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "po_dtl_tab")
public class PurchaseDtl {
	
	@Id
	@GeneratedValue
	@Column(name = "po_dtl_id_col")
	private Integer id;
	
	@Column(name = "po_dtl_qty_col")
	private Integer quantity;
	
	//integration// Asscociation Mapping
	//Many details can be connected to one part
	//HAS-A // *...1
	//PurchaseDtl-----<>Part
	@ManyToOne
	@JoinColumn(name = "part_id_fk_col")
	private Part part;   //HAS-A

}
