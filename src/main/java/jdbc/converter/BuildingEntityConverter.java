package jdbc.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jdbc.model.BuildingRequestDTO;
import jdbc.repository.entity.BuildingEntity;
import jdbc.repository.entity.DistrictEntity;
@Component
public class BuildingEntityConverter {
	@Autowired
	private ModelMapper mapper;
	public BuildingEntity toBuildingEntity(BuildingRequestDTO buildingRequestDTO) {
		BuildingEntity result = mapper.map(buildingRequestDTO, BuildingEntity.class);
		DistrictEntity districtEntity = new DistrictEntity();
		districtEntity.setId(buildingRequestDTO.getDistrictid());
		result.setDistrict(districtEntity);
		return result;
	}
}
