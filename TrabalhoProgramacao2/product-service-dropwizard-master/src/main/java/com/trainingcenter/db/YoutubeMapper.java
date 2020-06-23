package com.trainingcenter.db;

import com.trainingcenter.api.Youtube;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class YoutubeMapper implements RowMapper<Youtube> {


    @Override
    public Youtube map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Youtube(
                rs.getLong("id"),
                rs.getString("nomevideo"),
                rs.getString("nomecanal"),
                rs.getDate("dataenvio")
        );
}
}
