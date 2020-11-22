package org.mpatapenka.issuetracker.api.service;

import java.time.Clock;

@FunctionalInterface
public interface ClockProvider {

    Clock getClock();
}