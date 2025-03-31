package com.chargePoint.dataManager;

import com.chargePoint.models.ChargeSessionAcl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class ChargeSessionRequestDAOImpl implements ChargeSessionRequestDAO {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  private static final String COLUMN_NAME_ENITITY_ID = "entityId";
  private static final String COLUMN_NAME_ENITITY_TYPE = "entityType";
  private static final String COLUMN_NAME_ACCESS = "access";
  private static final String TABLE_NAME = ChargeSessionAcl.class.getSimpleName().toUpperCase();

  @Autowired
  ChargeSessionRequestDAOImpl(JdbcTemplate jdbcTemplate) {
    initTable(jdbcTemplate);
  }

  private void initTable(JdbcTemplate jdbcTemplate) {
    String dropTableSql = "DROP TABLE IF EXISTS %s;".formatted(TABLE_NAME);
    String createTableSql = "CREATE TABLE %s(%s VARCHAR(100) PRIMARY KEY, %s VARCHAR(50), %s VARCHAR(50));".formatted(TABLE_NAME, COLUMN_NAME_ENITITY_ID, COLUMN_NAME_ENITITY_TYPE, COLUMN_NAME_ACCESS);
    jdbcTemplate.execute(dropTableSql);
    jdbcTemplate.execute(createTableSql);
  }

  private final RowMapper<ChargeSessionAcl> Mapper = (resultSet, idx) -> {
    ChargeSessionAcl chargeSessionAcl = new ChargeSessionAcl();
    chargeSessionAcl.setEntityId(resultSet.getString(COLUMN_NAME_ENITITY_ID));
    chargeSessionAcl.setEntityType(resultSet.getString(COLUMN_NAME_ENITITY_TYPE));
    chargeSessionAcl.setAccess(resultSet.getString(COLUMN_NAME_ACCESS));
    return chargeSessionAcl;
  };

  @Override
  public List<ChargeSessionAcl> getChargeSessionAcls() throws DataAccessException {
    String sql = "SELECT * FROM ChargeSessionAcl";
    return jdbcTemplate.query(sql, Mapper);
  }

  @Override
  public void createChargeSessionAcls(ChargeSessionAcl acl) throws DataAccessException {
    String sql = "INSERT INTO ChargeSessionAcl (entityId, entityType, access) VALUES (?, ?, ?)";
    jdbcTemplate.update(sql, acl.getEntityId(), acl.getEntityType(), acl.getAccess());
  }

}
