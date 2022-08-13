function getCandExamination(examId)
{
	document.header.action = "examinationMenu.dgmt";
	document.header.examType.value = examId;
	document.header.method = "post";
	document.header.submit();  
}


