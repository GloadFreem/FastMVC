
<!-- /.aside -->
<section id="content">
	<section class="hbox stretch">
		<!-- .aside -->
		<aside class="aside-xl b-l b-r" id="note-list">
			<section class="vbox flex">
				<header class="header clearfix">
					<span class="pull-right m-t">
						<button class="btn btn-dark btn-sm btn-icon" id="new-note"
							data-toggle="tooltip" data-placement="right" title="New">
							<i class="fa fa-plus"></i>
						</button>
					</span>
					<p class="h3">Notebook</p>
					<div class="input-group m-t-sm m-b-sm">
						<span class="input-group-addon input-sm"><i
							class="fa fa-search"></i></span> <input type="text"
							class="form-control input-sm" id="search-note"
							placeholder="search">
					</div>
				</header>
				<section>
					<section>
						<section>
							<div class="padder">
								<!-- note list -->
								<ul id="note-items" class="list-group list-group-sp">
								</ul>
								<!-- templates -->
								<script type="text/template" id="item-template"> <div class="view" id="note-"> <button class="destroy close hover-action">&times;</button> <div class="note-name"> <strong></span> </div> </script>
								<!-- / template -->
								<!-- note list -->
								<p class="text-center">&nbsp;</p>
							</div>
						</section>
					</section>
				</section>
			</section>
		</aside>
		<!-- /.aside -->
		<aside id="note-detail">
			<script type="text/template" id="note-template"> <section class="vbox"> <header class="header bg-light lter bg-gradient b-b"> <p id="note-date">Created on </p> </header> <section class="bg-light lter"> <section class="hbox stretch"> <aside> <section class="vbox b-b"> <section class="paper"> <textarea type="text" class="form-control scrollable" placeholder="Type your note here"></textarea> </section> </section> </aside> </section> </section> </section> </script>
		</aside>
	</section>
	<a href="#" class="hide nav-off-screen-block"
		data-toggle="class:nav-off-screen" data-target="#nav"></a>
</section>

<!-- Bootstrap --> <!-- App --> <script src="js/libs/underscore-min.js" cache="false"></script><script src="js/libs/backbone-min.js" cache="false"></script><script src="js/libs/backbone.localStorage-min.js" cache="false"></script> <script src="js/libs/moment.min.js" cache="false"></script><!-- Notes --><script src="js/apps/notes.js" cache="false"></script>