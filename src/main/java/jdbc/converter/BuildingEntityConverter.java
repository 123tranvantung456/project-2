package jdbc.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jdbc.model.BuildingRequestDTO;
import jdbc.repository.entity.BuildingEntity;

@Component
public class BuildingEntityConverter {
	@Autowired
	private ModelMapper mapper;
	public BuildingEntity toBuildinngEntity (BuildingRequestDTO buildingRequestDTO) {
		BuildingEntity result = mapper.map(buildingRequestDTO, BuildingEntity.class);
		return result;
	}
}
