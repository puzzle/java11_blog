package ch.puzzle.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

import ch.puzzle.http.util.ResponseHelper;

/**
 * Make an asynchronous http GET request using the Java 9 httpClient API.
 */
public class HelloHttpAsync {

	public static void readPageAsynchronousHttp(String pageUrl) throws Exception {

		CompletableFuture<HttpResponse<String>> asyncResponse = HttpClient.newHttpClient().sendAsync( //
				HttpRequest.newBuilder(new URI(pageUrl)).GET().build(), //
				HttpResponse.BodyHandlers.ofString());

		// version 1
		// asyncResponse.thenAccept(response ->
		// System.out.println(ResponseHelper.toShortString(response)));

		// version 2
		asyncResponse //
				.thenApply(response -> ResponseHelper.toShortString(response)) //
				.thenAccept(System.out::println);
	}

	public static void main(final String[] args) throws Exception {
		System.out.println("\n--- read page by url http 2 -------------");
		readPageAsynchronousHttp("http://www.puzzle.ch/de/");

		for (int i = 0; i < 10; i++) {
			System.out.println(".");
			Thread.sleep(300);
		}
	}
}
