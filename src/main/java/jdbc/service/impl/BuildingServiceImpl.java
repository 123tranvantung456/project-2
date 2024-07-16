package jdbc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdbc.converter.BuildingDTOConverter;
import jdbc.converter.BuildingEntityConverter;
import jdbc.converter.BuildingSearchBuilderConverter;
import jdbc.model.BuildingDTO;
import jdbc.model.BuildingRequestDTO;
import jdbc.repository.BuildingReponsitory;
import jdbc.repository.entity.BuildingEntity;
import jdbc.service.BuildingService;
@Service
public class BuildingServiceImpl implements BuildingService{
	@Autowired
	private BuildingReponsitory buildingReponsitory;
	@Autowired
	private BuildingDTOConverter buildingDTOConverter;
	@Autowired 
	private BuildingSearchBuilderConverter buildingSearchBuilderConverter;
	@Autowired
	private BuildingEntityConverter buildingEntityConverter;
	@Override
	public List<BuildingDTO> findAll(Map<String, Object> map, List<String> typeCode) {
		List<BuildingEntity> buildingEntities = buildingReponsitory.findAll(buildingSearchBuilderConverter.toBuildingSearchBuilder(map, typeCode));
		List<BuildingDTO> results = new ArrayList<BuildingDTO>();
		for (BuildingEntity buildingEntity : buildingEntities) {
			BuildingDTO buildingDTO = buildingDTOConverter.toBuildingDTO(buildingEntity);
			results.add(buildingDTO);
		}
		return results;
	}

	@Override
	public void create(BuildingRequestDTO buildingRequestDTO) {
		BuildingEntity buildingEntity = buildingEntityConverter.toBuildingEntity(buildingRequestDTO);
		buildingReponsitory.create(buildingEntity);
	}

	@Override
	public void update(BuildingRequestDTO buildingRequestDTO) {
		BuildingEntity buildingEntity = buildingEntityConverter.toBuildingEntity(buildingRequestDTO);
		buildingReponsitory.update(buildingEntity);
	}

	@Override
	public void delete(List<Long> ids) {
		buildingReponsitory.delete(ids);
	}
}
