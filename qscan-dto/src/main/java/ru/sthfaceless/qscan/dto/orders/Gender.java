package ru.sthfaceless.qscan.dto.orders;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Gender {

    MAN("MAN"), WOMAN("WOMAN");
    private final String name;

    @Override
    public String toString(){
        return this.name;
    }
}
