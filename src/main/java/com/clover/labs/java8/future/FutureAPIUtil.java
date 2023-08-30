package com.clover.labs.java8.future;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FutureAPIUtil {

    public static void main(String[] args) throws Exception {
        System.out.println();
        System.out.println("#\tsupplyAsync() \t\t\t-> " + supplyAsync().get());
        System.out.println("#\tthenApplyAsync() \t\t-> " + thenApplyAsync().get());
        System.out.print(" (" + thenAcceptAsync().get() + ")\n");
        System.out.println("#\tthenComposeAsync() \t\t-> " + thenComposeAsync().get());
        System.out.println("#\tthenCombineAsync() \t\t-> " + thenCombineAsync().get());
        System.out.print(" (" + thenAcceptBothAsync().get() + ")\n");
        System.out.println("#\tallOf() \t\t\t\t-> " + allOf().get());
        System.out.println("#\thandleErrors(null) \t\t-> " + handleErrors(null).get());
        System.out.println("#\thandleErrors(World) \t-> " + handleErrors("World").get());
        System.out.print("#\ttestPredicate() \t\t-> " + testPredicate());

        System.out.println();
    }


    // provide an instance of the Supplier as a lambda expression that does the
    // calculation and returns the result
    public static CompletableFuture<String> supplyAsync() {
        return CompletableFuture.supplyAsync(() -> "Hello World");
    }

    // process the result of a computation is to feed it to a function and returns a
    // Future
    public static CompletableFuture<String> thenApplyAsync() {
        return CompletableFuture.supplyAsync(() -> "Hello").thenApplyAsync(s -> s + " World");
    }

    // don't need to return a value the Future chain
    public static CompletableFuture<Void> thenAcceptAsync() {
        return thenApplyAsync().thenAcceptAsync(s -> System.out.print("#\tthenAcceptAsync() \t\t-> " + s + " - Void"));
    }

    // Combining Futures
    public static CompletableFuture<String> thenComposeAsync() {
        return CompletableFuture.supplyAsync(() -> "Hello")
                .thenComposeAsync(s -> CompletableFuture.supplyAsync(() -> s + " World"));
    }

    // two independent Futures and do something with their results
    public static CompletableFuture<String> thenCombineAsync() {
        return CompletableFuture.supplyAsync(() -> "Hello")
                .thenCombineAsync(CompletableFuture.supplyAsync(() -> " World"), (s1, s2) -> s1 + s2);
    }

    // two independent Futures and do something with their results, but return
    // nothing
    public static CompletableFuture<Void> thenAcceptBothAsync() {
        return CompletableFuture.supplyAsync(() -> "Hello")
                .thenAcceptBothAsync(CompletableFuture.supplyAsync(() -> " World"),
                        (s1, s2) -> System.out.print("#\tthenAcceptBothAsync() \t-> " + s1 + s2 + " - Void"));
    }

    // Running Multiple Futures in Parallel
    public static CompletableFuture<String> allOf() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hell");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "o W");
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "orld");

        return CompletableFuture.completedFuture(Stream.of(future1, future2, future3)
                .map(CompletableFuture::join)
                .collect(Collectors.joining("")));
    }

    // Handling Errors
    public static CompletableFuture<String> handleErrors(String name) {
        return CompletableFuture.supplyAsync(() -> {
            if (name == null) {
                throw new RuntimeException("Computation error!");
            }
            return "Hello " + name;
        }).handle((s, t) -> s != null ? s : "Hello World");
    }

    public static boolean testPredicate() {
        Predicate<Task> isMerchantSurchargeEnabled = d -> Optional.ofNullable(d.getIsResponseReceived()).filter(r -> r != null).isPresent();

        return isMerchantSurchargeEnabled.test(new Task("a", "b", Boolean.FALSE));
    }
}


@NoArgsConstructor
@AllArgsConstructor
@Data
class Task {

    private String request;

    private String response;

    private Boolean isResponseReceived;

}