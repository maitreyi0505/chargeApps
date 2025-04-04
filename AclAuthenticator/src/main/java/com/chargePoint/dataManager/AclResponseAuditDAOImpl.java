package com.chargePoint.dataManager;

import com.chargePoint.models.AclResponseAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Handle DB operations for tracking of ACL response
 */
@Repository
public class AclResponseAuditDAOImpl implements AclResponseAuditDAO {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private static final String COLUMN_STATION_ID = "STATION_ID";
  private static final String COLUMN_DRIVER_ID = "DRIVER_ID";
  private static final String COLUMN_CALLBACK_URL = "CALLBACK_URL";
  private static final String COLUMN_TIMESTAMP = "TIMESTAMP";
  private static final String COLUMN_PERMISSION_STATUS = "PERMISSION_STATUS";
  private static final String COLUMN_CALLBACK_RESPONSE_STATUS = "CALLBACK_RESPONSE_STATUS";
  private static final String TABLE_NAME = "ACL_RESPONSE_AUDIT";

  @Autowired
  AclResponseAuditDAOImpl(JdbcTemplate jdbcTemplate) {
    initTable(jdbcTemplate);
  }

  /**
   * Create an empty table if it doesn't exist already
   */
  private void initTable(JdbcTemplate jdbcTemplate) {
    String dropTableSql = "DROP TABLE IF EXISTS %s;".formatted(TABLE_NAME);
    String createTableSql =
        "CREATE TABLE %s ( ID BIGINT PRIMARY KEY AUTO_INCREMENT,  %s VARCHAR(100) , %s VARCHAR(100) , %s VARCHAR(100) , %s BIGINT , %s ENUM ('ALLOWED', 'NOT_ALLOWED', 'UNKNOWN', 'INVALID'), %s INT );".formatted(
            TABLE_NAME, COLUMN_STATION_ID, COLUMN_DRIVER_ID, COLUMN_CALLBACK_URL, COLUMN_TIMESTAMP,
            COLUMN_PERMISSION_STATUS, COLUMN_CALLBACK_RESPONSE_STATUS);

    jdbcTemplate.execute(dropTableSql);
    jdbcTemplate.execute(createTableSql);
  }

  @Override
  public void insertAclResponseAudit(AclResponseAudit auditEntry) {
    String sql = "INSERT INTO %s (%s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?)".formatted(TABLE_NAME,
        COLUMN_STATION_ID, COLUMN_DRIVER_ID, COLUMN_CALLBACK_URL, COLUMN_TIMESTAMP,
        COLUMN_PERMISSION_STATUS, COLUMN_CALLBACK_RESPONSE_STATUS);

    jdbcTemplate.update(sql, auditEntry.getStationId(), auditEntry.getDriverId(), auditEntry.getCallbackUrl(), auditEntry.getTimestamp(), auditEntry.getPermissionStatus().toString(), auditEntry.getCallbackResponseStatus());
  }
}
