package cn.np.boots.flow;

public interface NpFlowContext {
    <T> T get(String key);
    <T> void set(String key,T value);
}
