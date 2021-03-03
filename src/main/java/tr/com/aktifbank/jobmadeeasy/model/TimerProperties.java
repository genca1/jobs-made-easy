package tr.com.aktifbank.jobmadeeasy.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TimerProperties {
    @Id
    private int Id;
    private int totalFireCount;
    private int remainingFireCount;
    private boolean runForever;
    private long printIntervalMs;
    private long initialOffsetMs;
    private String callbackData;
}
