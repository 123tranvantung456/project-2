package jdbc.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jdbc.model.BuildingDTO;
import jdbc.repository.entity.BuildingEntity;
import jdbc.repository.entity.RentAreaEntity;

@Component
public class BuildingDTOConverter {
	@Autowired
	private ModelMapper modelMapper;
	public BuildingDTO toBuildingDTO (BuildingEntity buildingEntity) {
		BuildingDTO result = modelMapper.map(buildingEntity, BuildingDTO.class);
		result.setAddress(buildingEntity.getStreet() + buildingEntity.getWard() + buildingEntity.getDistrict().getName());
		List<RentAreaEntity> rentAreas = buildingEntity.getRentAreaEntities();
		String rentArea = rentAreas.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(", "));
		result.setRentArea(rentArea);
		return result; 
	}
}   
