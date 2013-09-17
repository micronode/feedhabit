<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<script type="text/javascript">
jQuery(document).ready(function() {
//  jQuery("div.content").hide();
  jQuery("a.subscribe").click(function()
  {
    jQuery("div.subscribe").dialog({
      height: 360,
      width: 540,
      modal: true
    });
  });

  jQuery("a.showSummary").click(function()
  {
//    jQuery("div.content").hide();
//    jQuery("div.card").height(70)
//    jQuery(this).height(220)
//    jQuery(this).children("div.content").show("fast");
    var articleId = jQuery(this).parent().children("div.content").attr('id');
    jQuery(this).parent().children("div.content").load('summary.groovy', {p:articleId}).dialog({
      height: 360,
      width: 540,
      modal: true,
      open: function(){
            jQuery('.ui-widget-overlay').bind('click',function(){
                jQuery('#' + articleId).dialog('close');
            })
        }
    });
  });
});
</script>
