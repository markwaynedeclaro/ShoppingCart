package com.automatic.shoppingcart.model;

public enum PromoEnum {

    BUYXGETX("BUYXGETX"),
    ORDERCOUNTDISCOUNT("ORDERCOUNTDISCOUNT");

    private String value;

    PromoEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }

    public static PromoEnum getEnum(String value) {
        if(value == null)
            throw new IllegalArgumentException();
        for(PromoEnum v : values())
            if(value.equalsIgnoreCase(v.getValue())) return v;
        throw new IllegalArgumentException();
    }

}
