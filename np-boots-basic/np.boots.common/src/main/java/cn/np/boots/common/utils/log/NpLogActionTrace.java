package cn.np.boots.common.utils.log;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class NpLogActionTrace extends NpLogAction {
    @Override
    public boolean isEnabled() {
        return log.isTraceEnabled();
    }

    @Override
    protected void doMsg(String msg) {
        log.trace(msg);
    }

    @Override
    protected void doMsg(Throwable throwable, String msg) {
        log.trace(msg, throwable);
    }
}
