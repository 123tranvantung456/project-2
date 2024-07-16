package jdbc.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jdbc.model.BuildingDTO;
import jdbc.repository.BuildingReponsitory;
import jdbc.repository.entity.BuildingEntity;
import jdbc.repository.entity.DistrictEntity;
import jdbc.service.BuildingService;
@RestController
public class Test_BuildingAPI {	
	@Autowired
	private BuildingService buildingService;
	@Autowired
	private BuildingReponsitory buildingReponsitory;  
	@GetMapping(value = "api/test")
	public List<BuildingDTO> findAll(@RequestParam Map<String, Object> map,
									@RequestParam (name = "typeCode", required = false) List<String> typeCode){
		List<BuildingDTO> results = buildingService.findAll(map, typeCode);
		return results;
	}
//	@GetMapping(value = "api/test/{id}")
//	public BuildingDTO getBuildingById(@PathVariable Long id){
//		BuildingDTO result = new BuildingDTO();
//		BuildingEntity buildingEntity = buildingReponsitory.findById(id).get();
//		return result;
//	}
	@PostMapping(value = "/api/test")
	@Transactional
	public void creBuilding (@RequestBody BuildingDTO buildingDTO) {
		BuildingEntity buildingEntity = new BuildingEntity();
		buildingEntity.setName("123");
		DistrictEntity districtEntity = new DistrictEntity();
		districtEntity.setId(buildingDTO.getDistrictId());
		buildingEntity.setDistrict(districtEntity);	
		buildingReponsitory.save(buildingEntity);
		System.out.println("ok");
	}
	@PutMapping(value = "/api/test")
	@Transactional
	public void updateBuilding (@RequestBody BuildingDTO buildingDTO) {
		BuildingEntity buildingEntity = buildingReponsitory.findById(buildingDTO.getId()).get();
		buildingEntity.setName(buildingDTO.getName());
		DistrictEntity districtEntity = new DistrictEntity();
		districtEntity.setId(buildingDTO.getDistrictId());
		buildingEntity.setDistrict(districtEntity);
		buildingReponsitory.save(buildingEntity); 
		System.out.println("ok");
	}
	// xoa 1
//	@DeleteMapping(value = "api/test/{id}")
//	public void delBuilding (@PathVariable Long id) {
//		buildingReponsitory.deleteById(id);
//	}
	
	// xoa nhieu
//	@DeleteMapping(value = "api/test/{ids}")
//	@Transactional
//	public void delBuilding (@PathVariable List<Long> ids) {
//		buildingReponsitory.deleteByIdIn(ids);
//	}
	
//	@GetMapping(value = "api/test/{name}")
//	@Transactional
//	public List<BuildingDTO> delBuilding (@PathVariable String name) {
//		List<BuildingEntity> buildingEntities = buildingReponsitory.findByNameContaining(name);
//		List<BuildingDTO> buildingDTOs = new ArrayList<BuildingDTO>();
//		return buildingDTOs; 
//	}
	
	@GetMapping(value = "api/test/{name}/{street}")
	@Transactional
	public List<BuildingDTO> delBuilding (@PathVariable String name, @PathVariable String street) {
		List<BuildingEntity> buildingEntities = buildingReponsitory.findByNameContainingAndStreet(name, street);
		List<BuildingDTO> buildingDTOs = new ArrayList<BuildingDTO>();
		return buildingDTOs; 
	}
	
}
