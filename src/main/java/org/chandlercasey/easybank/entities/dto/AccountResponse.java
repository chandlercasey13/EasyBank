package org.chandlercasey.easybank.entities.dto;

import org.chandlercasey.easybank.entities.AccountTypes;

import java.util.Date;

public record AccountResponse(
        Long accountNumber,
        AccountTypes accountType,
        String branchAddress,
        Date createDt
) {}
