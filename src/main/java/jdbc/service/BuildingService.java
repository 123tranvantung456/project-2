package jdbc.service;

import java.util.List;
import java.util.Map;
import jdbc.model.BuildingDTO;
import jdbc.model.BuildingRequestDTO;

public interface BuildingService {
	List<BuildingDTO> findAll(Map<String, Object> map, List<String> typeCode);
	void create(BuildingRequestDTO buildingRequestDTO);
	void update(BuildingRequestDTO buildingRequestDTO);
	void delete(List<Long> ids); 
}
