package ru.psu.taskforjob.services;

import org.jooq.Record3;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;
import ru.psu.taskforjob.db.DataSourceConfig;
import ru.psu.taskforjob.dto.EmployeeDTO;
import ru.psu.taskforjob.jooq.tables.Employee;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmpService {
    DataSource dataSource;
    SQLDialect dialect;

    public EmpService(){
        dataSource = DataSourceConfig.createDataSource();
        dialect = SQLDialect.POSTGRES;
    }

    public List<EmployeeDTO> getAll(){
        Result<Record3<UUID, String, UUID>> result = DSL.using(dataSource, dialect).select(Employee.EMPLOYEE.ID, Employee.EMPLOYEE.WORKERNAME, Employee.EMPLOYEE.IDORG)
                .from(Employee.EMPLOYEE).fetch();

        List<EmployeeDTO> list=new ArrayList<>();
        for (Record3<UUID, String, UUID> emp: result) {
            list.add(new EmployeeDTO(emp.get(Employee.EMPLOYEE.ID),emp.get(Employee.EMPLOYEE.WORKERNAME),emp.get(Employee.EMPLOYEE.IDORG),null));
        }
        return list;
    }
}
