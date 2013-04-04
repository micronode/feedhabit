<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
jQuery(document).ready(function() {
  jQuery("div.content").hide();
  jQuery("div.card").click(function()
  {
    jQuery(this).children("div.content").slideToggle(500);
  });
});
</script>
