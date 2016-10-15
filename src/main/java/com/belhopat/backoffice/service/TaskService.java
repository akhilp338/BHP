package com.belhopat.backoffice.service;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.belhopat.backoffice.dto.DashboardCount;
import com.belhopat.backoffice.model.Task;

@Service
public interface TaskService {

	public DataTablesOutput<Task> getDashboardTasks(DataTablesInput input);

	public DashboardCount getDashboardCount();

}
