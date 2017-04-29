/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetApplication.dataContracts;

import java.io.Serializable;

/**
 *
 * @author Eclat
 */
public class DailyTransaction implements Serializable{
    private Long day;
    private Double amount;
    
    public DailyTransaction() {
        day = 0L;
        amount = 0.0;
    }

    public Long getDay() {
        return day;
    }

    public void setDay(Long day) {
        this.day = day;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
}
