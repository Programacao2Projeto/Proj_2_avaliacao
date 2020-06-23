package com.trainingcenter.db;

import com.trainingcenter.api.Youtube;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterRowMapper(YoutubeMapper.class)
public interface YoutubeDao {


    @SqlUpdate("insert into youtube (nomevideo,nomecanal, dataenvio) values (:nomevideo, :nomecanal, :dataenvio)")
    @GetGeneratedKeys
    long insert(@BindBean Youtube product);

    @SqlUpdate("update youtube set nomevideo = :nomevideo, nomecanal = :nomecanal where id = :id")
    @GetGeneratedKeys
    long update(@BindBean Youtube product);

    @SqlUpdate("delete from youtube  where id = :id")
    @GetGeneratedKeys
    long delete(@BindBean Youtube product);

    @SqlQuery("select * from youtube")
    List<Youtube> getAllVideos();

    @SqlQuery("select * from youtube where id = :id")
    Youtube findById(@Bind("id") long id);
}
