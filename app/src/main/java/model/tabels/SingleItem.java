package model.tabels;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ddopik on 10/10/2017.
 */

public class SingleItem extends RealmObject {

    @PrimaryKey
    private int itemId;
    private String itemName;
    private String itemPrice;
    private boolean itemExistence;
    private String itemUpdate;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public boolean isItemExistence() {
        return itemExistence;
    }

    public void setItemExistence(boolean itemExistence) {
        this.itemExistence = itemExistence;
    }

    public String getItemUpdate() {
        return itemUpdate;
    }

    public void setItemUpdate(String itemUpdate) {
        this.itemUpdate = itemUpdate;
    }




}
