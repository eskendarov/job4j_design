package ru.job4j.io.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG
            = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
        final byte bt = 12;
        final short sh = 44;
        final int in = 3452;
        final long lng = 23535L;
        final float fl = 0.45f;
        final double db = 34.2d;
        final boolean bln = true;
        final char ch = 'A';
        LOG.debug("byte: {}, short: {}, int: {}, long: {},"
                        + "float: {},double: {}, boolean: {}, char: {}",
                bt, sh, in, lng, fl, db, bln, ch
        );
    }
}
