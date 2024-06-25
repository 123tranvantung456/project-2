package mvcand3layer.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.beans.BuildingDTO;

import mvcand3layer.service.BuildingService;

@RestController
public class BuildingAPI {
	@Autowired
	private BuildingService buildingService;
	@GetMapping(value = "api/test1")
	public List<BuildingDTO> get(@RequestParam (name = "name", required =  false) String name,
			@RequestParam (name = "districtId", required =  false) Integer district,
			@RequestParam (name="typeCode", required = false) List<String> typeCode){
		List<BuildingDTO> buildingDTOs = buildingService.findAll(name, district);
		return buildingDTOs;
	} 
}
 