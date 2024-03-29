/*
 * This file is generated by jOOQ.
 */
package ru.psu.taskforjob.jooq.tables;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import ru.psu.taskforjob.jooq.Indexes;
import ru.psu.taskforjob.jooq.Keys;
import ru.psu.taskforjob.jooq.Public;
import ru.psu.taskforjob.jooq.tables.records.OrganizationRecord;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Organization extends TableImpl<OrganizationRecord> {

    private static final long serialVersionUID = 2115167863;

    /**
     * The reference instance of <code>public.Organization</code>
     */
    public static final Organization ORGANIZATION = new Organization();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<OrganizationRecord> getRecordType() {
        return OrganizationRecord.class;
    }

    /**
     * The column <code>public.Organization.id</code>.
     */
    public final TableField<OrganizationRecord, UUID> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>public.Organization.OrgName</code>.
     */
    public final TableField<OrganizationRecord, String> ORGNAME = createField(DSL.name("OrgName"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>public.Organization.idHeadOrg</code>.
     */
    public final TableField<OrganizationRecord, UUID> IDHEADORG = createField(DSL.name("idHeadOrg"), org.jooq.impl.SQLDataType.UUID, this, "");

    /**
     * Create a <code>public.Organization</code> table reference
     */
    public Organization() {
        this(DSL.name("Organization"), null);
    }

    /**
     * Create an aliased <code>public.Organization</code> table reference
     */
    public Organization(String alias) {
        this(DSL.name(alias), ORGANIZATION);
    }

    /**
     * Create an aliased <code>public.Organization</code> table reference
     */
    public Organization(Name alias) {
        this(alias, ORGANIZATION);
    }

    private Organization(Name alias, Table<OrganizationRecord> aliased) {
        this(alias, aliased, null);
    }

    private Organization(Name alias, Table<OrganizationRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Organization(Table<O> child, ForeignKey<O, OrganizationRecord> key) {
        super(child, key, ORGANIZATION);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.ORGANIZATION_PKEY);
    }

    @Override
    public UniqueKey<OrganizationRecord> getPrimaryKey() {
        return Keys.ORGANIZATION_PKEY;
    }

    @Override
    public List<UniqueKey<OrganizationRecord>> getKeys() {
        return Arrays.<UniqueKey<OrganizationRecord>>asList(Keys.ORGANIZATION_PKEY, Keys.UQ_NAME);
    }

    @Override
    public List<ForeignKey<OrganizationRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<OrganizationRecord, ?>>asList(Keys.ORGANIZATION__FK_HEADORG);
    }

    public ru.psu.taskforjob.jooq.tables.Organization organization() {
        return new ru.psu.taskforjob.jooq.tables.Organization(this, Keys.ORGANIZATION__FK_HEADORG);
    }

    @Override
    public Organization as(String alias) {
        return new Organization(DSL.name(alias), this);
    }

    @Override
    public Organization as(Name alias) {
        return new Organization(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Organization rename(String name) {
        return new Organization(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Organization rename(Name name) {
        return new Organization(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<UUID, String, UUID> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}
