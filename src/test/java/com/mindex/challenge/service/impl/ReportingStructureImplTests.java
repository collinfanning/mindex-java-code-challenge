package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureImplTests {


    @Autowired
    private ReportingStructureImpl reportingStructureService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void testRead() {
        Employee testEmployee = new Employee();
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Doe");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Developer");

        ReportingStructure testReportingStructure = new ReportingStructure();
        testReportingStructure.setEmployee(testEmployee);
        testReportingStructure.setNumberOfReports(2);

        ReportingStructure actualReportingStructure = reportingStructureService.read("03aa1462-ffa9-4978-901b-7c001562cf6f");

        int testReports = testReportingStructure.getNumberOfReports();
        int actualReports = actualReportingStructure.getNumberOfReports();

        assertEquals(testReports, actualReports);
    }
}
