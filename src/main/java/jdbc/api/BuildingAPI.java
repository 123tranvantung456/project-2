package jdbc.api;

import java.util.List;
import java.util.Map;

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
	@PostMapping(value = "api/test123") 
	public void create (@RequestBody BuildingRequestDTO buildingRequestDTO) {
		buildingService.create(buildingRequestDTO);
	}
	@PutMapping(value = "api/test123")
	public void update (@RequestBody BuildingRequestDTO buildingRequestDTO) {
		buildingService.update(buildingRequestDTO);
	}
	@DeleteMapping(value = "api/test123/{ids}")
	public void delete (@PathVariable List<Long> ids) {
		buildingService.delete(ids);
	}
}
