package real_time_scheduling_system.servlets;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import real_time_scheduling_system.experiment.ExperimentNumberSingleton;
import real_time_scheduling_system.experiment.IExperiment;
import real_time_scheduling_system.experiment.SystemExperementManager;
import real_time_scheduling_system.experiment.SystemExperementResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class uploadModelSettings
 */
@WebServlet("/uploadModelSettings")
public class uploadModelSettings extends HttpServlet {
	public static final String STORE_FOLDER="C:\\test\\";
	public static final String GRAPHIC_EXTENSION=".jpeg";
	public static final String FILE_EXTENSION=".xml";
	public static final String MODEL_SETTINGS_FILE_NAME="modelingSettings";
	public static final String MACHINE_SETTINGS_FILE_NAME="machineSettings";
	public static final String EXPERIMENTS_LIST="experimentsList";
	public static final String SESSION_PARAMETER_ERROR="ERROR";
	public static final String SESSION_PARAMETER_EXPERIMENT_RESULTS="EXPERIMENT_RESULTS";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uploadModelSettings() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error="";
		List<IExperiment.ExperimentTypes> experimentTypes=new ArrayList<>();
		String modelSettingsFilePath=null;
		String machineSettingsFilePath=null;
		int requestId=ExperimentNumberSingleton.getNumber();
		try {
	        List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
	       
	        for (FileItem item : items) {
	            if (!item.isFormField()) {
	                String fieldName = item.getFieldName();
	                if(fieldName!=null && fieldName.equals(MODEL_SETTINGS_FILE_NAME)){
	                	try {
	                		modelSettingsFilePath=STORE_FOLDER+MODEL_SETTINGS_FILE_NAME+requestId+FILE_EXTENSION;
							System.out.println("Filepath="+modelSettingsFilePath);
	                		Util.uploadFile(modelSettingsFilePath, item.getInputStream());
						} catch (Exception e) {
							error+="Incorect model settings file;";
						}
	                }
	                if(fieldName!=null && fieldName.equals(MACHINE_SETTINGS_FILE_NAME)){
	                	try {
	                		machineSettingsFilePath=STORE_FOLDER+MACHINE_SETTINGS_FILE_NAME+requestId+FILE_EXTENSION;
							Util.uploadFile(machineSettingsFilePath, item.getInputStream());
						} catch (Exception e) {
							error+=" Incorect machine settings file;";
						}
	                }
	            }else{
	            	String fieldName=item.getFieldName();
	            	if(fieldName!=null && fieldName.length()>EXPERIMENTS_LIST.length()){
	            		String experimentTypeNumberTxt=fieldName.substring(EXPERIMENTS_LIST.length(),fieldName.length());
	            		int experimentNumber=-1;
	            		try{
	            			experimentNumber=Integer.valueOf(experimentTypeNumberTxt);
	            		}catch(Exception e){
	            			continue;
	            		}
	            		if(experimentNumber>=0 && experimentNumber<IExperiment.ExperimentTypes.values().length){
	            			experimentTypes.add(IExperiment.ExperimentTypes.values()[experimentNumber]);
	            		}
	            	}
	            	System.out.println("Field"+item.getFieldName());
	            }
	        }
	    } catch (FileUploadException e) {
	    	 error+=" Incorect request parameters";
	    }
		
		if(experimentTypes==null || experimentTypes.size()==0){
			error+=" Select at least one type of experiment;";
		}
		SystemExperementResult experementResult=null;
		if(error==null || error.length()==0){
			try{
				experementResult=SystemExperementManager.makeExperements(machineSettingsFilePath, modelSettingsFilePath, experimentTypes, STORE_FOLDER, GRAPHIC_EXTENSION);
			}catch (Exception e) {
				error+=" Incorect files;";
			}
		}
		HttpSession session=request.getSession(true);
		session.setAttribute(SESSION_PARAMETER_ERROR, error);
		if(error!=null && error.length()!=0){
			response.sendRedirect("modelingSettings.jsp");
		}else{
			session.setAttribute(SESSION_PARAMETER_EXPERIMENT_RESULTS,experementResult);
			response.sendRedirect("modelingResult.jsp");
		}
	}
		
}
