<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<script type="text/javascript">
jQuery(document).ready(function() {

  // load random background..
  var images = ['http://www.wallm.com/images/2013/01/fall-backgrounds-high-definition-high.jpg',
  	 'http://www.wallcg.com/images/2013/02/desktop-sports-surfing-early-morning-background.jpg',
  	 'http://www.hddesktopbackgrounds.us/backgrounds-images/1920x1080/2iyzb-wallpapers-173812926-1920x1080.jpg',
  	 'http://www.hddesktopbackgrounds.us/backgrounds-images/1920x1080/psp4i-wallpapers-19586376-1920x1080.jpg'];
  \$('html').css({'background-image': 'url(' + images[Math.floor(Math.random() * images.length)] + ')'});

//  jQuery("div.content").hide();
  jQuery("a.subscribe").click(function()
  {
    jQuery("div.subscribe").dialog({
      height: 360,
      width: 540,
      modal: true,
      show: 'puff',
      hide: 'puff',
      open: function(){
            jQuery('.ui-widget-overlay').bind('click',function(){
                jQuery("div.subscribe").dialog('close');
            })
        }
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
      show: 'puff',
      hide: 'puff',
      open: function(){
            jQuery('.ui-widget-overlay').bind('click',function(){
                jQuery('#' + articleId).dialog('destroy');
            })
        },
      close: function() {
            jQuery('#' + articleId).dialog('destroy');
        }
    });
  });
});
</script>
