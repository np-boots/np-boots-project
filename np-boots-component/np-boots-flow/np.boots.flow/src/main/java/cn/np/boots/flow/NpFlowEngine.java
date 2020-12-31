package cn.np.boots.flow;

public interface NpFlowEngine<Node,Relation,In,Ctx> {
    void register(Node from,Node to,Relation relation);
}
