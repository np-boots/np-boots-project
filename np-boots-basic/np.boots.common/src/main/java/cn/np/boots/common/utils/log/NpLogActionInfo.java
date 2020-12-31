package cn.np.boots.common.utils.log;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class NpLogActionInfo extends NpLogAction {
    @Override
    public boolean isEnabled() {
        return log.isInfoEnabled();
    }

    @Override
    protected void doMsg(String msg) {
        log.info(msg);
    }

    @Override
    protected void doMsg(Throwable throwable, String msg) {
        log.info(msg, throwable);
    }
}
