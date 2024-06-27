package jdbc.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jdbc.model.BuildingDTO;
import jdbc.repository.DistrictRepository;
import jdbc.repository.RentAreaRepository;
import jdbc.repository.entity.BuildingEntity;
import jdbc.repository.entity.DistrictEntity;
import jdbc.repository.entity.RentAreaEntity;

@Component
public class BuildingDTOConverter {
	
	@Autowired
	private DistrictRepository districtRepository;
	@Autowired
	private RentAreaRepository rentAreaRepository;
	@Autowired
	private ModelMapper modelMapper;
	public BuildingDTO toBuildingDTO (BuildingEntity buildingEntity) {
		BuildingDTO result = modelMapper.map(buildingEntity, BuildingDTO.class);
		long districtId = buildingEntity.getDistrictid();
		DistrictEntity districtEntity = districtRepository.findById(districtId); 
		result.setAddress(buildingEntity.getStreet() + buildingEntity.getWard() + buildingEntity.getDistrictid() + districtEntity.getName());
		long Id = buildingEntity.getId();
		List<RentAreaEntity> rentAreaRepositories = rentAreaRepository.findByBuildingId(Id);
		String rentArea = rentAreaRepositories.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(", "));
		result.setRentArea(rentArea);
		
//		result.setBrokerageFee(buildingEntity.getBrokeragefee());
//		result.setEmptyArea(null);
//		result.setFloorArea(buildingEntity.getFloorArea());
//		result.setManagerName(null);
//		result.setName(buildingEntity.getName());
//		result.setRentPrice(buildingEntity.getRentprice());
//		result.setServiceFee(buildingEntity.getServicefee());
		return result; 
	}
}
