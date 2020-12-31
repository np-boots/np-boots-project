package cn.np.boots.common.utils.log;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class NpLogActionError extends NpLogAction {
    @Override
    public boolean isEnabled() {
        return log.isErrorEnabled();
    }

    @Override
    protected void doMsg(String msg) {
        log.error(msg);
    }

    @Override
    protected void doMsg(Throwable throwable, String msg) {
        log.error(msg, throwable);
    }
}
