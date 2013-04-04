<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"/>
<script type="text/javascript">
jQuery(document).ready(function() {
  jQuery("div.content").hide();
  jQuery("div.card").click(function()
  {
    jQuery(this).next("div.content").slideToggle(500);
  });
});
</script>
