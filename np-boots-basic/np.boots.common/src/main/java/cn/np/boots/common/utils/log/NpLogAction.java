package cn.np.boots.common.utils.log;

import cn.np.boots.common.pattern.lamda.FunctionVoid;

import java.util.function.Supplier;

public abstract class NpLogAction {

    public NpLogAction(){

    }

    public abstract boolean isEnabled();
    protected abstract void doMsg(String msg);
    protected abstract void doMsg(Throwable throwable,String msg);

    public void msg(String msg){
        if(this.isEnabled()){
            this.doMsg(msg);
        }
    }

    public void msg(String msgFormat,Object... args){
        if(this.isEnabled()){
            this.doMsg(String.format(msgFormat,args));
        }
    }

    public void msg(Supplier<String> msgSupplier){
        if(this.isEnabled()){
            this.doMsg(msgSupplier.get());
        }
    }

    public void msg(Throwable throwable,String msg) {
        this.doMsg(throwable, msg);
    }

    public void msg(Throwable throwable,String msgFormat,Object... args){
        this.msg(throwable,String.format(msgFormat,args));
    }


    public void surround(String title, FunctionVoid execute) {
        if(this.isEnabled()){
            this.doMsg(title+"-beginning");
        }

        try {
            execute.execute();
        } catch (Exception e) {
            this.doMsg(e,title+"-beginning");
            throw e;
        }

        if(this.isEnabled()){
            this.doMsg(title+"-ended");
        }
    }
}
