$(document).ready(function (){
							
	$("#next").click(function(){	
	 $("#q1").hide("fast");
	$("#q2").show("slow");
						  
	  });
	
	$("#previous").click(function(){	
	 $("#q2").hide("fast");
	$("#q1").show("slow");
	  });
	
	});



/*  code for modal jquery window ------------------------------------------------ */

$(document).ready(function() {                                                                                                  

    //select all the a tag with name equal to modal
    $('a[name=modal]').click(function(e) {
                    //Cancel the link behavior
                    e.preventDefault();
                    //$('#myjquerymenu').hide();                       

                                    
                    //Get the A tag
                    var id = $(this).attr('href');
                    
    
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
    });

    
    //if close button is clicked
    $('.window .close').click(function (e) {
                    //Cancel the link behavior
                    e.preventDefault();
                    $('#myjquerymenu').show();
                    $('#mask').hide();
                    $('.window').hide();
    });    
    
    
    
    //select all the a tag with name equal to modal
    $('.pop').click(function(e) {
                    //Cancel the link behavior
                    e.preventDefault();
                    //$('#myjquerymenu').hide();
                                    
                    //Get the A tag
                    var id = $(this).attr('id');
                    
    
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
    
    });
    
    //if close button is clicked
    $('.window .close').click(function (e) {
                    $('#myjquerymenu').show();
                    //Cancel the link behavior
                    e.preventDefault();
                    $('#mask').hide();
                    $('.window').hide();
    });                        
    
              
    
    
});

	
	
	

						  

	//select all the a tag with name equal to modal
	$('.pop').click(function(e) {
		//Cancel the link behavior
		e.preventDefault();
		//$('#myjquerymenu').hide();
			
		//Get the A tag
		var id = $(this).attr('id');
		
	
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
	
	});
	
	//if close button is clicked
	$('.window .close').click(function (e) {
		$('#myjquerymenu').show();
		//Cancel the link behavior
		e.preventDefault();
		$('#mask').hide();
		$('.window').hide();
	});		
	
	
	
/*  --------------------------------------------------------------------------------  */

function leftcol(){
	document.getElementById('maincol').style.width='95%';
	
}

/*$(document).ready(function (){
	$("#slidebutton").click(function(){	
	$("#leftcol").slideToggle("slow");
	
						  
	  });
	});*/

$(function() {
		$("#slidebutton").toggle(
			function() {
				$("#leftcol").animate({marginLeft:'-20%'}, 1000);
				$("#maincol").fadeTo("fast", 0.4);
				$("#maincol").animate({marginLeft:'60%'},400);	
				$("#maincol").fadeTo("fast",0.2);
				$("#maincol").fadeTo("fast",1);
				$("#maincol").animate({marginLeft:'20px'}, 300);
				
			},
			function() {
				$("#leftcol").animate({marginLeft:'0%',float:'left'}, 1000);
				$("#maincol").fadeTo("fast",0.5);
				$("#maincol").animate({width:'78%',float:'left'}, 500);
				$("#maincol").fadeTo("fast",1);
			}
		);
		
		$("#searchbutton").css({cursor:'hand'});
	$("#searchbutton").click(function () {
	$("#search").show("fast");
    });
	
	$("#resetbutton").css({cursor:'hand'});
	$("#resetbutton").click(function () {
	$("#search").hide("fast");
	$("#summary").show("fast");	
    });
		
	});

/*  --------------------------------------------------------------------------------  */
/*  editor      */

 $(function()
  {
      $('#wysiwyg').wysiwyg();	 
	  
	  
  });
 

/*  --------------------------------------------------------------------------------  */

<!-- function for input on focus for IE -->

sfFocus = function() {
                var sfEls = document.getElementsByTagName("INPUT");
                for (var i=0; i<sfEls.length; i++) {
								if(sfEls[i].onfocus == null)
								{
									sfEls[i].onfocus=function() 
									{
										this.className+=" sffocus";
									}
								}
								if(sfEls[i].onblur == null)
								{
									sfEls[i].onblur=function() 
									{
											this.className=this.className.replace(new RegExp(" sffocus\\b"), "");
									}
								}
                }
}
if (window.attachEvent) window.attachEvent("onload", sfFocus);

/*  --------------------------------------------------------------------------------  */

/*    move selected options       */
<!--

var NS4 = (navigator.appName == "Netscape" && parseInt(navigator.appVersion) < 5);

function addOption(theSel, theText, theValue)
{
  var newOpt = new Option(theText, theValue);
  var selLength = theSel.length;
  theSel.options[selLength] = newOpt;
}

function deleteOption(theSel, theIndex)
{ 
  var selLength = theSel.length;
  if(selLength>0)
  {
    theSel.options[theIndex] = null;
  }
}

function moveOptions(theSelFrom, theSelTo)
{
  
  var selLength = theSelFrom.length;
  var selectedText = new Array();
  var selectedValues = new Array();
  var selectedCount = 0;
  
  var i;
  
  // Find the selected Options in reverse order
  // and delete them from the 'from' Select.
  for(i=selLength-1; i>=0; i--)
  {
    if(theSelFrom.options[i].selected)
    {
      selectedText[selectedCount] = theSelFrom.options[i].text;
      selectedValues[selectedCount] = theSelFrom.options[i].value;
      deleteOption(theSelFrom, i);
      selectedCount++;
    }
  }
  
  // Add the selected text/values in reverse order.
  // This will add the Options to the 'to' Select
  // in the same order as they were in the 'from' Select.
  for(i=selectedCount-1; i>=0; i--)
  {
    addOption(theSelTo, selectedText[i], selectedValues[i]);
  }
  
  if(NS4) history.go(0);
}

//-->

var imge1="common/images/plus.png";
var imge2="common/images/minus.png";
function tree(menu,images)

{
if(menu.style.display=='none')
{menu.style.display='block';
images.src=imge2;
}
else
{
menu.style.display='none';
images.src=imge1;
}

}



