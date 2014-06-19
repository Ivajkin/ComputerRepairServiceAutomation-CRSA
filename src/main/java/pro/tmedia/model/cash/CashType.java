package pro.tmedia.model.cash;

import pro.tmedia.model.DictionaryItem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User: Ivaykin Timofey
 * Date: 20.06.14
 */

/*
`id` int not null AUTO_INCREMENT unique primary key,
`name` varchar(100) CHARACTER SET utf8 not null unique,
`saldo` int not null
*/
@Entity
@Table(name="cash_type")
public class CashType extends DictionaryItem {
    int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
