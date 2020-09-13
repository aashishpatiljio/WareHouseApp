package in.nareshit.aashish.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "order_method_tab")
public class OrderMethod {
	
	@Id
	@GeneratedValue
	@Column(name = "order_id_col")
	private Integer id;
	@Column(name = "order_mode_col")
	private String mode;
	@Column(name = "order_code_col")
	private String code;
	@Column(name = "order_method_col")
	private String method;
	@ElementCollection
	@CollectionTable(name = "order_accept_tab", joinColumns = @JoinColumn(name="order_id_col"))
	@Column(name = "order_accept_col")
	private List<String> accept;
	@Column(name = "order_descr_col")
	private String descr;
}
