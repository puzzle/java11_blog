package ch.puzzle.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;

import ch.puzzle.http.util.ResponseHelper;
import ch.puzzle.http.util.SimpleResponseHelper;

/**
 * Basic usage of the Java 11 httpClient API.
 *
 * <li>make an http GET request</li>
 * <li>download an html page</li>
 */
public class HelloHttp {

	public static void helloHttp(String pageUrl) throws Exception {
		HttpRequest request = HttpRequest.newBuilder(new URI(pageUrl)) //
				.GET() //
				.build();

		HttpResponse<String> response = HttpClient.newHttpClient().send( //
				request, //
				HttpResponse.BodyHandlers.ofString());

		System.out.println(SimpleResponseHelper.toString(response));
	}

	public static void readPageSynchronousHttp1(String pageUrl) throws Exception {
		HttpResponse<String> response = HttpClient.newHttpClient().send( //
				HttpRequest.newBuilder(new URI(pageUrl)).GET().build(), //
				HttpResponse.BodyHandlers.ofString());

		System.out.println(ResponseHelper.toShortString(response));
	}

	public static void downloadPageSynchronous(String pageUrl) throws Exception {
		HttpResponse<Path> response = HttpClient.newHttpClient().send( //
				HttpRequest.newBuilder(new URI(pageUrl)).GET().build(), //
				HttpResponse.BodyHandlers.ofFile(Paths.get("my-test.html")));

		System.out.println(ResponseHelper.toShortString(response));
	}

	public static void main(final String[] args) throws Exception {
		System.out.println("\n--- hello world  ------------------------");
		helloHttp("https://www.puzzle.ch/de/");

		System.out.println("\n--- read page by url http 1.1 -----------");
		readPageSynchronousHttp1("https://www.puzzle.ch/de/");

		System.out.println("\n--- download file by url ----------------");
		downloadPageSynchronous("https://www.puzzle.ch/de/");
	}
}
