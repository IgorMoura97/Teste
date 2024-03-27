/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.restservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

// Classe de teste para testar o controlador GreetingController
@SpringBootTest
@AutoConfigureMockMvc
public class GreetingControllerTests {

	@Autowired
	private MockMvc mockMvc; // MockMvc é uma classe fornecida pelo Spring para testar controllers MVC

	// Teste para verificar se a mensagem padrão é retornada quando nenhum parâmetro é fornecido
	@Test
	public void noParamGreetingShouldReturnDefaultMessage() throws Exception {

		this.mockMvc.perform(get("/greeting")) // Realiza uma solicitação GET para /greeting
				.andDo(print()) // Imprime detalhes da solicitação e resposta no console
				.andExpect(status().isOk()) // Verifica se o status da resposta é OK (200)
				.andExpect(jsonPath("$.content").value("Hello, World!")); // Verifica se o conteúdo da resposta contém a mensagem esperada
	}

	// Teste para verificar se uma mensagem personalizada é retornada quando um parâmetro é fornecido
	@Test
	public void paramGreetingShouldReturnTailoredMessage() throws Exception {

		this.mockMvc.perform(get("/greeting").param("name", "Spring Community")) // Realiza uma solicitação GET para /greeting com o parâmetro 'name' como 'Spring Community'
				.andDo(print()) // Imprime detalhes da solicitação e resposta no console
				.andExpect(status().isOk()) // Verifica se o status da resposta é OK (200)
				.andExpect(jsonPath("$.content").value("Hello, Spring Community!")); // Verifica se o conteúdo da resposta contém a mensagem personalizada esperada
	}

}