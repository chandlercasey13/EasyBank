package org.chandlercasey.easybank.entities.dto;

import jakarta.persistence.Column;

import java.sql.Date;

public record CreateCardRequest(CardType cardType, long accountNumber) {
}
