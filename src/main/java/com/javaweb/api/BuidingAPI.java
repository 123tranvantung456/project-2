package com.javaweb.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.beans.BuildingDTO;
import com.javaweb.beans.ErrorResponseDTO;
import com.javaweb.customException.FieldRequiredException;

@RestController
public class BuidingAPI {
	@GetMapping(value = "/api/building/")
	@ResponseBody // de chuyen doi sang json va tra ra cho fe
	public BuildingDTO testAPI(@RequestParam (value = "name", required = false) String name,
						  @RequestParam (value = "number", required = false) Integer number,
						  @RequestParam (value = "ward", required = false) String ward
							) {
		BuildingDTO buildingDTO = new BuildingDTO();
		buildingDTO.setName(name);
		buildingDTO.setNumber(number);
		buildingDTO.setWard(ward);
		return buildingDTO;
	}
//	@RequestMapping(value = "/api/building/", method = RequestMethod.POST)
//	public void getBuilding(@RequestParam Map<String, String> params) {
//		System.out.println("ok");
//	}
//	@RequestMapping(value = "api/building/", method = RequestMethod.POST)
//	public void getBuilding1(@RequestBody Map<String, String> params) {
//		System.out.println("ok");
//	}
	@RequestMapping(value = "api/building/", method = RequestMethod.POST)
	public void getBuilding1(@RequestBody BuildingDTO buildingDTO) { // key trong api/building/ phai trung voi thuoc tinh cua Building
		System.out.println("okkk");
		System.out.println(buildingDTO.toString());
	}
	@DeleteMapping (value = "api/building/{id}/{name}") // {id} => bat buoc phai co id truyen len tu client 
	public void deleteBuilding(@PathVariable Integer id, @PathVariable String name) {
		System.out.println("da xoa toa nha co id la " +id);
	}
	@GetMapping (value = "api/")
	public Object get(){
		List<BuildingDTO> list = new ArrayList<BuildingDTO>();
		BuildingDTO b1 = new BuildingDTO();
		b1.setName("123");
		b1.setNumber(1);
		b1.setWard("xyz");
		BuildingDTO b2 = new BuildingDTO();
		b2.setName("456");
		b2.setNumber(2);
		b2.setWard("abc");
		list.add(b1); list.add(b2);
		try {
			int x = 5 / 0; 
			System.err.println(x);
		} catch (Exception e) {
			ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
			errorResponseDTO.setError(e.getMessage());
			List<String> details = new ArrayList<String>();
			details.add("loi chia cho 0");
			details.add("loiiiiiiiiiiiii");
			errorResponseDTO.setDetail(details);
			return errorResponseDTO;
		}
		return list;
	}
	@PostMapping (value = "api/error")
	public Object get(@RequestBody BuildingDTO buildingDTO) {
		try {
			validate(buildingDTO);
		} catch (Exception e) {
			ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
			errorResponseDTO.setError(e.getMessage());
			List<String> details = new ArrayList<String>();
			details.add("cac truong khong duoc thieu");
			errorResponseDTO.setDetail(details);
			return errorResponseDTO;
		}
		return null;
	}
	public void validate(BuildingDTO buildingDTO){
		if (buildingDTO.getName() == null || buildingDTO.getName() == "" || buildingDTO.getNumber() == null) throw new FieldRequiredException("LOI");
	}
} 
   