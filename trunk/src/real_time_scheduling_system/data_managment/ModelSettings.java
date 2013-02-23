package real_time_scheduling_system.data_managment;

public class ModelSettings {
	private int minimumTaskTime;
	private int maximumTaskTime;
	private int inputTaskFlowType;
	private float taskLoadingTimeInterval;
	private float taskLoadingCountBorder;
	private int schedulingAlgorithmType;
	private float modelingTime;
	private double normalDistributionM;
	private double normalDistributionSigma;
	private double minProcessor;
	private double maxProcessor;
	private double minMemmoryCapacity;
	private double maxMemmoryCapacity;
	private float minWorkTimePercentage;
	private float maxWorkTimePercentage;
	private double minNewTaskSpeed;
	private double maxNewTaskSpeed;
	private int minAccessLevel;
	private int maxAccessLevel;
	
	public ModelSettings(int minimumTaskTime, int maximumTaskTime,
			int inputTaskFlowType, 
			float taskLoadingTimeInterval, float taskLoadingCountBorder,
			int schedulingAlgorithmType, float modelingTime,
			double normalDistributionM, double normalDistributionSigma,
			double minProcessor, double maxProcessor,
			double minMemmoryCapacity, double maxMemmoryCapacity,
			float minWorkTimePercentage, float maxWorkTimePercentage,
			double minNewTaskSpeed, double maxNewTaskSpeed, int minAccessLevel,
			int maxAccessLevel) {
		super();
		this.minimumTaskTime = minimumTaskTime;
		this.maximumTaskTime = maximumTaskTime;
		this.inputTaskFlowType = inputTaskFlowType;
		this.taskLoadingTimeInterval = taskLoadingTimeInterval;
		this.taskLoadingCountBorder = taskLoadingCountBorder;
		this.schedulingAlgorithmType = schedulingAlgorithmType;
		this.modelingTime = modelingTime;
		this.normalDistributionM = normalDistributionM;
		this.normalDistributionSigma = normalDistributionSigma;
		this.minProcessor = minProcessor;
		this.maxProcessor = maxProcessor;
		this.minMemmoryCapacity = minMemmoryCapacity;
		this.maxMemmoryCapacity = maxMemmoryCapacity;
		this.minWorkTimePercentage = minWorkTimePercentage;
		this.maxWorkTimePercentage = maxWorkTimePercentage;
		this.minNewTaskSpeed = minNewTaskSpeed;
		this.maxNewTaskSpeed = maxNewTaskSpeed;
		this.minAccessLevel = minAccessLevel;
		this.maxAccessLevel = maxAccessLevel;
	}

	public int getMinimumTaskTime() {
		return minimumTaskTime;
	}

	public void setMinimumTaskTime(int minimumTaskTime) {
		this.minimumTaskTime = minimumTaskTime;
	}

	public int getMaximumTaskTime() {
		return maximumTaskTime;
	}

	public void setMaximumTaskTime(int maximumTaskTime) {
		this.maximumTaskTime = maximumTaskTime;
	}

	public int getInputTaskFlowType() {
		return inputTaskFlowType;
	}

	public void setInputTaskFlowType(int inputTaskFlowType) {
		this.inputTaskFlowType = inputTaskFlowType;
	}

	public float getTaskLoadingTimeInterval() {
		return taskLoadingTimeInterval;
	}

	public void setTaskLoadingTimeInterval(float taskLoadingTimeInterval) {
		this.taskLoadingTimeInterval = taskLoadingTimeInterval;
	}

	public float getTaskLoadingCountBorder() {
		return taskLoadingCountBorder;
	}

	public void setTaskLoadingCountBorder(float taskLoadingCountBorder) {
		this.taskLoadingCountBorder = taskLoadingCountBorder;
	}

	public int getSchedulingAlgorithmType() {
		return schedulingAlgorithmType;
	}

	public void setSchedulingAlgorithmType(int schedulingAlgorithmType) {
		this.schedulingAlgorithmType = schedulingAlgorithmType;
	}

	public float getModelingTime() {
		return modelingTime;
	}

	public void setModelingTime(float modelingTime) {
		this.modelingTime = modelingTime;
	}

	public double getNormalDistributionM() {
		return normalDistributionM;
	}

	public void setNormalDistributionM(double normalDistributionM) {
		this.normalDistributionM = normalDistributionM;
	}

	public double getNormalDistributionSigma() {
		return normalDistributionSigma;
	}

	public void setNormalDistributionSigma(double normalDistributionSigma) {
		this.normalDistributionSigma = normalDistributionSigma;
	}

	public double getMinProcessor() {
		return minProcessor;
	}

	public void setMinProcessor(double minProcessor) {
		this.minProcessor = minProcessor;
	}

	public double getMaxProcessor() {
		return maxProcessor;
	}

	public void setMaxProcessor(double maxProcessor) {
		this.maxProcessor = maxProcessor;
	}

	public double getMinMemmoryCapacity() {
		return minMemmoryCapacity;
	}

	public void setMinMemmoryCapacity(double minMemmoryCapacity) {
		this.minMemmoryCapacity = minMemmoryCapacity;
	}

	public double getMaxMemmoryCapacity() {
		return maxMemmoryCapacity;
	}

	public void setMaxMemmoryCapacity(double maxMemmoryCapacity) {
		this.maxMemmoryCapacity = maxMemmoryCapacity;
	}

	public float getMinWorkTimePercentage() {
		return minWorkTimePercentage;
	}

	public void setMinWorkTimePercentage(float minWorkTimePercentage) {
		this.minWorkTimePercentage = minWorkTimePercentage;
	}

	public float getMaxWorkTimePercentage() {
		return maxWorkTimePercentage;
	}

	public void setMaxWorkTimePercentage(float maxWorkTimePercentage) {
		this.maxWorkTimePercentage = maxWorkTimePercentage;
	}

	public double getMinNewTaskSpeed() {
		return minNewTaskSpeed;
	}

	public void setMinNewTaskSpeed(double minNewTaskSpeed) {
		this.minNewTaskSpeed = minNewTaskSpeed;
	}

	public double getMaxNewTaskSpeed() {
		return maxNewTaskSpeed;
	}

	public void setMaxNewTaskSpeed(double maxNewTaskSpeed) {
		this.maxNewTaskSpeed = maxNewTaskSpeed;
	}

	public int getMinAccessLevel() {
		return minAccessLevel;
	}

	public void setMinAccessLevel(int minAccessLevel) {
		this.minAccessLevel = minAccessLevel;
	}

	public int getMaxAccessLevel() {
		return maxAccessLevel;
	}

	public void setMaxAccessLevel(int maxAccessLevel) {
		this.maxAccessLevel = maxAccessLevel;
	}
	
	
}
