package tr.com.aktifbank.jobmadeeasy.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TimerProperties {
    private int totalFireCount;
    private int remainingFireCount;
    private boolean runForever;
    private long printIntervalMs;
    private long initialOffsetMs;
    private String callbackData;
}
