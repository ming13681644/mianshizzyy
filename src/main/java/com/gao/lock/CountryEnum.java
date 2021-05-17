package com.gao.lock;

import lombok.Getter;

/**
 * ����ຫ��κ��
 */
public enum CountryEnum {
    one(1, "��"), two(2, "��"), three(3, "��"), four(4, "��"), five(5, "��"), six(6, "κ");
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
