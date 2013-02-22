package real_time_scheduling_system.scheduling.a_scheduling_algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class OpenList {
	private float randomGeneratedF;
	private List<TaskScheduling> list;

	public OpenList(float randomGeneratedF) {
		list = new LinkedList<TaskScheduling>();
		this.randomGeneratedF = randomGeneratedF;
	}

	public void insert(TaskScheduling taskScheduling) {
		if (taskScheduling.getfValue() > randomGeneratedF) {
			return;
		}
		ListIterator<TaskScheduling> iterator = list.listIterator();
		while (iterator.hasNext()) {
			if (iterator.next().getfValue() > taskScheduling.getfValue()) {
				iterator.add(taskScheduling);
				return;
			}
		}
		list.add(taskScheduling);
	}

	public void insert(ArrayList<TaskScheduling> tasksScheduling) {
		if (tasksScheduling == null) {
			return;
		}
		for (TaskScheduling taskScheduling : tasksScheduling) {
			insert(taskScheduling);
		}
	}

	public TaskScheduling getFirst() {
		if (list.size() == 0) {
			return null;
		}
		TaskScheduling taskScheduling = list.get(0);
		list.remove(0);
		return taskScheduling;
	}
}
