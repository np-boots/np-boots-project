package cn.np.boots.common.utils.serialize.json;

import cn.np.boots.common.api.NpJsonSerializeAction;
import cn.np.boots.common.utils.NpUtils;
import cn.np.boots.exception.NpRuntimeException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NpJacksonJsonSerializeActionAction implements NpJsonSerializeAction {
    private final ObjectMapper objectMapper;

    public NpJacksonJsonSerializeActionAction(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public <T> T deserialize(String content, Class<T> target, Class<?>... elements) {
        if(NpUtils.type().string().isEmpty(content)){
            return null;
        }
        try {
            return objectMapper.readValue(content,target);
        }catch (Exception e){
            NpUtils.log().error().msg(e,"json-deserialize error");
            return null;
        }

    }

    @Override
    public String serialize(Object data) {
        try {
            return objectMapper.writeValueAsString(data);
        } catch (Exception e) {
            throw new NpRuntimeException(e, "NP:np-json serialize failed");
        }
    }
}
