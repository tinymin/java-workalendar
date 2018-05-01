package workalendar.core;

import static workalendar.util.Easter.EASTER;

public class OrthodoxMixin extends ChristianMixin {
    public OrthodoxMixin() {
        this.setEasterMethod(EASTER.ORTHODOX);
    }
}
