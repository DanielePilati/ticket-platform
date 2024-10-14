package org.web.app.java.spring.platform.ticket;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class TicketRestControllerTest {

	@Autowired
	private MockMvc mocMvc;

	@Test
	void isEmptyCategory() throws Exception {
		try {
			mocMvc.perform(get("/api/tickets/category/{type}", "Other")).andExpect(status().isNoContent())
					.andDo(print());
		} catch (Exception exception) {
			System.out.println("Something went wrong. Exception: " + exception);
		}
	}

	@Test
	void notFoundCategory() throws Exception {
		try {
			mocMvc.perform(get("/api/tickets/category/{type}", "prova")).andExpect(status().isBadRequest())
					.andDo(print());
		} catch (Exception exception) {
			System.out.println("Something went wrong. Exception: " + exception);
		}
	}

	@Test
	void thereIsCategory() throws Exception {
		try {
			mocMvc.perform(get("/api/tickets/category/{type}", "Assistance")).andExpect(status().isOk()).andDo(print());
		} catch (Exception exception) {
			System.out.println("Something went wrong. Exception: " + exception);
		}
	}

	@Test
	void isEmptyStatus() throws Exception {
		try {
			mocMvc.perform(get("/api/tickets/state/{state}", "ToDo")).andExpect(status().isNoContent()).andDo(print());
		} catch (Exception exception) {
			System.out.println("Something went wrong. Exception: " + exception);
		}
	}

	@Test
	void notFoundStatus() throws Exception {
		try {
			mocMvc.perform(get("/api/tickets/state/{state}", "prova")).andExpect(status().isBadRequest())
					.andDo(print());
		} catch (Exception exception) {
			System.out.println("Something went wrong. Exception: " + exception);
		}
	}

	@Test
	void thereIsStatus() throws Exception {
		try {
			mocMvc.perform(get("/api/tickets/state/{state}", "Completed")).andExpect(status().isOk()).andDo(print());
		} catch (Exception exception) {
			System.out.println("Something went wrong. Exception: " + exception);
		}
	}

}
