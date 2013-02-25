<%@page import="real_time_scheduling_system.experiment.IExperiment"%>
<%@page import="real_time_scheduling_system.servlets.uploadModelSettings"%>
<%@page import="real_time_scheduling_system.experiment.SystemExperementResult"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modeling result</title>
</head>
<body>
<%
	HttpSession httpSession=request.getSession(true);
	SystemExperementResult experementResult=(SystemExperementResult)httpSession.getAttribute(uploadModelSettings.SESSION_PARAMETER_EXPERIMENT_RESULTS);
	if(experementResult==null){
%>
<br> <p> Error inside modeling system</p>
<%}
	Iterator<IExperiment.ExperimentTypes> iterator=experementResult.getGraphicFiles().keySet().iterator();
	while(iterator.hasNext()){
		IExperiment.ExperimentTypes experimentType=iterator.next();
		String filePath=experementResult.getGraphicFiles().get(experimentType);
%>
<br><p> Graphic <%=experimentType.name %> </p>
<img src="<%=filePath %>" alt="<%=experimentType.name %>">
<%} %>
</body>
</html>