<%@page import="real_time_scheduling_system.experiment.IExperiment"%>
<%@page import="real_time_scheduling_system.servlets.uploadModelSettings"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Set model settings</title>
<link rel='stylesheet' href='s.css' type='text/css'/>
</head>
<body class="bg" style="background-attachment:fixed;">
<center><div style="padding-top:200px;  ">
<div style="background:url(resources/lap1.png) no-repeat; width:760px; height:376px; border-radius:10px;">
<table style="width:426px;">
<tr>
<td class="hh1" >
<center>
<p style="padding-top:10px; color:#FFF; font-size:24px;"> Set model Setting </p></center>
<%
	HttpSession httpSession=request.getSession(true);
	String error=(String)httpSession.getAttribute(uploadModelSettings.SESSION_PARAMETER_ERROR);
	System.out.println("JSP Error="+error);
	if(error!=null && error.length()!=0){
%>
<p> <%=error %> </p>
	<%} %>
<form action="uploadModelSettings" method="post" enctype="multipart/form-data">
    <p style="color:#FFF; font-size:16px;"> Select Model Setting File:</p> 
    <center><input type="file" name="<%=uploadModelSettings.MODEL_SETTINGS_FILE_NAME %>" /></center>
    <p style="color:#FFF; font-size:16px;"> Select Machine Setting File: </p>
    <center><input type="file" name="<%=uploadModelSettings.MACHINE_SETTINGS_FILE_NAME %>" /></center>
    <p style="color:#FFF; font-size:16px;"> Select experiment types:</p>
    <div style="font-size:10px;">
       		<% for(int i=0;i<IExperiment.ExperimentTypes.values().length;i++){ %>
       			<br><input type="checkbox" name="<%=uploadModelSettings.EXPERIMENTS_LIST+i %>"> <%=IExperiment.ExperimentTypes.values()[i].name %>
			<%} %>
            
        </div>    
    <br><center><input type="submit" value="Start modeling" /></center>
</form>
</td>
</tr>
</table>

</div>
</div>
</center>
</body>
</html>