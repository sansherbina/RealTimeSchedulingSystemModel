package real_time_scheduling_system.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import real_time_scheduling_system.experiment.ExperementBuilder;
import real_time_scheduling_system.experiment.ExperimentNumberSingleton;
import real_time_scheduling_system.experiment.IExperiment;
import real_time_scheduling_system.experiment.SystemExperementManager;
import real_time_scheduling_system.experiment.SystemExperementResult;

/**
 * Servlet implementation class uploadModelSettings
 */
@WebServlet("/uploadModelSettings")
public class uploadModelSettings extends HttpServlet {
	public static final String TEST_PREFIX="C:\\test\\";
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
	                		modelSettingsFilePath=TEST_PREFIX+MODEL_SETTINGS_FILE_NAME+requestId+FILE_EXTENSION;
							Util.uploadFile(modelSettingsFilePath, item.getInputStream());
						} catch (Exception e) {
							error+="Incorect model settings file;";
						}
	                }
	                if(fieldName!=null && fieldName.equals(MACHINE_SETTINGS_FILE_NAME)){
	                	try {
	                		machineSettingsFilePath=TEST_PREFIX+MACHINE_SETTINGS_FILE_NAME+requestId+FILE_EXTENSION;
							Util.uploadFile(machineSettingsFilePath, item.getInputStream());
						} catch (Exception e) {
							error+=" Incorect machine settings file;";
						}
	                }
	            }
	        }
	    } catch (FileUploadException e) {
	    	 error+=" Incorect request parameters";
	    }
		String[] experimentTypesNames=request.getParameterValues(EXPERIMENTS_LIST);
		List<IExperiment.ExperimentTypes> experimentTypes=new ArrayList<>();
		if(experimentTypesNames==null || experimentTypesNames.length==0){
			error+=" Select at least one type of experiment;";
		}else{
			for(int i=0;i<experimentTypesNames.length;i++){
				experimentTypes.add(IExperiment.ExperimentTypes.valueOf(experimentTypesNames[i]));
			}
		}
		
		SystemExperementResult experementResult=null;
		if(error==null || error.length()==0){
			try{
				experementResult=SystemExperementManager.makeExperements(machineSettingsFilePath, modelSettingsFilePath, experimentTypes);
			}catch (Exception e) {
				error+=" Incorect files;";
			}
		}
		if(error!=null && error.length()!=0){
			response.sendRedirect("modelingSettings.jsp");
		}else{
			HttpSession session=request.getSession(true);
			session.setAttribute(SESSION_PARAMETER_EXPERIMENT_RESULTS,experementResult);
			response.sendRedirect("modelingResult.jsp");
		}
	}
		
}