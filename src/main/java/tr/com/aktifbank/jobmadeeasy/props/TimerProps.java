package tr.com.aktifbank.jobmadeeasy.props;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TimerProps {
    private int totalFireCount;
    private boolean runForever;
    private long printIntervalMs;
    private long initialOffsetMs;
    private String callbackData;
}
