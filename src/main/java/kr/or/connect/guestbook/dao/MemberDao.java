package kr.or.connect.guestbook.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.guestbook.dto.Member;
import static kr.or.connect.guestbook.dao.MemberDaoSqls.*;

@Repository
public class MemberDao {
	private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<Member> rowMapper = BeanPropertyRowMapper.newInstance(Member.class);
    
    public MemberDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("guestbook_user");
    }
    
    public int insert(Member member) {
    	SqlParameterSource params = new BeanPropertySqlParameterSource(member);
		return insertAction.execute(params);
    }
    
    public int deleteById(String id) {
    	Map<String, ?> params = Collections.singletonMap("id", id);
    	return jdbc.update(DELETE_BY_ID, params);
    }
    
    public int selectCount() {
    	
		return jdbc.queryForObject(SELECT_COUNT, Collections.emptyMap(), Integer.class);
	}
    
    public Optional<Member> login(String id, String password) {
    	Map<String,String> params = new HashMap<>();
    	params.put("id", id);
    	params.put("password", password);
    	List<Member> member = jdbc.query(SELECT_BY_ID, params, rowMapper);
    	if(member.size() == 0) {
    		return Optional.ofNullable(null);
    	}
    	return Optional.of(member.get(0));
    }
}
