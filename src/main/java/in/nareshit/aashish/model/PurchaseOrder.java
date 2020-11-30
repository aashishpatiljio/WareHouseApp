package in.nareshit.aashish.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "purchase_order_tab")
public class PurchaseOrder {

	@Id
	@Column(name = "po_id_col")
	@GeneratedValue
	private Integer id;
	
	@Column(name = "po_code_col")
	private String orderCode;
	
	@Column(name = "po_ref_num_col")
	private String refNumber;
	
	@Column(name = "po_qlty_chk_col")
	private String qualityCheck;
	
	@Column(name = "po_status_col")
	private String status;
	
	@Column(name = "po_desc_col")
	private String description;
}
