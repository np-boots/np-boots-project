package cn.np.boots.common.utils.log;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class NpLogActionDebug extends NpLogAction {
    @Override
    public boolean isEnabled() {
        return log.isDebugEnabled();
    }

    @Override
    protected void doMsg(String msg) {
        log.debug(msg);
    }

    @Override
    protected void doMsg(Throwable throwable, String msg) {
        log.debug(msg, throwable);
    }
}
