package kr.pe.kwonnam.currentdatetime;

import org.junit.rules.ExternalResource;

import java.time.*;

// TODO support multithreaded tests
public class CurrentDateTimeUtilsRule extends ExternalResource {

    public void fixCurrentDateTime(Instant fixedInstant) {
        fixCurrentDateTime(fixedInstant, ZoneId.systemDefault());
    }

    public void fixCurrentDateTime(Instant fixedInstant, ZoneId zoneId) {
        CurrentDateTimeUtils.fixWithClock(Clock.fixed(fixedInstant, zoneId));
    }

    public void fixCurrentDateTime(ZonedDateTime fixedZonedDateTime) {
        fixCurrentDateTime(fixedZonedDateTime, fixedZonedDateTime.getZone());
    }

    public void fixCurrentDateTime(ZonedDateTime fixedZonedDateTime, ZoneId zoneId) {
        CurrentDateTimeUtils.fixWithClock(Clock.fixed(fixedZonedDateTime.toInstant(), zoneId));
    }

    public void fixCurrentDateTime(OffsetDateTime fixedOffsetDateTime) {
        fixCurrentDateTime(fixedOffsetDateTime, ZoneId.systemDefault());
    }

    public void fixCurrentDateTime(OffsetDateTime fixedOffsetDateTime, ZoneId zoneId) {
        CurrentDateTimeUtils.fixWithClock(Clock.fixed(fixedOffsetDateTime.toInstant(), zoneId));
    }

    public void fixCurrentDateTime(LocalDateTime fixedLocalDateTime) {
        CurrentDateTimeUtils.fixWithClock(Clock.fixed(ZonedDateTime.of(fixedLocalDateTime, ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault()));
    }

    @Override
    protected void after() {
        CurrentDateTimeUtils.unfixLocalDateTime();
    }
}
