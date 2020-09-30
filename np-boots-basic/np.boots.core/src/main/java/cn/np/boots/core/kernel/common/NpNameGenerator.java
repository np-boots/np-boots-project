package cn.np.boots.core.kernel.common;

public class NpNameGenerator {

    private final String prefix;

    public NpNameGenerator(String prefix){
        this.prefix = prefix;
    }

    public String generate(Class<?> clazz){
        return prefix + clazz.toString();
    }

    public static NpNameGenerator prefix(String prefix){
        return new NpNameGenerator(prefix);
    }
}
