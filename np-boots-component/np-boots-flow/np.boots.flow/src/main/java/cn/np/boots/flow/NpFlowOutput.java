package cn.np.boots.flow;

import lombok.Getter;

@Getter
public class NpFlowOutput<Node, TData> {
    private boolean isSuccess;
    private Node node;
    private TData data;

    public NpFlowOutput<Node, TData> suc(Node node, TData data) {
        this.isSuccess = isSuccess;
        this.node = node;
        this.data = data;
        return this;
    }

    public static <Node, TData> NpFlowOutput<Node, TData> success(Node node, TData data) {
        return new NpFlowOutput<Node,TData>().suc(node,data);
    }

    @Override
    public String toString() {
        return "NpFlowOutput{" +
                "isSuccess=" + isSuccess +
                ", node=" + node +
                ", data=" + data +
                '}';
    }
}
