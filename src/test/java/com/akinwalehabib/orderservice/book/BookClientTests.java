package com.akinwalehabib.orderservice.book;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class BookClientTests {
  private MockWebServer mockWebServer;
  private BookClient bookClient;

  @BeforeEach
  void setUp() throws IOException {
    this.mockWebServer = new MockWebServer();
    this.mockWebServer.start();

    var webClient = WebClient.builder()
      .baseUrl(this.mockWebServer.url("/").uri().toString())
      .build();
    this.bookClient = new BookClient(webClient);
  }

  @AfterEach
  void clean() throws IOException {
    mockWebServer.shutdown();
  }

	@Disabled
  @Test
	void whenBookExistsThenReturnBook() {
		var bookIsbn = "1234567890";

		var mockResponse = new MockResponse()
				.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.setBody("""
							{
								"isbn": %s,
								"title": "Title",
								"author": "Author",
								"price": 9.90,
								"publisher": "Polarsophia"
							}
						""".formatted(bookIsbn));

		mockWebServer.enqueue(mockResponse);

		Mono<Book> book = bookClient.getBookByIsbn(bookIsbn);

		StepVerifier.create(book)
				.expectNextMatches(b -> b.isbn().equals(bookIsbn))
				.verifyComplete();
	}

}
