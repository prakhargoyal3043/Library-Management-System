package project.capstone.Mapper;

import project.capstone.DTO.RenteeLogDTO;
import project.capstone.EntityModels.RenteeLog;

public class RenteeLogMapper {
	
	public static RenteeLogDTO mapToRenteeLogDTO(RenteeLog renteeLog)
	{
		return new RenteeLogDTO(
				renteeLog.getRentId(),
				renteeLog.getUserId(),
				renteeLog.getBookId(),
				renteeLog.getIssueDate(),
				renteeLog.getDueDate(),
				renteeLog.getTotalAmount(),
				renteeLog.getAmountPaid(),
				renteeLog.getReturnDate()
				);
	}
	
	public static RenteeLog mapToRenteeLog(RenteeLogDTO renteeLogDTO)
	{
		return new RenteeLog(
				renteeLogDTO.getRentId(),
				renteeLogDTO.getUserId(),
				renteeLogDTO.getBookId(),
				renteeLogDTO.getIssueDate(),
				renteeLogDTO.getDueDate(),
				renteeLogDTO.getTotalAmount(),
				renteeLogDTO.getAmountPaid(),
				renteeLogDTO.getReturnDate()
				);
	}
}
