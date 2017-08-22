package com.example.pc_.story.database;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by pc- on 2017/8/17.
 */
@Table("AddressItem")
public class AddressItem {

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    public int id;

    public String getProvinceString() {
        return provinceString;
    }

    public void setProvinceString(String provinceString) {
        this.provinceString = provinceString;
    }

    @Column("Province")
    public String  provinceString;

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public String getRegionString() {

        return regionString;
    }

    public void setRegionString(String regionString) {
        this.regionString = regionString;
    }

    public String getCityString() {

        return cityString;
    }

    public void setCityString(String cityString) {
        this.cityString = cityString;
    }

    @Column("City")
    public String cityString;
    @Column("Region")
    public String regionString;

    public String getDefiniteString() {
        return definiteString;
    }

    public void setDefiniteString(String definiteString) {
        this.definiteString = definiteString;
    }

    @Column("Definite")
    public String definiteString;
    @Column("IsDefault")
    public boolean  isDefault;

}
