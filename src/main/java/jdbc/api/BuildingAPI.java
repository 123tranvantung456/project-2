package jdbc.api;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
import jdbc.repository.entity.BuildingEntity;
import jdbc.repository.entity.DistrictEntity;
import jdbc.service.BuildingService;

@RestController
public class BuildingAPI {	
	@Autowired
	private BuildingService buildingService;
	@PersistenceContext
	private EntityManager entityManager;
	@GetMapping(value = "api/test123")
	public List<BuildingDTO> findAll(@RequestParam Map<String, Object> map,
									@RequestParam (name = "typeCode", required = false) List<String> typeCode){
		List<BuildingDTO> results = buildingService.findAll(map, typeCode);
		return results;
	}
	@PostMapping(value = "/api/test123")
	@Transactional
	public void creBuilding (@RequestBody BuildingDTO buildingDTO) {
		BuildingEntity buildingEntity = new BuildingEntity();
		buildingEntity.setName("123");
		DistrictEntity districtEntity = new DistrictEntity();
		districtEntity.setId(buildingDTO.getDistrictId());
		buildingEntity.setDistrict(districtEntity);
		entityManager.persist(buildingEntity);
		System.out.println("ok");
	}
	@PutMapping(value = "/api/test123")
	@Transactional
	public void updateBuilding (@RequestBody BuildingDTO buildingDTO) {
		BuildingEntity buildingEntity = new BuildingEntity();
		buildingEntity.setId(1L);
		buildingEntity.setName(buildingDTO.getName());
		DistrictEntity districtEntity = new DistrictEntity();
		districtEntity.setId(buildingDTO.getDistrictId());
		buildingEntity.setDistrict(districtEntity);
		entityManager.merge(buildingEntity);
		System.out.println("ok");
	}
	@DeleteMapping(value = "api/test123/{Id}")
	@Transactional
	public void delBuilding (@PathVariable Long Id) {
		BuildingEntity buildingEntity = entityManager.find(BuildingEntity.class, Id);
		entityManager.remove(buildingEntity);
		System.out.println("ok");
	}
}
