package jdbc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdbc.converter.BuildingDTOConverter;
import jdbc.converter.BuildingEntityConverter;
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
	private BuildingEntityConverter buildingEntityConverter;
	@Override
	public List<BuildingDTO> findAll(Map<String, Object> map, List<String> typeCode) {
		List<BuildingEntity> buildingEntities = buildingReponsitory.findAll(map, typeCode);
		List<BuildingDTO> results = new ArrayList<BuildingDTO>();
		for (BuildingEntity buildingEntity : buildingEntities) {
			BuildingDTO buildingDTO = buildingDTOConverter.toBuildingDTO(buildingEntity);
			results.add(buildingDTO);
		}
		return results;
	}
	@Override
	public void create(BuildingRequestDTO buildingRequestDTO) {
		BuildingEntity buildingEntity = buildingEntityConverter.toBuildinngEntity(buildingRequestDTO);
		if(buildingReponsitory.create(buildingEntity)) System.out.println("add success");
		else System.out.println("add failed");
	}
	@Override
	public void update(BuildingRequestDTO buildingRequestDTO) {
		BuildingEntity buildingEntity = buildingEntityConverter.toBuildinngEntity(buildingRequestDTO);
		if(buildingReponsitory.update(buildingEntity)) System.out.println("update success");
		else System.out.println("update failed");
	}
	@Override
	public void delete(List<Long> ids) {
		buildingReponsitory.delete(ids);
	}
}
