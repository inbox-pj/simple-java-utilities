package com.clover.labs.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import java.util.HashMap;
import java.util.Map;

public class GossipApplication {
    public static void main(String[] args) {
        // Create the ActorSystem
        ActorSystem system = ActorSystem.create("GossipSystem");
        Map<String, ActorRef> nodes = new HashMap<>();

        int nodeCount = 5;
        for (int i = 1; i < nodeCount; i++) {
            nodes.put("Node-"+ i, system.actorOf(GossipNode.props("Node-"+ i, nodes), "node"+i));
        }
        for(ActorRef node : nodes.values()) {
            node.tell("[Message] Hello from Node-"+node, ActorRef.noSender());
        }

        // Shutdown the ActorSystem
        system.terminate();
    }
}