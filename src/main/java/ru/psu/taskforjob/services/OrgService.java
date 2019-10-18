package ru.psu.taskforjob.services;

import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;
import ru.psu.taskforjob.db.DataSourceConfig;
import ru.psu.taskforjob.jooq.tables.Employee;
import ru.psu.taskforjob.jooq.tables.Organization;

import javax.sql.DataSource;
import java.util.*;

@Service
public class OrgService {
    DataSource dataSource;
    SQLDialect dialect;

    public OrgService() {
        dataSource = DataSourceConfig.createDataSource();
        dialect = SQLDialect.POSTGRES;
    }

    public Map<String, Object> createOrg(String name, UUID headId) {
        UUID newId = UUID.randomUUID();
        if (String.valueOf(headId).equals(""))
            headId = null;

        DSL.using(dataSource, dialect).insertInto(Organization.ORGANIZATION)
                .set(Organization.ORGANIZATION.ID, newId)
                .set(Organization.ORGANIZATION.ORGNAME, name)
                .set(Organization.ORGANIZATION.IDHEADORG, headId)
                .execute();
        UUID finalHeadId = headId;
        Map<String, Object> newOrg = new HashMap<String, Object>() {{
            put("id", newId);
            put("name", name);
            put("headId", finalHeadId);
        }};
        return newOrg;
    }

    public Map<String, Object> updateOrg(Map<String, String> organization) {
        UUID id = UUID.fromString(organization.get("id"));
        String newName = organization.get("name");
        UUID newHeadId = UUID.fromString(organization.get("headId"));

        if (newHeadId.equals(""))
            newHeadId = null;

        DSL.using(dataSource, dialect).update(Organization.ORGANIZATION)
                .set(Organization.ORGANIZATION.ORGNAME, newName)
                .set(Organization.ORGANIZATION.IDHEADORG, newHeadId)
                .where(Organization.ORGANIZATION.ID.eq(id))
                .execute();

        UUID finalNewHeadId = newHeadId;
        Map<String, Object> newOrg = new HashMap<String, Object>() {{
            put("id", id);
            put("name", newName);
            put("headId", finalNewHeadId);
        }};
        return newOrg;

    }

    public void deleteOrg(UUID uuid) {
        Record1<Integer> countChildOrg = DSL.using(dataSource, dialect).selectCount()
                .from(Organization.ORGANIZATION)
                .where(Organization.ORGANIZATION.IDHEADORG.eq(uuid)).fetchAny();
        if (countChildOrg.value1().equals(0))
            return;

        DSL.using(dataSource, dialect).deleteFrom(Organization.ORGANIZATION).where(Organization.ORGANIZATION.ID.eq(uuid));
    }

    public List<Map<String, Object>> getAll() {
        Result<Record2<String, Integer>> result = DSL.using(dataSource, dialect).select(Organization.ORGANIZATION.ORGNAME, Employee.EMPLOYEE.ID.countDistinct().as("COUNT"))
                .from(Organization.ORGANIZATION.fullJoin(Employee.EMPLOYEE)
                        .on(Organization.ORGANIZATION.ID.eq(Employee.EMPLOYEE.IDORG))).groupBy(Organization.ORGANIZATION.ORGNAME)
                .fetch();

        List<Map<String, Object>> ans = new ArrayList<Map<String, Object>>();

        for (Record2<String, Integer> org : result) {
            ans.add(new HashMap<String, Object>() {{
                put("name", org.get(Organization.ORGANIZATION.ORGNAME));
                put("count", org.get("COUNT"));
            }});
        }

        return ans;
    }

}
