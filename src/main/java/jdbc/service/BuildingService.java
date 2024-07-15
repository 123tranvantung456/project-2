package jdbc.service;

import java.util.List;
import java.util.Map;
import jdbc.model.BuildingDTO;
import jdbc.model.BuildingRequestDTO;

public interface BuildingService {
	List<BuildingDTO> findAll(Map<String, Object> map, List<String> typeCode);
	public void create (BuildingRequestDTO buildingRequestDTO);
	public void update (BuildingRequestDTO buildingRequestDTO);
	public void delete (List<Long> ids);
}
