package jdbc.converter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import jdbc.Builder.BuildingSearchBuilder;
import jdbc.util.MapUtil;

@Component
public class BuildingSearchBuilderConverter {
	public BuildingSearchBuilder toBuildingSearchBuilder (Map<String, Object> params, List<String> typeCode) {
		BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
														.setName(MapUtil.getObject(params, "name", String.class))
														.setAreaFrom(MapUtil.getObject(params, "areaFrom", Long.class))
														.setAreaTo(MapUtil.getObject(params, "areaTo", Long.class))
														.setDistrictId(MapUtil.getObject(params, "districtId", Long.class))
														.setFloorArea(MapUtil.getObject(params, "floorArea", Long.class))
														.setManagerName(MapUtil.getObject(params, "managerName", String.class))
														.setManagerPhoneNumber(MapUtil.getObject(params, "managerPhone", String.class))
														.setNumberOfBasement(MapUtil.getObject(params, "numberOfBasement", Integer.class))
														.setPriceFrom(MapUtil.getObject(params, "priceFrom", Long.class))
														.setPriceTo(MapUtil.getObject(params, "priceTo", Long.class))
														.setStaffId(MapUtil.getObject(params, "staffId", Long.class))
														.setStreet(MapUtil.getObject(params, "street", String.class))
														.setTypeCode(typeCode)
														.setWard(MapUtil.getObject(params, "ward", String.class))
														.setWard(null)
														.build();	
		return buildingSearchBuilder;
	}
}
