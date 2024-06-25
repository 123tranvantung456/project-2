package mvcand3layer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.beans.BuildingDTO;

import mvcand3layer.reponsitory.BuildingRepository;
import mvcand3layer.reponsitory.entity.BuildingEntity;
import mvcand3layer.service.BuildingService;
@Service
public class BuildingServiceIplm implements BuildingService{
	@Autowired
	private BuildingRepository buildingRepository;
	@Override
	public List<BuildingDTO> findAll(String name, Integer district) {
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(name, district);
		List<BuildingDTO> buildingDTOs = new ArrayList<BuildingDTO>();
		for (BuildingEntity item : buildingEntities) {
			BuildingDTO buildingDTO = new BuildingDTO();
			buildingDTO.setName(item.getName());
			buildingDTO.setNumber(item.getNumber());
			buildingDTO.setWard("phuong + " +item.getWard());
			buildingDTOs.add(buildingDTO);
		}
		return buildingDTOs;
	}
}
