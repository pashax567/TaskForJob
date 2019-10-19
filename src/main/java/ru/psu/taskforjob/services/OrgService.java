package ru.psu.taskforjob.services;

import org.jooq.*;
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

        Record1<Integer> countRaw = DSL.using(dataSource, dialect).selectCount().from(Organization.ORGANIZATION)
                .where(Organization.ORGANIZATION.ORGNAME.eq(name)).fetchAny();
        if (countRaw.value1() > 0 || name == "")
            return null;
        DSL.using(dataSource, dialect).insertInto(Organization.ORGANIZATION)
                .set(Organization.ORGANIZATION.ID, newId)
                .set(Organization.ORGANIZATION.ORGNAME, name)
                .set(Organization.ORGANIZATION.IDHEADORG, headId)
                .onConflict()
                .doNothing()
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
        UUID newHeadId = null;
        if (organization.get("headId") != "")
            newHeadId = UUID.fromString(organization.get("headId"));
        Record1<Integer> countRaw = DSL.using(dataSource, dialect).selectCount().from(Organization.ORGANIZATION)
                .where(Organization.ORGANIZATION.ORGNAME.eq(newName)).fetchAny();
        if (countRaw.value1() > 0 || newName == "")
            return null;

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
        if (countChildOrg.value1()>0)
            return;

        DSL.using(dataSource, dialect).deleteFrom(Organization.ORGANIZATION).where(Organization.ORGANIZATION.ID.eq(uuid)).execute();
    }

    public List<Map<String, Object>> getAll() {
        Result<Record4<UUID, String, UUID, Integer>> result = DSL.using(dataSource, dialect)
                .select(Organization.ORGANIZATION.ID.as("id"), Organization.ORGANIZATION.ORGNAME, Organization.ORGANIZATION.IDHEADORG.as("headId"), Employee.EMPLOYEE.ID.countDistinct().as("COUNT"))
                .from(Organization.ORGANIZATION.fullJoin(Employee.EMPLOYEE)
                        .on(Organization.ORGANIZATION.ID.eq(Employee.EMPLOYEE.IDORG)))
                .groupBy(Organization.ORGANIZATION.ID)
                .fetch();

        List<Map<String, Object>> ans = new ArrayList<>();

        for (Record4<UUID, String, UUID, Integer> org : result) {
            ans.add(new HashMap<String, Object>() {{
                put("id", org.get(Organization.ORGANIZATION.ID));
                put("name", org.get(Organization.ORGANIZATION.ORGNAME));
                put("headId", org.get("headId"));
                put("count", org.get("COUNT"));
            }});
        }

        return ans;
    }

}
