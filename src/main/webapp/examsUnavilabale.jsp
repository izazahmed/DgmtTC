<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script>

var menu = 'examinationWindow';
</script>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">

<div id="breadcrumb"><strong>Home</strong> &gt; Examinations </div>
<div id="wrapper" class="clearfix"> 

<%@include file="/candiateExaminationsLeftMenu.jsp"%>

<DIV id="maincol"><DIV class="fleft" style="WIDTH: 100%">


<TABLE cellSpacing="0" cellPadding="0" width="100%" border="0">
<TBODY><TR>

<TD vAlign="top" align="left"> 
<TABLE id="sortTable" cellSpacing="1" cellPadding="0" width="100%" border="0">
<THEAD><TR>
<TH class="mainHeader" colSpan="4">Examinations</TH>
</TR>
<tr>

<td>
<font color="red"><s:actionerror/></font>	  		
</td>
		</tr>
</THEAD>

 

<TBODY id="offTblBdy">

<input type="hidden" name="examId" value="<%=request.getParameter("examId")%>"/>
<input type="hidden" name="exampaperId"/>
<s:hidden name="prockerKey" />

</TBODY>  
        
  
</TBODY></TABLE>
</TD></TR></TBODY></TABLE>
</DIV></DIV>
</div>
</form>
</body>
</html>
 