package com.gao.lock;

import lombok.Getter;

/**
 * Æë³þÑàº«ÕÔÎºÇØ
 */
public enum CountryEnum {
    one(1, "Æë"), two(2, "³þ"), three(3, "Ñà"), four(4, "º«"), five(5, "ÕÔ"), six(6, "Îº");
    @Getter
    private int code;
    @Getter
    private String country;

    CountryEnum(int code, String country) {
        this.code = code;
        this.country = country;
    }

    public static CountryEnum foreachCountryEnum(int i) {
        CountryEnum[] values = CountryEnum.values();
        for (CountryEnum value : values) {
            if (i == value.getCode()) {
                return value;
            }
        }
        return null;
    }
}
