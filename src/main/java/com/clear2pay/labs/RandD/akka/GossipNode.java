package com.clover.RandD.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GossipNode extends AbstractActor {
    private final String nodeId;
    private final Map<String, ActorRef> nodes;
    private final List<String> processedNodes;

    public GossipNode(String nodeId, Map<String, ActorRef> nodes) {
        this.nodeId = nodeId;
        this.nodes = nodes;
        this.processedNodes = new ArrayList<>();
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, message -> {
                    System.out.println("Node " + nodeId + " received: " + message);
                    // Gossip protocol logic here, e.g., propagate the message to other nodes
                    gossipMessageToOtherNodes(message);
                })
                .build();
    }

    private void gossipMessageToOtherNodes(String message) {
        // Here, we assume that the ActorRefs of other nodes are stored in the 'nodes' map
        // and the node's own ActorRef is excluded from the list
        for (Map.Entry<String, ActorRef> entry : nodes.entrySet()) {
            String targetNodeId = entry.getKey();
            ActorRef targetNodeRef = entry.getValue();

            if (!targetNodeId.equals(nodeId) && !processedNodes.contains(targetNodeId)) {
                // Use 'tell' to send the message to the target node's actor
                targetNodeRef.tell("[Forwarding]"+message, getSelf());
                processedNodes.add(targetNodeId);
            }
        }
    }

    public static Props props(String nodeId, Map<String, ActorRef> nodes) {
        return Props.create(GossipNode.class, nodeId, nodes);
    }
}