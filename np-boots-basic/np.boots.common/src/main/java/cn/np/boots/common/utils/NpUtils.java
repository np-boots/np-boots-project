package cn.np.boots.common.utils;

import cn.np.boots.common.utils.log.NpLogUtils;
import cn.np.boots.common.utils.reflect.NpReflectUtils;
import cn.np.boots.common.utils.serialize.NpSerializeUtils;
import cn.np.boots.common.utils.tool.NpToolUtils;
import cn.np.boots.common.utils.type.NpTypeUtils;
import cn.np.boots.common.utils.web.NpWebUtils;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class NpUtils {
    private static final NpReflectUtils reflect = new NpReflectUtils();

    public static NpReflectUtils reflect() {
        return reflect;
    }

    private static final NpToolUtils tools = new NpToolUtils();

    public static NpToolUtils tools() {
        return tools;
    }

    private static final NpTypeUtils type = new NpTypeUtils();

    public static NpTypeUtils type() {
        return type;
    }

    private static final NpWebUtils web = new NpWebUtils();

    public static NpWebUtils web() {
        return web;
    }

    private static final NpSerializeUtils serialize = new NpSerializeUtils();

    public static NpSerializeUtils serialize() {
        return serialize;
    }

    private static final NpLogUtils log = new NpLogUtils();

    public static NpLogUtils log() {
        return log;
    }
}
