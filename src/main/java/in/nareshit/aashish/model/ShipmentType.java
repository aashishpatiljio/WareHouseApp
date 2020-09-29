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
@Table(name = "shipment_type_tab")
public class ShipmentType {

	@Id
	@GeneratedValue
	@Column(name = "shipment_id_col")
	private Integer id;	
	@Column(name = "shipment_mode_col")
	private String shipmentMode;
	@Column(name = "shipment_code_col")
	private String shipmentCode;
	@Column(name = "shipment_enableshp_col")
	private String enableShipment;
	@Column(name = "shipment_grade_col")
	private String shipmentGrade;
	@Column(name = "shipment_description_col")
	private String description;

}
