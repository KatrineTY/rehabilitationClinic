package com.javaschool.services.impls;

import com.javaschool.dao.interfaces.WorkingTImeDao;
import com.javaschool.entities.WorkingTime;
import com.javaschool.services.interfaces.WorkingTimeService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@NoArgsConstructor
public class WorkingTimeServiceImpl implements WorkingTimeService {
    @Autowired
    private WorkingTImeDao workingTImeDao;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<WorkingTime> getWorkingTimes() {
        return workingTImeDao.getWorkingTimes();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public WorkingTime getWorkingTime(int id) {
        return workingTImeDao.getWorkingTime(id);
    }

}
