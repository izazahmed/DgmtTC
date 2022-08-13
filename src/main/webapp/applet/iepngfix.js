/*----------------------------- How to use -----------------------------
IE6 doesn't display transparent PNG correctly.
To fix this issue in your HTML page:

   1. For each <img> tag that should be fixed set the class="png_transparent".

   2. Define the following statements:
	<!--[if lte IE 6]>
	<script type="text/javascript" src="iepngfix.js"></script>
	<script type="text/javascript">
 		pngFixInit("blank.gif");
	</script>
   	<![endif]-->
      
      Don't forget to specify correct location
      for the files "iepngfix.js" and "blank.gif".
*/

function pngFixInit(blank_gif) {
    var oldonload = window.onload;
    var func = function() { fixAllPngImages(blank_gif); }
    if (typeof window.onload != 'function') {
	window.onload = func; 
    } else {
	window.onload = function() {
	    if (oldonload) oldonload();
	    func();
	};
    }
}

function fixAllPngImages(blank_gif) {
    var root = document;
    for (var i = root.all.length - 1, obj = null; (obj = root.all[i]); i--) {
	if (obj.className=='png_transparent') {
	    fixPngImage(obj,blank_gif);
	}
    }
}

function fixPngImage(img,blank_gif) {
    var src = img.src;
    img.style.width  = img.width  + "px";
    img.style.height = img.height + "px";
    img.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+src+"', sizingMethod='scale')";
    img.src = blank_gif;
}
