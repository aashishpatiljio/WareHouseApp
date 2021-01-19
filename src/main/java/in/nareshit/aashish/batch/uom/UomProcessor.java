/*
package in.nareshit.aashish.batch.uom;

import org.springframework.batch.item.ItemProcessor;

import in.nareshit.aashish.model.Uom;

public class UomProcessor implements ItemProcessor<Uom, Uom> {

	@Override
	public Uom process(Uom uom) throws Exception {

		String type = uom.getUomType();
		if (type == null || "".equals(type.trim())) {
			uom.setUomType("NA");
		} else {
			uom.setUomType(type.toUpperCase());
		}

		String desc = uom.getDescription();
		if (desc == null || "".equals(desc.trim())) {
			uom.setDescription("FROM BATCH PROCESSING");
		}

		String model = uom.getUomModel();
		uom.setUomModel(model.toUpperCase());

		return uom;
	}

}
*/