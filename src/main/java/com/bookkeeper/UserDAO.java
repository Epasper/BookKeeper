package com.bookkeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UserDAO extends JdbcDaoSupport implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    DataSource driverManagerDataSource = DataSourceCreator.getDataSource();



    @PostConstruct
    private void initialize() {
        DataSource dataSource = DataSourceCreator.getDataSource();
        this.setDataSource(dataSource);
        System.out.println(dataSource);
    }

/*    public User findUserAccount(String userName) throws SQLException {
        String sql = UserMapper.BASE_SQL + " where username = ?";
        jdbcTemplate.setDataSource(driverManagerDataSource);
        PreparedStatement preparedStatement = driverManagerDataSource.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, userName);

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{userName}, (rs, rowNum) ->
                    new User(
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getLong("user_id")
                    ));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }*/

    @Override
    public User findByUsername(String username) throws SQLException {
        String sql = UserMapper.BASE_SQL + " where username = ?";
        jdbcTemplate.setDataSource(driverManagerDataSource);
        PreparedStatement preparedStatement = driverManagerDataSource.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, username);

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{username}, (rs, rowNum) ->
                    new User(
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getLong("user_id")
                    ));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<User> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<User> findAllById(Iterable<Integer> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void deleteAll(Iterable<? extends User> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends User> S save(S s) {
        return null;
    }

    @Override
    public <S extends User> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<User> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends User> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<User> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public User getOne(Integer integer) {
        return null;
    }

    @Override
    public <S extends User> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends User> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends User> boolean exists(Example<S> example) {
        return false;
    }
}
