function isEmpty(inputStr) {
	if (inputStr == "" || inputStr == null) {
		return true
	}
	return false
}
function checkForNumeric(strVal){
	strVal = trim(strVal);
	var charpos = strVal.search("[^0-9]");
	if(strVal.length>0 && charpos>=0){
		return false;
	}
	return true;
}
function checkForDecimal(strVal){
	strVal = trim(strVal);
	var charpos = strVal.search("[^0-9.]");
	if(strVal.length>0 && charpos>=0){
		return false;
	}
	return true;
}
function checkForNumericSpace(strVal){
	strVal = trim(strVal);
	var charpos = strVal.search("[^0-9 ]");
	if(strVal.length>0 && charpos>=0){
		return false;
	}
	return true;
}
function trim(strText) {
	if(strText.length>1){
		while (strText.substring(0,1) == ' ')
			strText = strText.substring(1, strText.length);
		while (strText.substring(strText.length-1,strText.length) == ' ')
			strText = strText.substring(0, strText.length-1);
		return strText;
	}else if( strText ==' '){
		return '';
	}else{
		return strText;
	}
}
 function textLimit(field, maxlen) {
	if (field.value.length > maxlen)
	{
		alert('Only '+maxlen+' characters are allowed, your input has been truncated!');
		field.focus();
	}
	if (field.value.length > maxlen)
	field.value = field.value.substring(0, maxlen);
} 

function checkBoxChecked(obj)
{
	if(obj==undefined){
		return false;
	}
	if(obj.length == undefined)
	{
		if (obj.checked == true)
		{
			return true;
		}
	}
	else
	{
		for( i=0; i< obj.length ; i++)
		{
			if (obj[i].checked == true)
			{
				return true;
			}
		}
	}
}
function checkForspl(strVal) {
	strVal = trim(strVal);
	var charpos = strVal.search("[#&+]");
	if(strVal.length>0 && charpos>=0){
		return true;
	}
	return false;
}
function compareDates(fromDate,toDate)
{	
	var sDate1 = fromDate;
	var sDate2 = toDate;
	var returnVal = -1;

	var myRegExp = /^(\d{1,2})\/(\d{1,2})\/(\d{2}|\d{4})$|^(\d{1,2})-(\d{1,2})-(\d{2}|\d{4})$/;
	var matchArray1 = sDate1.match(myRegExp);
	var matchArray2 = sDate2.match(myRegExp);

	var iDay1 = 0;
	var iMonth1 = 0;
	var iYear1 = 0;
	var iDay2 = 0;
	var iMonth2 = 0;
	var iYear2 = 0;

	if ((matchArray1 != null) && (matchArray2 != null))
	{

		if ((matchArray1[4] == null) || (matchArray1[4] == "")) 
		{
			// alert("slashes used");
			iDay1 = parseInt(matchArray1[1], 10);			
			iMonth1 = parseInt(matchArray1[2], 10);
			iYear1 = parseInt(matchArray1[3], 10);
		}
		else if ((matchArray1[1] == null) || (matchArray1[1] == ""))
		{
			// alert("dashes used");
			iDay1 = parseInt(matchArray1[4], 10);
			iMonth1 = parseInt(matchArray1[5], 10);
			iYear1 = parseInt(matchArray1[6], 10);
		}
		if ((matchArray2[4] == null) || (matchArray2[4] == ""))
		{
			// alert("slashes used");
			iDay2 = parseInt(matchArray2[1], 10);
			iMonth2 = parseInt(matchArray2[2], 10);
			iYear2 = parseInt(matchArray2[3], 10);
		}
		else if ((matchArray2[1] == null) || (matchArray2[1] == ""))
		{
			// alert("dashes used");
			iDay2 = parseInt(matchArray2[4], 10);
			iMonth2 = parseInt(matchArray2[5], 10);
			iYear2 = parseInt(matchArray2[6], 10);
		}		
		if (iYear1 < 100) // 2-digit year entered, convert it to 4-digit yr
		{
			iYear1 = 2000+iYear1;
		}
		if (iYear2 < 100) // 2-digit year entered, convert it to 4-digit yr
		{
			iYear2 = 2000+iYear2;
		}
		// First check the year
		if (iYear1 > iYear2)
		{
			returnVal = 1;
		}
		else if (iYear1 < iYear2)
		{
			returnVal = 3;
		}
		else
		{
			// Then the month
			if (iMonth1 > iMonth2)
			{
				returnVal = 1;
			}
			else if (iMonth1 < iMonth2)
			{
				returnVal = 3;
			}
			else
			{
				// Check day last
				if (iDay1 > iDay2)
				{
					returnVal = 1;
				}
				else if (iDay1 < iDay2)
				{
					returnVal = 3;
				}
				else
				{
					returnVal = 2;
				}
			}
		}

	}
	return returnVal;
	}

