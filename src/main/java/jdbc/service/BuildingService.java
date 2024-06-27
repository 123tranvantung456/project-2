package jdbc.service;

import java.util.List;
import java.util.Map;
import jdbc.model.BuildingDTO;

public interface BuildingService {
	List<BuildingDTO> findAll(Map<String, Object> map, List<String> typeCode);
}
