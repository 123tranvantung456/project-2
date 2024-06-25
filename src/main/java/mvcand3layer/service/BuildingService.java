package mvcand3layer.service;

import java.util.List;

import com.javaweb.beans.BuildingDTO;

public interface BuildingService {
	List<BuildingDTO> findAll(String name, Integer district);
}
