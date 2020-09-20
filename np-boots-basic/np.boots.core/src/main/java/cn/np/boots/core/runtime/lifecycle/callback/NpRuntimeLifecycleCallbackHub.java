package cn.np.boots.core.runtime.lifecycle.callback;

import cn.np.boots.core.api.listener.*;
import lombok.Getter;

import java.util.TreeSet;

@Getter
public class NpRuntimeLifecycleCallbackHub {
    private static final NpRuntimeLifecycleCallbackHub instance = new NpRuntimeLifecycleCallbackHub();

    public static final NpRuntimeLifecycleCallbackHub getInstance() {
        return instance;
    }

    private TreeSet<NpRuntimeLifecycleCallback<? extends NpOnApplicationStartedListener>> onApplicationStartedCallback = new TreeSet<>();
    private TreeSet<NpRuntimeLifecycleCallback<? extends NpOnBeanDefinitionListener>> onBeanDefinitionCallback = new TreeSet<>();
    private TreeSet<NpRuntimeLifecycleCallback<? extends NpOnBeanFactoryPostListener>> onBeanFactoryPostCallback = new TreeSet<>();
    private TreeSet<NpRuntimeLifecycleCallback<? extends NpOnBeanPostListener>> onBeanPostCallback = new TreeSet<>();


    public NpRuntimeLifecycleCallbackHub registerOnApplicationStartedCallback(NpRuntimeLifecycleCallback<? extends NpOnApplicationStartedListener> callback) {
        this.onApplicationStartedCallback.add(callback);
        return this;
    }

    public NpRuntimeLifecycleCallbackHub notifyOnApplicationStartedCallback(NpOnApplicationStartedEvent event) {
        this.getOnApplicationStartedCallback().forEach(callback -> {
            callback.getCallbackBean().onApplicationStarted(event);
        });
        return this;
    }


    public NpRuntimeLifecycleCallbackHub registerOnBeanDefinitionCallback(NpRuntimeLifecycleCallback<? extends NpOnBeanDefinitionListener> callback) {
        this.onBeanDefinitionCallback.add(callback);
        return this;
    }

    public NpRuntimeLifecycleCallbackHub notifyOnBeanDefinitionCallback(NpOnBeanDefinitionEvent event) {
        this.getOnBeanDefinitionCallback().forEach(callback->{
            callback.getCallbackBean().onBeanDefinition(event);
        });
        return this;
    }



    public NpRuntimeLifecycleCallbackHub registerOnBeanFactoryPostCallback(NpRuntimeLifecycleCallback<? extends NpOnBeanFactoryPostListener> callback) {
        this.onBeanFactoryPostCallback.add(callback);
        return this;
    }

    public NpRuntimeLifecycleCallbackHub notifyOnBeanFactoryPostCallback(NpOnBeanFactoryPostEvent event) {
        this.getOnBeanFactoryPostCallback().forEach(callback->{
            callback.getCallbackBean().onBeanFactoryPost(event);
        });
        return this;
    }

    public NpRuntimeLifecycleCallbackHub registerOnBeanPostCallback(NpRuntimeLifecycleCallback<? extends NpOnBeanPostListener> callback) {
        this.onBeanPostCallback.add(callback);
        return this;
    }

    public NpRuntimeLifecycleCallbackHub notifyOnBeanPostCallback(NpOnBeanPostEvent event) {
        this.getOnBeanPostCallback().forEach(callback->{
            callback.getCallbackBean().onBeanPost(event);
        });
        return this;
    }
}
