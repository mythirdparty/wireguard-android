/*
 * Copyright © 2018 WireGuard LLC. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package com.wireguard.config;

import android.support.annotation.Nullable;

import com.wireguard.crypto.KeyFormatException;

public class BadConfigException extends Exception {
    private final Location location;
    private final Reason reason;
    private final Section section;
    @Nullable private final CharSequence text;

    private BadConfigException(final Section section, final Location location,
                               final Reason reason, @Nullable final CharSequence text,
                               @Nullable final Throwable cause) {
        super(cause);
        this.section = section;
        this.location = location;
        this.reason = reason;
        this.text = text;
    }

    public BadConfigException(final Section section, final Location location,
                              final Reason reason, @Nullable final CharSequence text) {
        this(section, location, reason, text, null);
    }

    public BadConfigException(final Section section, final Location location,
                              final KeyFormatException cause) {
        this(section, location, Reason.INVALID_KEY, null, cause);
    }

    public BadConfigException(final Section section, final Location location,
                              @Nullable final CharSequence text,
                              final NumberFormatException cause) {
        this(section, location, Reason.INVALID_NUMBER, text, cause);
    }

    public BadConfigException(final Section section, final Location location,
                              final ParseException cause) {
        this(section, location, Reason.INVALID_VALUE, cause.getText(), cause);
    }

    public Location getLocation() {
        return location;
    }

    public Reason getReason() {
        return reason;
    }

    public Section getSection() {
        return section;
    }

    @Nullable
    public CharSequence getText() {
        return text;
    }

    public enum Location {
        TOP_LEVEL,
        ADDRESS,
        ALLOWED_IPS,
        DNS,
        ENDPOINT,
        EXCLUDED_APPLICATIONS,
        LISTEN_PORT,
        MTU,
        PERSISTENT_KEEPALIVE,
        PRE_SHARED_KEY,
        PRIVATE_KEY,
        PUBLIC_KEY,
    }

    public enum Reason {
        INVALID_KEY,
        INVALID_NUMBER,
        INVALID_VALUE,
        MISSING_ATTRIBUTE,
        MISSING_SECTION,
        MISSING_VALUE,
        SYNTAX_ERROR,
        UNKNOWN_ATTRIBUTE,
        UNKNOWN_SECTION,
        VALUE_OUT_OF_RANGE,
    }

    public enum Section {
        CONFIG,
        INTERFACE,
        PEER,
    }
}
