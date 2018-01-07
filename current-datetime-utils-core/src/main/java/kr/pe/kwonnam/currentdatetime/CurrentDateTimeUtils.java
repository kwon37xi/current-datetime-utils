package kr.pe.kwonnam.currentdatetime;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public abstract class CurrentDateTimeUtils {

    private static volatile Clock clock;

    public static LocalDateTime now() {
        if (clock == null) {
            LocalDateTime.now();
        }
        return LocalDateTime.now(clock);
    }

    public static Instant instantNow() {
        if (clock == null) {
            return Instant.now();
        }
        return Instant.now(clock);
    }

    public static ZonedDateTime zonedNow() {
        if (clock == null) {
            return ZonedDateTime.now();
        }
        return ZonedDateTime.now(clock);
    }

    public static OffsetDateTime offsetNow() {
        if (clock == null) {
            return OffsetDateTime.now();
        }
        return OffsetDateTime.now(clock);
    }

    public static Date dateNow() {
        return Date.from(instantNow());
    }

    public static LocalDate today() {
        if (clock == null) {
            return LocalDate.now();
        }
        return LocalDate.now(clock);
    }

    public static Instant instantToday() {
        return instantNow().truncatedTo(ChronoUnit.DAYS);
    }

    public static ZonedDateTime zonedToday() {
        return zonedNow().truncatedTo(ChronoUnit.DAYS);
    }

    public static OffsetDateTime offsetToday() {
        return offsetNow().truncatedTo(ChronoUnit.DAYS);
    }

    public static Date dateToday() {
        return Date.from(instantToday());
    }

    static void fixWithClock(Clock clock) {
        if (clock == null) {
            throw new IllegalArgumentException("clock must not be null.");
        }
        CurrentDateTimeUtils.clock = clock;
    }

    static void unfixLocalDateTime() {
        clock = null;
    }
}
