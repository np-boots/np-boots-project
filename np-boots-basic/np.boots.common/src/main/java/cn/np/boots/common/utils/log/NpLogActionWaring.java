package cn.np.boots.common.utils.log;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class NpLogActionWaring extends NpLogAction {
    @Override
    public boolean isEnabled() {
        return log.isWarnEnabled();
    }

    @Override
    protected void doMsg(String msg) {
        log.warn(msg);
    }

    @Override
    protected void doMsg(Throwable throwable, String msg) {
        log.warn(msg, throwable);
    }
}
