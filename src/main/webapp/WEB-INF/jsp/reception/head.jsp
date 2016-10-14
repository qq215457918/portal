<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="navbar navbar-fixed-top scroll-hide">
  <div class="container-fluid top-bar">
    <div class="pull-right">
	  <ul class="nav navbar-nav pull-right">
		<li class="dropdown notifications hidden-xs">
		  <a class="dropdown-toggle" data-toggle="dropdown" href="#">
		  	<span aria-hidden="true" class="se7en-flag"></span>
			<div class="sr-only">Notifications</div>
			<p class="counter">4</p>
		  </a>
		  <ul class="dropdown-menu">
			<li>
			  <a href="#">
				<div class="notifications label label-info">New</div>
				<p>New user added: Jane Smith</p>
			  </a>
			</li>
			<li>
			  <a href="#">
				<div class="notifications label label-info">New</div>
				<p>Sales targets available</p>
			  </a>
			</li>
			<li>
			  <a href="#">
				<div class="notifications label label-info">New</div>
				<p>New performance metric added</p>
			  </a>
			</li>
			<li>
			  <a href="#">
				<div class="notifications label label-info">New</div>
				<p>New growth data available</p>
			  </a>
			</li>
		  </ul>
		</li>
		<li class="dropdown messages hidden-xs">
		  <a class="dropdown-toggle" data-toggle="dropdown" href="#">
		  	<span aria-hidden="true" class="se7en-envelope"></span>
				<div class="sr-only">Messages</div>
				<p class="counter">3</p>
		  </a>
		  <ul class="dropdown-menu messages">
			<li>
			  <a href="#">
			  	<img width="34" height="34" src="${basePath}resources/images/avatar-male2.png" />Could we meet today? I wanted...
			  </a>
			</li>
			<li>
			  <a href="#">
				<img width="34" height="34" src="${basePath}resources/images/avatar-female.png" />Important data needs your analysis...
			  </a>
			</li>
			<li>
			  <a href="#">
			    <img width="34" height="34" src="${basePath}resources/images/avatar-male2.png" />Buy Se7en today, it's a great theme...
			  </a>
			</li>
		  </ul>
		</li>
		<li class="dropdown settings hidden-xs">
		  <a class="dropdown-toggle" data-toggle="dropdown" href="#">
		  	<span aria-hidden="true" class="se7en-gear"></span>
			<div class="sr-only">Settings</div>
		  </a>
		  <ul class="dropdown-menu">
			<li>
			  <a class="settings-link blue" href="javascript:chooseStyle('none', 30)"><span></span>Blue</a>
			</li>
			<li>
			  <a class="settings-link green" href="javascript:chooseStyle('green-theme', 30)"><span></span>Green</a>
			</li>
			<li>
			  <a class="settings-link orange" href="javascript:chooseStyle('orange-theme', 30)"><span></span>Orange</a>
			</li>
			<li>
			  <a class="settings-link magenta" href="javascript:chooseStyle('magenta-theme', 30)"><span></span>Magenta</a>
			</li>
			<li>
				<a class="settings-link gray" href="javascript:chooseStyle('gray-theme', 30)"><span></span>Gray</a>
			</li>
		  </ul>
		</li>
		<li class="dropdown user hidden-xs">
		  <a data-toggle="dropdown" class="dropdown-toggle" href="#">
		  	<img width="34" height="34" src="${basePath}resources/images/avatar-male.jpg" />John Smith<b class="caret"></b>
		  </a>
			<ul class="dropdown-menu">
			  <li><a href="#"><i class="icon-user"></i>My Account</a></li>
			  <li><a href="#"> <i class="icon-gear"></i>Account Settings</a></li>
			  <li><a href="login1.html"> <i class="icon-signout"></i>Logout</a></li>
			</ul>
		</li>
	  </ul>
	</div>
	<button class="navbar-toggle">
	  <span class="icon-bar"></span>
	  <span class="icon-bar"></span>
	  <span class="icon-bar"></span>
	</button>
	<!-- 系统LOGO -->
	<a class="logo" href="index-2.html">se7en</a>
	<!-- 系统全站查询 -->
	<!-- <form class="navbar-form form-inline col-lg-2 hidden-xs">
	  <input class="form-control" placeholder="Search" type="text">
	</form> -->
  </div>
  <div class="container-fluid main-nav clearfix">
	<div class="nav-collapse">
	  <ul class="nav">
		<li>
		  <a class="current" href="index-2.html"><span aria-hidden="true" class="se7en-home"></span>Dashboard</a>
		</li>
		<li>
		  <a href="social.html"> <span aria-hidden="true" class="se7en-feed"></span>Social Feed</a>
		</li>
		<li class="dropdown">
		  <a data-toggle="dropdown" href="#">
		  	<span aria-hidden="true" class="se7en-star"></span>Features<b class="caret"></b>
		  </a>
		  <ul class="dropdown-menu">
			<li><a href="buttons.html">Buttons</a></li>
			<li><a href="fontawesome.html">Font Awesome Icons</a></li>
			<li>
			  <a href="glyphicons.html"> 
			  	<span class="notifications label label-warning">New</span>
			  	<p>Glyphicons</p>
			  </a>
			</li>
			<li><a href="components.html">Components</a></li>
			<li><a href="widgets.html">Widgets</a></li>
			<li><a href="typo.html">Typography</a></li>
			<li><a href="grid.html">Grid Layout</a></li>
		  </ul>
		</li>
		<li class="dropdown">
		  <a data-toggle="dropdown" href="#"> 
		  	<span aria-hidden="true" class="se7en-forms"></span>Forms<b class="caret"></b>
		  </a>
		  <ul class="dropdown-menu">
			<li>
			  <a href="form-components.html"> 
				<span class="notifications label label-warning">New</span>
				<p>Form Components</p>
			  </a>
			</li>
			<li><a href="form-advanced.html">Advanced Forms</a></li>
		  </ul>
		</li>
		<li class="dropdown">
		  <a data-toggle="dropdown" href="#"> 
		  	<span aria-hidden="true" class="se7en-tables"></span>Tables<b class="caret"></b></a>
			  <ul class="dropdown-menu">
				<li><a href="tables.html">Basic tables</a></li>
				<li><a href="datatables.html">DataTables</a></li>
				<li>
				  <a href="datatables-editable.html">
					<div class="notifications label label-warning">New</div>
					<p>Editable DataTables</p>
				  </a>
				</li>
			  </ul>
		</li>
		<li>
		  <a href="charts.html"> <span aria-hidden="true" class="se7en-charts"></span>Charts</a>
		</li>
		<li class="dropdown">
		  <a data-toggle="dropdown" href="#">
		  	<span aria-hidden="true" class="se7en-pages"></span>Pages<b class="caret"></b>
		  </a>
		  <ul class="dropdown-menu">
			<li>
			  <a href="chat.html">
			  	<span class="notifications label label-warning">New</span>
			  	<p>Chat</p>
			  </a>
			</li>
			<li><a href="calendar.html">Calendar</a></li>
			<li>
			  <a href="timeline.html">
			  	<span class="notifications label label-warning">New</span>
				<p>Timeline</p>
			  </a>
			</li>
			<li>
			  <a href="login1.html"> 
			    <span class="notifications label label-warning">New</span>
				<p>Login 1</p>
			  </a>
			</li>
			<li><a href="login2.html">Login 2</a></li>
			<li>
			  <a href="signup1.html"> 
			  	<span class="notifications label label-warning">New</span>
				<p>Sign Up 1</p>
			  </a>
			</li>
			<li><a href="signup2.html">Sign Up 2</a></li>
			<li>
			  <a href="invoice.html"> 
			  	<span class="notifications label label-warning">New</span>
				<p>Invoice</p>
			  </a>
			</li>
			<li>
			  <a href="faq.html"> 
			  	<span class="notifications label label-warning">New</span>
				<p>FAQ</p>
			  </a>
			</li>
			<li><a href="filters.html">Filter Results</a></li>
			<li><a href="404-page.html">404 Page</a></li>
		  </ul>
		</li>
		<li>
		  <a href="gallery.html"><span aria-hidden="true" class="se7en-gallery"></span>Gallery</a>
		</li>
	  </ul>
	</div>
  </div>
</div>