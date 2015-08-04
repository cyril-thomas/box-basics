package com.simplyct.woddojo.helper.payments;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by cyril on 8/1/15.
 */
@Data
public class TransactionResult {

    private TransactionStatus status;
    private String confirmationId;
    private String message;
    private BigDecimal amount;

    public enum TransactionStatus {
        ERROR, DECLINED, AUTHORIZED
    }

    public boolean isSuccessful(){
        return this.status.equals(TransactionStatus.AUTHORIZED);
    }
}
