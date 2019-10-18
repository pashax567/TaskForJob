package ru.psu.taskforjob.services;

import org.jooq.Record2;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import ru.psu.taskforjob.db.DataSourceConfig;
import ru.psu.taskforjob.dto.OrganizationDTO;
import ru.psu.taskforjob.jooq.tables.Employee;
import ru.psu.taskforjob.jooq.tables.Organization;
import ru.psu.taskforjob.jooq.tables.records.OrganizationRecord;

import javax.sql.DataSource;
import java.util.*;

public class TestService {
    DataSource dataSource;
    SQLDialect dialect;

    public TestService() {
        dataSource = DataSourceConfig.createDataSource();
        dialect = SQLDialect.POSTGRES;
    }

    void Test() {
        Result<OrganizationRecord> fetch = DSL.using(dataSource, dialect).selectFrom(Organization.ORGANIZATION).fetch();
    }

    public void createOrg(String name, UUID headId) {

        DSL.using(dataSource, dialect).insertInto(Organization.ORGANIZATION)
                .set(Organization.ORGANIZATION.ID, UUID.randomUUID())
                .set(Organization.ORGANIZATION.ORGNAME, name)
                .set(Organization.ORGANIZATION.IDHEADORG, headId)
                .execute();
    }

    public void updateOrg(Organization organization) {
        DSL.using(dataSource, dialect).update(Organization.ORGANIZATION)
                .set(Organization.ORGANIZATION.ID, organization.ID)
                .set(Organization.ORGANIZATION.ORGNAME, organization.ORGNAME)
                .set(Organization.ORGANIZATION.IDHEADORG, organization.IDHEADORG)
                .execute();
    }

    public void Delete(UUID uuid) {
        DSL.using(dataSource, dialect).deleteFrom(Organization.ORGANIZATION).where(Organization.ORGANIZATION.ID.eq(uuid));
    }

    public List<Map<String, Object>> getAll() {
        Result<Record2<String, Integer>> result = DSL.using(dataSource, dialect).select(Organization.ORGANIZATION.ORGNAME, Employee.EMPLOYEE.ID.countDistinct().as("COUNT"))
                .from(Organization.ORGANIZATION.fullJoin(Employee.EMPLOYEE)
                        .on(Organization.ORGANIZATION.ID.eq(Employee.EMPLOYEE.IDORG))).groupBy(Organization.ORGANIZATION.ORGNAME)
                .fetch();
        Result<OrganizationRecord> fetch = DSL.using(dataSource, dialect).selectFrom(Organization.ORGANIZATION).fetch();

        List<Map<String, Object>> ans = new ArrayList<Map<String, Object>>();

//        for (Record2<String, Integer> org : result) {
//            ans.add(new HashMap<String, Object>() {{
//                put("name", org.get(Organization.ORGANIZATION.ORGNAME));
//                put("count", org.get("COUNT"));
//            }});
//        }

        return ans;
    }

}
