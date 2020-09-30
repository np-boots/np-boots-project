package cn.np.boots.core.kernel.runtime2.root2.lifecycle.event;

import cn.np.boots.core.api.listener.*;
import lombok.Getter;

import java.util.TreeSet;

@Getter
public class NpRuntimeLifecycleEventHub {
    private static final NpRuntimeLifecycleEventHub instance = new NpRuntimeLifecycleEventHub();

    public static final NpRuntimeLifecycleEventHub getInstance() {
        return instance;
    }

    private TreeSet<NpRuntimeLifecycleCallback<? extends NpOnApplicationStartedListener>> onApplicationStartedCallback = new TreeSet<>();
    private TreeSet<NpRuntimeLifecycleCallback<? extends NpOnBeanDefinitionListener>> onBeanDefinitionCallback = new TreeSet<>();
    private TreeSet<NpRuntimeLifecycleCallback<? extends NpOnBeanFactoryPostListener>> onBeanFactoryPostCallback = new TreeSet<>();
    private TreeSet<NpRuntimeLifecycleCallback<? extends NpOnBeanPostListener>> onBeanPostCallback = new TreeSet<>();


    public NpRuntimeLifecycleEventHub registerOnApplicationStartedCallback(NpRuntimeLifecycleCallback<? extends NpOnApplicationStartedListener> callback) {
        this.onApplicationStartedCallback.add(callback);
        return this;
    }

    public NpRuntimeLifecycleEventHub notifyOnApplicationStartedCallback(NpOnApplicationStartedEvent event) {
        this.getOnApplicationStartedCallback().forEach(callback -> {
            callback.getCallbackBean().onApplicationStarted(event);
        });
        return this;
    }


    public NpRuntimeLifecycleEventHub registerOnBeanDefinitionCallback(NpRuntimeLifecycleCallback<? extends NpOnBeanDefinitionListener> callback) {
        this.onBeanDefinitionCallback.add(callback);
        return this;
    }

    public NpRuntimeLifecycleEventHub notifyOnBeanDefinitionCallback(NpOnBeanDefinitionEvent event) {
        this.getOnBeanDefinitionCallback().forEach(callback->{
            callback.getCallbackBean().onBeanDefinition(event);
        });
        return this;
    }



    public NpRuntimeLifecycleEventHub registerOnBeanFactoryPostCallback(NpRuntimeLifecycleCallback<? extends NpOnBeanFactoryPostListener> callback) {
        this.onBeanFactoryPostCallback.add(callback);
        return this;
    }

    public NpRuntimeLifecycleEventHub notifyOnBeanFactoryPostCallback(NpOnBeanFactoryPostEvent event) {
        this.getOnBeanFactoryPostCallback().forEach(callback->{
            callback.getCallbackBean().onBeanFactoryPost(event);
        });
        return this;
    }

    public NpRuntimeLifecycleEventHub registerOnBeanPostCallback(NpRuntimeLifecycleCallback<? extends NpOnBeanPostListener> callback) {
        this.onBeanPostCallback.add(callback);
        return this;
    }

    public NpRuntimeLifecycleEventHub notifyOnBeanPostCallback(NpOnBeanPostEvent event) {
        this.getOnBeanPostCallback().forEach(callback->{
            callback.getCallbackBean().onBeanPost(event);
        });
        return this;
    }
}
