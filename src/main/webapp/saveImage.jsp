<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,com.isavvix.tools.*,java.util.Hashtable" %>
<%

String questionNo = request.getParameter("questionNo");
String questionPaperId = request.getParameter("questionPaperId");
String currentQuestionNo = request.getParameter("currentQuestionNo");
String answer = request.getParameter("answer"); 
String action = request.getParameter("action");  
String subjTimeSlotId = request.getParameter("subjTimeSlotId");
String subjectCodeId = request.getParameter("subjectCodeId");
String message = request.getParameter("message");
String timeLeftInSecs = request.getParameter("timeLeftInSecs");
String imageName = request.getParameter("imageName");
String imageDir = application.getRealPath("/")+"/DGMTImages/";
String picture                = imageName;
String imgSplit[] = imageName.split("\\.");
String annotation             = imgSplit[0]+"-pic.xml";
String annotation_image       = imgSplit[0]+"-pic.png";
String picture_and_annotation = imgSplit[0]+"-cmt.jpg";

String url="";
if (action.equalsIgnoreCase("displayQuestion")){
	url ="getCandidateQuestion.dgmt?questionNo="+questionNo+"&action="+action+"&questionPaperId="+questionPaperId+"&subjTimeSlotId="+subjTimeSlotId+"&subjectCodeId="+subjectCodeId+"&currentQuestionNo="+currentQuestionNo+"&message=minTime&timeLeftInSecs="+timeLeftInSecs+"&imageName="+imageName+"&answer="+answer; 
} else {
	url ="getCandidateQuestion.dgmt?questionNo="+questionNo+"&action="+action+"&questionPaperId="+questionPaperId+"&subjTimeSlotId="+subjTimeSlotId+"&subjectCodeId="+subjectCodeId+"&message=minTime&timeLeftInSecs="+timeLeftInSecs+"&imageName="+imageName+"&answer="+answer; 
}


HttpMultiPartParser parser = new HttpMultiPartParser();    
String boundary = parser.getBoundary(request.getContentType());
Hashtable data  = parser.parseData(request.getInputStream(),boundary,null);

String save_fields[][] = {
    { "picture"                , picture },
    { "picture_and_annotation" , picture_and_annotation },
    { "annotation"             , annotation },
    { "annotation_image"       , annotation_image },
};

for(int i=0; i < save_fields.length; i++) {
    String fieldName = save_fields[i][0];
    Object uploadField = data.get(fieldName);
    if (uploadField != null) {
	FileInfo fileInfo = (FileInfo)uploadField;
	String filename = save_fields[i][1];
	try {
		File f = new File(imageDir,filename);
		String canonicalPath = f.getCanonicalPath();
	    OutputStream os = null;
	    os = new FileOutputStream(canonicalPath);
    	    try {
    	    	if(!canonicalPath.equals(imageDir+filename)){
    	    		
    	    		request.getSession().setAttribute("statusval", "invalidData");
    	    		
    	    	}else{
					os.write(fileInfo.getFileContents());
    	    	}
    	    }
	    finally {
		os.close();
    	    }
	}
	catch (IOException ioex) { %>
	  #SHOWERROR <%= ioex.getMessage() %>  
	  <%
	    return;
	}	
  }
} %>
