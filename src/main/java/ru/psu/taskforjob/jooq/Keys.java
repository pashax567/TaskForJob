/*
 * This file is generated by jOOQ.
 */
package ru.psu.taskforjob.jooq;


import javax.annotation.Generated;

import org.jooq.ForeignKey;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;

import ru.psu.taskforjob.jooq.tables.Employee;
import ru.psu.taskforjob.jooq.tables.Organization;
import ru.psu.taskforjob.jooq.tables.records.EmployeeRecord;
import ru.psu.taskforjob.jooq.tables.records.OrganizationRecord;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>public</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<EmployeeRecord> EMPLOYEE_PKEY = UniqueKeys0.EMPLOYEE_PKEY;
    public static final UniqueKey<OrganizationRecord> ORGANIZATION_PKEY = UniqueKeys0.ORGANIZATION_PKEY;
    public static final UniqueKey<OrganizationRecord> UQ_NAME = UniqueKeys0.UQ_NAME;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<EmployeeRecord, OrganizationRecord> EMPLOYEE__FK_ORG = ForeignKeys0.EMPLOYEE__FK_ORG;
    public static final ForeignKey<EmployeeRecord, EmployeeRecord> EMPLOYEE__FK_CHIEF = ForeignKeys0.EMPLOYEE__FK_CHIEF;
    public static final ForeignKey<OrganizationRecord, OrganizationRecord> ORGANIZATION__FK_HEADORG = ForeignKeys0.ORGANIZATION__FK_HEADORG;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class UniqueKeys0 {
        public static final UniqueKey<EmployeeRecord> EMPLOYEE_PKEY = Internal.createUniqueKey(Employee.EMPLOYEE, "Employee_pkey", Employee.EMPLOYEE.ID);
        public static final UniqueKey<OrganizationRecord> ORGANIZATION_PKEY = Internal.createUniqueKey(Organization.ORGANIZATION, "Organization_pkey", Organization.ORGANIZATION.ID);
        public static final UniqueKey<OrganizationRecord> UQ_NAME = Internal.createUniqueKey(Organization.ORGANIZATION, "UQ_Name", Organization.ORGANIZATION.ORGNAME);
    }

    private static class ForeignKeys0 {
        public static final ForeignKey<EmployeeRecord, OrganizationRecord> EMPLOYEE__FK_ORG = Internal.createForeignKey(ru.psu.taskforjob.jooq.Keys.ORGANIZATION_PKEY, Employee.EMPLOYEE, "Employee__FK_Org", Employee.EMPLOYEE.IDORG);
        public static final ForeignKey<EmployeeRecord, EmployeeRecord> EMPLOYEE__FK_CHIEF = Internal.createForeignKey(ru.psu.taskforjob.jooq.Keys.EMPLOYEE_PKEY, Employee.EMPLOYEE, "Employee__FK_Chief", Employee.EMPLOYEE.IDCHIEF);
        public static final ForeignKey<OrganizationRecord, OrganizationRecord> ORGANIZATION__FK_HEADORG = Internal.createForeignKey(ru.psu.taskforjob.jooq.Keys.ORGANIZATION_PKEY, Organization.ORGANIZATION, "Organization__FK_HeadOrg", Organization.ORGANIZATION.IDHEADORG);
    }
}