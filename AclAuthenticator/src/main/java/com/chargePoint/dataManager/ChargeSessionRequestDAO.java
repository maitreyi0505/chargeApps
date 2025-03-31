package com.chargePoint.dataManager;

import com.chargePoint.models.ChargeSessionAcl;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;

public interface ChargeSessionRequestDAO {

  List<ChargeSessionAcl> getChargeSessionAcls() throws DataAccessException;

  void createChargeSessionAcls(ChargeSessionAcl chargeSessionAcl) throws DataAccessException;
}
