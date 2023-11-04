package com.carlos.invoice.server.Application;

public final class Constants {

    private Constants() {
        throw new IllegalAccessError("Constant class");
    }

    public final static class JWT {
        public static final String TOKEN_HEADER = "Authorization";
        public static final String TOKEN_PREFIX = "Bearer";
        public static final String USER_NAME = "username";
    }
}
