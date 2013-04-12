<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.2/jquery-ui.min.js"></script>
<script type="text/javascript">
jQuery(document).ready(function() {
//  jQuery("div.content").hide();
  jQuery("a.showSummary").click(function()
  {
//    jQuery("div.content").hide();
//    jQuery("div.card").height(70)
//    jQuery(this).height(220)
//    jQuery(this).children("div.content").show("fast");
    jQuery(this).parent().children("div.content").dialog({
      height: 360,
      width: 540,
      modal: true
    });
  });
});
</script>
