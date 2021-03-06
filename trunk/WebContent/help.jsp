<%@page import="real_time_scheduling_system.servlets.uploadModelSettings"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Help</title>
<link rel='stylesheet' href='s.css' type='text/css'/>
</head>
<body class="bg" style="background-attachment:fixed;">
<center><div style="padding-top:200px;  ">
<div style="background:url(resources/lap1.png) no-repeat; width:760px; height:500px; border-radius:10px;">
<center> <p class="hh1" style="padding-top:10px;"> Examples </p> </center>
<table style="width:760px; height:200px;">
<tr>
<td width="248" height="296" class="hh1" >
<center>
<p style="font-size:14px;"> Model settings: </p>
<div style="width:380px; height:250px; background-color:#FFF; border-radius:5px; padding-left:10px;">
<p style="font-size:9px; color:#000" align="left"> <br>
&lt;settings&gt; <br>
&nbsp;&lt;minimumTaskTime&gt;0&lt;/minimumTaskTime&gt;
&nbsp;&lt;maximumTaskTime&gt;10&lt;/maximumTaskTime&gt;
&nbsp;&lt;inputTaskFlowType&gt;1&lt;/inputTaskFlowType&gt;
&nbsp;&lt;taskLoadingTimeInterval&gt;100&lt;/taskLoadingTimeInterval&gt;
&nbsp;&lt;taskLoadingCountBorder&gt;10.0&lt;/taskLoadingCountBorder&gt;
&nbsp;&lt;schedulingAlgorithmType&gt;1&lt;/schedulingAlgorithmType&gt;
&nbsp;&lt;modelingTime&gt;100.0&lt;/modelingTime&gt;
&nbsp;&lt;normalDistributionM&gt;10.0&lt;/normalDistributionM&gt;
&nbsp;&lt;normalDistributionSigma&gt;10.0&lt;/normalDistributionSigma&gt;
&nbsp;&lt;minProcessor&gt;1.0&lt;/minProcessor&gt;
&nbsp;&lt;maxProcessor&gt;10.0&lt;/maxProcessor&gt;
&nbsp;&lt;minMemmoryCapacity&gt;10.0&lt;/minMemmoryCapacity&gt;
&nbsp;&lt;maxMemmoryCapacity&gt;1000.0&lt;/maxMemmoryCapacity&gt;
&nbsp;&lt;minWorkTimePercentage&gt;10.0&lt;/minWorkTimePercentage&gt;
&nbsp;&lt;maxWorkTimePercentage&gt;100.0&lt;/maxWorkTimePercentage&gt;
&nbsp;&lt;minNewTaskSpeed&gt;10.0&lt;/minNewTaskSpeed&gt;
&nbsp;&lt;maxNewTaskSpeed&gt;100.0&lt;/maxNewTaskSpeed&gt;
&nbsp;&lt;minAccessLevel&gt;1&lt;/minAccessLevel&gt;
&nbsp;&lt;maxAccessLevel&gt;10&lt;/maxAccessLevel&gt;<br>
&lt;/settings&gt;
</p>
</div>
</center>
</td>
<td width="10"></td>

<td width="248" class="hh1" >
<center>
<p style="font-size:14px;"> Machine settings: </p>
<div style="width:310px; height:250px; background-color:#FFF; border-radius:5px; padding-left:10px;">
<p style="font-size:9px; color:#000" align="left"> <br>
&lt;settings&gt; <br>
&nbsp;    &lt;conf id="0"&gt; <br>
&nbsp;&nbsp;        &lt;id&gt;0&lt;/id&gt; <br>
&nbsp;&nbsp;        &lt;memmoryCapacity&gt;10&lt;/memmoryCapacity&gt; <br>
&nbsp;&nbsp;		&lt;processors&gt; <br>
&nbsp;&nbsp;&nbsp;		    &lt;proc&gt;5&lt;/proc&gt; <br>
&nbsp;&nbsp;&nbsp;			&lt;proc&gt;10&lt;/proc&gt; <br>
&nbsp;&nbsp;&nbsp;			&lt;proc&gt;15&lt;/proc&gt; <br>
&nbsp;&nbsp;		&lt;/processors&gt; <br>
&nbsp;&nbsp;		&lt;accessLevel&gt;0&lt;/accessLevel&gt; <br>
&nbsp;	&lt;/conf&gt; <br>
&lt;/settings&gt;
</p>
</div>
</center>
</td>
</tr>
</table>
</div>
</div>
<br><a class="links" href="<%=uploadModelSettings.DEFAULT_MODEL_FILE %>"> Model settings file </a> <br>
<br><a class="links" href="<%=uploadModelSettings.DEFAULT_MACHINE_FILE %>"> Machine settings file </a> <br>
</body>
</html>