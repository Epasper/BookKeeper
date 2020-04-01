package com.bookkeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Repository
@Transactional
public class UserDAO extends JdbcDaoSupport {

    /*    @Autowired
        public UserDAO(DataSource dataSource) {
            this.setDataSource(dataSource);
        }*/
    @Autowired
    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    DataSourceConfig dataSourceConfig = new DataSourceConfig();
    private DataSource dataSource = dataSourceConfig.getDataSource();

    @PostConstruct
    private void initialize() {
        this.setDataSource(dataSource);
    }

    public User findUserAccount(String userName) {
        String sql = UserMapper.BASE_SQL + " where username = '" + userName + "'";

        System.out.println(sql);

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{userName}, (rs, rowNum) ->
                    new User(
                            rs.getString("username"),
                            rs.getString("encrypted_password"),
                            rs.getLong("user_id")
                    ));
            //return this.getJdbcTemplate().queryForObject(sql, params, mapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
