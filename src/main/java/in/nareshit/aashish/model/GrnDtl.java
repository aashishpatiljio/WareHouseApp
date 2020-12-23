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
@Table(name = "grn_dtl_tab")
public class GrnDtl {  
	
	@Id
	@GeneratedValue
	@Column(name = "grn_dtl_id_col")
	private Integer id; 
	
	@Column(name = "grn_dtl_code_col")
	private String itemCode;
	@Column(name = "grn_dtl_cost_col")
	private Double baseCost;
	@Column(name = "grn_dtl_qty_col")
	private Integer qty;
	@Column(name = "grn_dtl_itemval_col")
	private Double itemVal;
	
	@Column(name = "grn_dtl_status_col")
	private String status;
	
	//Integration// Association mapping
	//Many GrnDtl are connected to one Grn
	//Linking GrnDtl's with Grn
	@ManyToOne
	@JoinColumn(name = "grn_id_fk_col")
	private Grn grn;		//HAS-A	
	
}
