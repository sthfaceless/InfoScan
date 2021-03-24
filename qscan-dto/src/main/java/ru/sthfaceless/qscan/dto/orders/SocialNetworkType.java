package ru.sthfaceless.qscan.dto.orders;

import lombok.AllArgsConstructor;

/**
 * @author danil
 * @date 23.04.19
 */
@AllArgsConstructor
public enum SocialNetworkType {

    VK("VK"),
    FB("FB"),
    INSTAGRAM("INSTAGRAM"),
    TWITTER("TWITTER");

    private final String name;

    @Override
    public String toString() {
        return this.name;
    }
}
