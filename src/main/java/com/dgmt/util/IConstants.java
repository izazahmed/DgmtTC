package com.dgmt.util;

public interface IConstants {
	
	public enum Status
	{
		Active("1"), Inactive("0");

		private final String code;

		private Status(String code)
		{
			this.code = code;
		}

		public String code()
		{
			return code;
		}
	}
	
	public enum UserManagement
	{
		ADMIN("ADMIN"), QBADMIN("QBADMIN"), CANDIDATE("CANDIDATE"), CANDIDATEEXAM("CANDIDATEEXAM"), CONTRIBUTOR("CONTRIBUTOR");

		private final String code;

		private UserManagement(String code)
		{
			this.code = code;
		}

		public String code()
		{
			return code;
		}
	}
	
	public enum ExamPaperStatus
	{
		PUBLISH("2"), UNPUBLISH("0"), INCOMPLETE("1"); 
		
		private final String code;

		private ExamPaperStatus(String code)
		{
			this.code = code;
		}

		public String code()
		{
			return code;
		}
	}
	
	public enum OfficerComd
	{
		NC("NC"), SC("SC"), WC("WC"), EC("EC"), SWC("SWC"), CC("CC"), ARTRAC("ARTRAC"); 
		
		private final String code;

		private OfficerComd(String code)
		{
			this.code = code;
		}

		public String code()
		{
			return code;
		}
	}
	
	public enum OfficerRank
	{
		LT("Lt"), CAPT("Capt"), MAJ("Maj"), LTCOL("Lt Col"); 
		
		private final String code;

		private OfficerRank(String code)
		{
			this.code = code;
		}

		public String code()
		{
			return code;
		}
	}
	
	public enum CommissionType
	{
		PC("PC"), SS("SS"); 
		
		private final String code;

		private CommissionType(String code)
		{
			this.code = code;
		}

		public String code()
		{
			return code;
		}
	}

	public enum StatusCode
	{
		ONGOING("5", "Ongoing"), SUBMITTED("6", "Submitted"), PASS("7","Pass"), FAIL("8","Fail"), ABSENT("9","Absent"),SUSPENDED("10","Susspended"), MANUAL_SUBMIT("11", "Manual Submitted"); 
		
		private final String code;
		private final String status;
		private StatusCode(String code,String status)
		{
			this.code = code;
			this.status = status;
		}

		public String code()
		{
			return code;
		}
		public String status()
		{
			return status;
		}
	}
}
