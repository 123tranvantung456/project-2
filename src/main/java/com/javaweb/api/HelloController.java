package com.javaweb.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.beans.BuildingDTO;
import com.javaweb.customException.FieldRequiredException;

@RestController
public class HelloController {

	@GetMapping("/")
	public Object home() {
//		try {
//			System.out.println(5/0);
//		} catch (Exception e) {
//			ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
//			errorResponseDTO.setError(e.getMessage());
//			List<String> details = new ArrayList<String>();
//			details.add("loi chia cho 0");
//			errorResponseDTO.setDetail(details);
//			return errorResponseDTO;
//		}
		System.out.println(5/0);
		return null;
	}
	
	@PostMapping(value = "/")
	public Object home1(@RequestBody BuildingDTO buildingDTO) {
		validate(buildingDTO);
		return null;
	}
	public void validate(BuildingDTO buildingDTO) {
		if (buildingDTO.getName() == null || buildingDTO.getName() == "" || buildingDTO.getNumber() == null) throw new FieldRequiredException("LOI");
	}
}