function checkForAlphabets(strVal)
{
	strVal = trim(strVal);
	var charpos = strVal.search("[^a-zA-Z]");
	if(strVal.length>0 && charpos>=0)
	{
		return false;
	}
	return true;
}
function checkForAlphabetsNumeric(strVal)
{
	strVal = trim(strVal);
	var charpos = strVal.search("[^a-zA-Z0-9 ]");
	if(strVal.length>0 && charpos>=0)
	{
		return false;
	}
	return true;
}
function onLoadFocus(object)
{
	object.focus();
}
function arrayContains(arryA,varbl){
      for(var i=0;i<arryA.length;i++){
            if(arryA[i]===varbl)
             return true;
      }
      return false;
}

function containsAll(arryA,arryB){
	var varblCount = 0;
      for(var i=0;i<arryA.length;i++){
            for(var j=0;j<arryB.length;j++){
                  if(arryA[i]===arryB[j])
                        varblCount++;
            }
      }
  if(varblCount == arryA.length)
	{
		  return true;
	}
      return false;
}

function checkForAlphabetsNumericSymbol(strVal)
{
	strVal = trim(strVal);
	var charpos = strVal.search("[^a-zA-Z0-9& ]");
	if(strVal.length>0 && charpos>=0)
	{
		return false;
	}
	return true;
}

function loading()
{	
	$(document).ready(function() {     
			var id = $('#pleaseWait'); 
			
			//Get the screen height and width
			var maskHeight = $(document).height();
			var maskWidth = $(window).width();

			//Set heigth and width to mask to fill up the whole screen
			$('#mask').css({'width':maskWidth,'height':maskHeight});
			
			//transition effect                           
			$('#mask').fadeIn(300); 
			$('#mask').fadeTo("fast",0.8);    

			//Get the window height and width
			var winH = $(window).height();
			var winW = $(window).width();

			//Set the popup window to center
			$(id).css('top',  winH/2-$(id).height()/2);
			$(id).css('left', winW/2-$(id).width()/2);

			//transition effect
			$(id).fadeIn(1000); 
			$("#pleaseWait").modal();
			$('#mask').show();	
			$('#myjquerymenu').hide();
		 });			
}

function sessionExpired()
{	
	location.href = 'login.dgmt';
}
function download(file)
{
	document.forms[0].action="download.dgmt?filename="+file;
	document.forms[0].method ="post";
	document.forms[0].submit();
}
function forwardToAction(actionName)
{
	document.forms[0].action=actionName;
	document.forms[0].method ="post";
	document.forms[0].submit();
}
function echeck(str) {

	var at = "@";
	var dot = ".";
	var lat = str.indexOf(at);
	var lstr = str.length;
	var ldot = str.indexOf(dot);
	if (str.indexOf(at) == -1) {
		alert("Invalid E-mail ID");
		return false;
	}

	if (str.indexOf(at) == -1 || str.indexOf(at) == 0
			|| str.indexOf(at) == lstr) {
		alert("Invalid E-mail ID");
		return false;
	}

	if (str.indexOf(dot) == -1 || str.indexOf(dot) == 0
			|| str.indexOf(dot) == lstr) {
		alert("Invalid E-mail ID");
		return false;
	}

	if (str.indexOf(at, (lat + 1)) != -1) {
		alert("Invalid E-mail ID");
		return false;
	}

	if (str.substring(lat - 1, lat) == dot
			|| str.substring(lat + 1, lat + 2) == dot) {
		alert("Invalid E-mail ID");
		return false;
	}

	if (str.indexOf(dot, (lat + 2)) == -1) {
		alert("Invalid E-mail ID");
		return false;
	}

	if (str.indexOf(" ") != -1) {
		alert("Invalid E-mail ID");
		return false;
	}
	return true;
}

 function textLimitFCK(field, maxlen) {
	if (field.innerHTML.length > maxlen)
	{
		alert('Only '+maxlen+' characters are allowed, your input has been truncated!');
		field.focus();
	}
	if (field.innerHTML.length > maxlen)
	field.innerHTML = field.innerHTML.substring(0, maxlen);
} 
function isSpclChar(value)
{	
 	var iChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?";
 	for (var i = 0; i < value.length; i++) 
 	{
 		if (iChars.indexOf(value.charAt(i)) != -1)
 		{
 			//alert ("The box has special characters. \nThese are not allowed.\n");
 			return false;
 		}
 	}
 	return true;
}