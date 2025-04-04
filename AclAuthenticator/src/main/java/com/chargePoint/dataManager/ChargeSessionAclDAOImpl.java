package com.chargePoint.dataManager;

import com.chargePoint.models.Access;
import com.chargePoint.models.ChargeSessionAcl;
import com.chargePoint.util.SampleDataProvider;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * Handle DB operations for ACL requests on creating charge session
 */
@Repository
public class ChargeSessionAclDAOImpl implements ChargeSessionAclDAO {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  private static final String COLUMN_NAME_ENTITY_ID = "ENTITY_ID";
  private static final String COLUMN_NAME_ENTITY_TYPE = "ENTITY_TYPE";
  private static final String COLUMN_NAME_ACCESS = "ACCESS";
  private static final String TABLE_NAME = "CHARGE_SESSION_ACL";

  @Autowired
  ChargeSessionAclDAOImpl(JdbcTemplate jdbcTemplate) {
    initTable(jdbcTemplate);
  }

  /**
   * Create an empty table if it doesn't exist already & populate dummy entries to table
   * by reading from {@link SampleDataProvider}
   */
  private void initTable(JdbcTemplate jdbcTemplate) {
    String dropTableSql = "DROP TABLE IF EXISTS %s;".formatted(TABLE_NAME);
    String createTableSql =
        "CREATE TABLE %s (%s VARCHAR(100) PRIMARY KEY, %s ENUM ('DRIVER', 'STATION'), %s ENUM ('ALLOWED', 'DENIED'));".formatted(
            TABLE_NAME, COLUMN_NAME_ENTITY_ID, COLUMN_NAME_ENTITY_TYPE, COLUMN_NAME_ACCESS);
    jdbcTemplate.execute(dropTableSql);
    jdbcTemplate.execute(createTableSql);

    SampleDataProvider.getSampleChargeSessionAcls().forEach(acl -> createChargeSessionAcls(acl, jdbcTemplate));
  }

  private final RowMapper<ChargeSessionAcl> Mapper = (resultSet, idx) -> {
    ChargeSessionAcl chargeSessionAcl = new ChargeSessionAcl();
    chargeSessionAcl.setEntityId(resultSet.getString(COLUMN_NAME_ENTITY_ID));
    chargeSessionAcl.setEntityType(ChargeSessionAcl.EntityType.valueOf(resultSet.getString(COLUMN_NAME_ENTITY_TYPE)));
    chargeSessionAcl.setAccess(Access.valueOf(resultSet.getString(COLUMN_NAME_ACCESS)));
    return chargeSessionAcl;
  };

  @Override
  public List<ChargeSessionAcl> getChargeSessionAclsForEntityPair(String firstEntityId, String secondEntityId)
      throws QueryTimeoutException {
    String sql =
        "SELECT * FROM %s where %s in ( '%s' , '%s')".formatted(TABLE_NAME, COLUMN_NAME_ENTITY_ID, firstEntityId,
            secondEntityId);
    jdbcTemplate.setQueryTimeout(3);
    return jdbcTemplate.query(sql, Mapper);
  }

  @Override
  public void createChargeSessionAcls(ChargeSessionAcl acl, JdbcTemplate jdbcTemplate) throws DataAccessException {
    String sql = "INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?)".formatted(TABLE_NAME, COLUMN_NAME_ENTITY_ID,
        COLUMN_NAME_ENTITY_TYPE, COLUMN_NAME_ACCESS);
    jdbcTemplate.update(sql, acl.getEntityId(), acl.getEntityType().toString(), acl.getAccess().toString());
  }
}
