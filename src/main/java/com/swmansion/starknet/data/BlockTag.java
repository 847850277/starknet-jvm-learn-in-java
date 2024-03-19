package com.swmansion.starknet.data;

public enum BlockTag {
    LATEST("latest"),
    PENDING("pending");

    private String tag;

    BlockTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}
