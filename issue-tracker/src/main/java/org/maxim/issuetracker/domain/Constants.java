package org.maxim.issuetracker.domain;

final class Constants {

    public static final int MIN_DURATION = 0;
    public static final int MAX_DURATION_PER_DAY = 1440;
    public static final int TEXT_MAX_SIZE = 255;

    public static final String SIZE_ERROR_MSG_SUFFIX = " should be shorter than " + TEXT_MAX_SIZE + " characters.";
    public static final String NULL_ERROR_MSG_SUFFIX = " can't be a 'null'.";

    private Constants() { }

}
