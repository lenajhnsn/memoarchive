package com.example.MemoArchive.daos;

import com.example.MemoArchive.models.Memory;
import com.example.MemoArchive.models.MemoryContribution;
import com.example.MemoArchive.models.Permission;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Controller // Controller annotation allows Spring to create DAO
public class MemoryContributionDao implements GeneralDaoInterface<MemoryContributionDao>{
    // INSTANCE VARIABLES
    private JdbcTemplate jdbcTemplate;

    // Spring creates the datasource
    public MemoryContributionDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // ----METHODS----

    // CREATE (POST)

    // READ (GET)

    // GET ALL PERMISSIONS
    public List<MemoryContribution> getAllMemoryContributions() {
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from memoryContribution");

        List<MemoryContribution> memoryContributions = new ArrayList<>();

        while (rowSet.next()) {
            memoryContributions.add(mapRowtoMemoryContribution(rowSet));
        }
        return memoryContributions;
    }


    // UPDATE (PUT)

    // DELETE

    // MAP ROW SET
    private MemoryContribution mapRowtoMemoryContribution(SqlRowSet rowSet) {
        MemoryContribution memoryContribution = new MemoryContribution();
        memoryContribution.setContributorId(rowSet.getInt("contributor_id"));
        memoryContribution.setContributionId(rowSet.getInt("contribution_id"));
        memoryContribution.setMemoryId(rowSet.getInt("memory_id"));
        return memoryContribution;

    }
}
