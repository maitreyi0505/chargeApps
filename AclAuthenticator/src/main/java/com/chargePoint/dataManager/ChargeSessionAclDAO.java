package com.chargePoint.dataManager;

import com.chargePoint.models.ChargeSessionAcl;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;


public interface ChargeSessionAclDAO {

  List<ChargeSessionAcl> getChargeSessionAclsForEntityPair(String firstEntityId, String secondEntityId) throws DataAccessException;

  void createChargeSessionAcls(ChargeSessionAcl acl, JdbcTemplate jdbcTemplate) throws DataAccessException;
}
