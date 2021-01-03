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
@Table(name = "so_dtl_tab")
public class SaleDtl {
	
	@Id
	@GeneratedValue
	@Column(name = "so_dtl_id_col")
	private Integer id;
	
	@Column(name = "so_dtl_qty_col")
	private Integer quantity;
	
	//integration// Asscociation Mapping
	//Many details can be connected to one part
	//HAS-A // *...1
	//PurchaseDtl-----<>Part
	@ManyToOne
	@JoinColumn(name = "part_id_fk_col")
	private Part part;   //HAS-A
	
	//Many details can be connected to one Sale Order
	@ManyToOne
	@JoinColumn(name = "order_id_fk_col")
	private SaleOrder order;
}
