package com.example.MemoArchive.dao;

import com.example.MemoArchive.model.VoiceMemo;
import com.example.MemoArchive.utility.DaoExceptionUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
public class VoiceMemoDao implements VoiceMemoInterface {
    private JdbcTemplate jdbcTemplate;

    public VoiceMemoDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public VoiceMemo addVoiceMemo(VoiceMemo voiceMemo) {
        return DaoExceptionUtil.handleJdbcOperation(() -> {
            String sql = "INSERT INTO voice_memo (memory_id, transcript_text) VALUES (?, ?)";
            jdbcTemplate.update(sql, voiceMemo.getMemoryId(), voiceMemo.getTranscriptText());
            return voiceMemo;
        });
    }

    @Override
    public VoiceMemo getVoiceMemoById(int voiceMemoId) {
        return DaoExceptionUtil.handleJdbcOperation(() -> {
            String sql = "SELECT * FROM voice_memo WHERE voice_memo_id = ?";
            return jdbcTemplate.queryForObject(sql, this::mapRowToVoiceMemo, voiceMemoId);
        });
    }

    @Override
    public List<VoiceMemo> getAllVoiceMemos() {
        return DaoExceptionUtil.handleJdbcOperation(() -> {
            String sql = "SELECT * FROM voice_memo";
            return jdbcTemplate.query(sql, this::mapRowToVoiceMemo);
        });
    }

    @Override
    public boolean updateVoiceMemo(VoiceMemo voiceMemo) {
        return DaoExceptionUtil.handleJdbcOperation(() -> {
            String sql = "UPDATE voice_memo SET memory_id = ?, transcript_text = ? WHERE voice_memo_id = ?";
            int rowsAffected = jdbcTemplate.update(sql, voiceMemo.getMemoryId(), voiceMemo.getTranscriptText(), voiceMemo.getVoiceMemoId());
            return rowsAffected > 0;
        });
    }

    @Override
    public boolean deleteVoiceMemo(int voiceMemoId) {
        return DaoExceptionUtil.handleJdbcOperation(() -> {
            String sql = "DELETE FROM voice_memo WHERE voice_memo_id = ?";
            int rowsAffected = jdbcTemplate.update(sql, voiceMemoId);
            return rowsAffected > 0;
        });
    }

    private VoiceMemo mapRowToVoiceMemo(ResultSet rs, int rowNum) throws SQLException {
        VoiceMemo voiceMemo = new VoiceMemo();
        voiceMemo.setVoiceMemoId(rs.getInt("voice_memo_id"));
        voiceMemo.setMemoryId(rs.getInt("memory_id"));
        voiceMemo.setTranscriptText(rs.getString("transcript_text"));
        return voiceMemo;
    }
}
