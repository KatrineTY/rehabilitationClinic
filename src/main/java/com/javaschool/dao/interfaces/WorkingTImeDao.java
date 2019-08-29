package com.javaschool.dao.interfaces;

import com.javaschool.entities.WorkingTime;

import java.util.List;

public interface WorkingTImeDao {

    /**
     * Retrieve working times
     *
     * @return list of working times
     */
    List<WorkingTime> getWorkingTimes();

    /**
     * Retrieve working time by id
     *
     * @param id - the specified id
     * @return the specified working time
     */
    WorkingTime getWorkingTime(int id);

}
