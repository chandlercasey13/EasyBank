package org.chandlercasey.easybank.entities.dto;

import org.chandlercasey.easybank.entities.AccountTypes;

public record CreateAccountRequest(AccountTypes accountType) { }