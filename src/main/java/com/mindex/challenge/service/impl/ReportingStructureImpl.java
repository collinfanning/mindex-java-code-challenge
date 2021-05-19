package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReportingStructureImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReportingStructure read(String id) {
        LOG.debug("Creating reportingStructure with employee id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);


        ReportingStructure rstructure = new ReportingStructure();
        rstructure.setEmployee(employee);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        int totalReports = countTotalReports(employee, 0);

        rstructure.setNumberOfReports(totalReports);
        return rstructure;
    }


    public int countTotalReports(Employee e, int sum)
    {
        List<Employee> currentReports = e.getDirectReports();
        if(currentReports != null) {
            for (Employee currentEmployee : currentReports) {
                String currentID = currentEmployee.getEmployeeId();
                currentEmployee = employeeRepository.findByEmployeeId(currentID);
                if (currentEmployee != null) {
                    sum += countTotalReports(currentEmployee, sum) ;
                }
            }
            return sum+currentReports.size();
        }
        else
            return 0;
    }
}
