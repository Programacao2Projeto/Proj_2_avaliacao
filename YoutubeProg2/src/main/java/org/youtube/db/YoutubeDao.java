package org.youtube.db;


import org.joda.time.LocalDate;
import org.youtube.api.Youtube;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class YoutubeDao {

    public YoutubeDao() { }

    private String createSQL = "insert into youtube (nomevideo,nomecanal, dataenvio) values (?, ?, ?)";
    private String readSQL = "select * from youtube";
    private String updateSQL = "update youtube set nomevideo = ?, nomecanal = ? where id = ?";
    private String deleteSQL = "delete from youtube  where id = ?";

    private final MySQLConnection mysql = new MySQLConnection();//

    public int size(){
        List<Youtube> youtubeList = new ArrayList();
        return youtubeList.size();
    }

    public boolean create(Youtube videos) {
        Connection conexao = mysql.getConnection();
        LocalDate data = LocalDate.now();

        String datafinal = data.toString();

        try {
            PreparedStatement stm = conexao.prepareStatement(createSQL);

            stm.setString(1, videos.getNomevideo());
            stm.setString(2, videos.getNomecanal());
            stm.setDate(3, Date.valueOf(datafinal));

            int num = stm.executeUpdate();
            return num > 0 ? true : false;

        } catch (final SQLException ex) {
            System.out.println("Falha no banco de dados.");
            ex.printStackTrace();
        } catch (final Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                conexao.close();
            } catch (final Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public List<Youtube> read() {
        Connection conexao = mysql.getConnection();
        List<Youtube> youtubeList = new ArrayList();

        try {
            PreparedStatement stm = conexao.prepareStatement(readSQL);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Youtube videos = new Youtube();
                videos.setNomecanal(rs.getString("nomecanal"));
                videos.setNomevideo(rs.getString("nomevideo"));
                videos.setDataenvio(rs.getDate("dataenvio"));
                videos.setId(rs.getLong("id"));
                youtubeList.add(videos);
            }
            return youtubeList;

        } catch (final SQLException ex) {
            System.out.println("Falha no banco de dados");
            ex.printStackTrace();
        } catch (final Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                conexao.close();
            } catch (final Exception ex) {
                ex.printStackTrace();
            }
        }
        return youtubeList;
    }
    public boolean update(Youtube results) {
        Connection conexao = mysql.getConnection();

        try {
            PreparedStatement stm = conexao.prepareStatement(updateSQL);
            stm.setString(1, results.getNomevideo());
            stm.setString(2, results.getNomecanal());
            stm.setLong(3, results.getId());

            int num = stm.executeUpdate();
            return num > 0 ? true : false;

        } catch (final SQLException ex) {
            System.out.println("Falha de conexão com a base de dados!");
            ex.printStackTrace();
        } catch (final Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                conexao.close();
            } catch (final Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
    public boolean delete(long id) {
        Connection conexao = mysql.getConnection();

        try {
            PreparedStatement stm = conexao.prepareStatement(deleteSQL);

            stm.setLong(1, id);
            int num = stm.executeUpdate();
            return num > 0 ? true : false;

        } catch (final SQLException ex) {
            System.out.println("Falha com o Banco de Dados");
            ex.printStackTrace();
        } catch (final Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                conexao.close();
            } catch (final Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
}