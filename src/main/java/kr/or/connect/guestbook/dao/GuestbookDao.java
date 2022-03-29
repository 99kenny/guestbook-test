package kr.or.connect.guestbook.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.guestbook.dto.Guestbook;
import kr.or.connect.guestbook.dto.Params;

import static kr.or.connect.guestbook.dao.GuestbookDaoSqls.*;

@Repository
public class GuestbookDao {
	private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<Guestbook> rowMapper = BeanPropertyRowMapper.newInstance(Guestbook.class);
    
    public GuestbookDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("guestbook")
                .usingGeneratedKeyColumns("id");
    }
    
    public List<Guestbook> selectAll(Params params) {
    	//sql문 매개변수 :start, :limit, :guestbookLocation 매칭해주는 객체 
    	SqlParameterSource p = new BeanPropertySqlParameterSource(params);
    	
        return jdbc.query(SELECT_PAGING, p, rowMapper);
    }
    
//    public List<Guestbook> selectAll(Params params){
//    	SqlParameterSource p = new BeanPropertySqlParameterSource(params);
//    	return jdbc.query(SELECT, p, rowMapper);
//    }


	public Long insert(Guestbook guestbook) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(guestbook);
		return insertAction.executeAndReturnKey(params).longValue();
	}
	
	public int deleteById(Long id) {
		Map<String, ?> params = Collections.singletonMap("id", id);
		return jdbc.update(DELETE_BY_ID, params);
	}
	
	public int selectCount() {
		return jdbc.queryForObject(SELECT_COUNT, Collections.emptyMap(), Integer.class);
	}
}
