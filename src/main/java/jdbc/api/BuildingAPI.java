package jdbc.api;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jdbc.model.BuildingDTO;
import jdbc.model.BuildingRequestDTO;
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
	@PostMapping(value = "/api/test123")
	@Transactional
	public void creBuilding (@RequestBody BuildingRequestDTO buildingRequestDTO) {
		buildingService.create(buildingRequestDTO);
	}
	@PutMapping(value = "/api/test123")
	@Transactional
	public void updateBuilding (@RequestBody BuildingRequestDTO buildingRequestDTO) {
		buildingService.update(buildingRequestDTO);
	}
	@DeleteMapping(value = "api/test123/{ids}")
	@Transactional
	public void delBuilding (@PathVariable List<Long> ids) {
		buildingService.delete(ids);
	}
}
