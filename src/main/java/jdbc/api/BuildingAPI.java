package jdbc.api;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jdbc.model.BuildingDTO;
import jdbc.service.BuildingService;

@RestController
public class BuildingAPI {	
	@Autowired
	private BuildingService buildingService;
	@GetMapping(value = "api/test123")
	public List<BuildingDTO> findAll(@RequestParam Map<String, Object> map,
									@RequestParam (name = "typeCode", required = false) List<String> typeCode){
		List<BuildingDTO> results = buildingService.findAll(map, typeCode);
		return results;
	}
}
