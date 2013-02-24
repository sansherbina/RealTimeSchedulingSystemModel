<%@page import="real_time_scheduling_system.experiment.IExperiment"%>
<%@page import="real_time_scheduling_system.servlets.uploadModelSettings"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Set model settings</title>
</head>
<body>
<p> Set model Setting </p>
<%
	String error=(String)session.getAttribute(uploadModelSettings.SESSION_PARAMETER_ERROR);
	if(error!=null && error.length()!=0){
%>
<p> <%=error %> </p>
	<%} %>
<form action="uploadModelSettings" method="post" enctype="multipart/form-data">
    Select Model Setting File <input type="file" name="<%=uploadModelSettings.MODEL_SETTINGS_FILE_NAME %>" /> <br />
    Select Machine Setting File <input type="file" name="<%=uploadModelSettings.MACHINE_SETTINGS_FILE_NAME %>" /><br />
    Select experement types 
    	<select size="<%=IExperiment.ExperimentTypes.values().length %>" name="<%=uploadModelSettings.EXPERIMENTS_LIST %>" multiple="multiple">
			<% for(int i=0;i<IExperiment.ExperimentTypes.values().length;i++){ %>
				<option><%=IExperiment.ExperimentTypes.values()[i].name %></option>
			<%} %>
		</select>
    <input type="submit" value="Start modeling" />
</form>
</body>
</html>