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
@Table(name = "sale_order_tab")
public class SaleOrder {
	
	@Id
	@GeneratedValue
	@Column(name = "so_id_col")
	private Integer id;
	
	@Column(name = "so_code_col")
	private String orderCode;
	
	@Column(name = "so_ref_num_col")
	private String refNumber;
	
	@Column(name = "so_stock_mode_col")
	private String stockMode;
	
	@Column(name = "so_stock_source_col")
	private String stockSource;
	
	@Column(name = "so_status_col")
	private String status;
	
	@Column(name = "so_desc_col")
	private String description;
	
	//Module Integrations //Assosciation mappings
	@ManyToOne
	@JoinColumn(name = "ship_id_fk_col")
	private ShipmentType shipmentType;		//HAS-A
	
	@ManyToOne
	@JoinColumn(name = "wh_user_customer_id_fk_col")
	private WhUserType customer;						//HAS-A
	
}
