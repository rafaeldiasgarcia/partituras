package br.com.partiturasapi.partituras.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.partiturasapi.partituras.dto.AtualizarPartituraRequest;
import br.com.partiturasapi.partituras.dto.AtualizarPartituraResponse;
import br.com.partiturasapi.partituras.dto.CriarPartituraRequest;
import br.com.partiturasapi.partituras.dto.CriarPartituraResponse;
import br.com.partiturasapi.partituras.dto.DetalharPartituraResponse;
import br.com.partiturasapi.partituras.dto.ListarPartituraResponse;
import br.com.partiturasapi.partituras.factory.PartituraFactory;
import br.com.partiturasapi.partituras.factory.PartituraRequestFactory;
import br.com.partiturasapi.partituras.factory.PartituraResponseFactory;
import br.com.partiturasapi.partituras.service.AtualizarPartituraService;
import br.com.partiturasapi.partituras.service.CriarPartituraService;
import br.com.partiturasapi.partituras.service.DetalharPartituraService;
import br.com.partiturasapi.partituras.service.ExcluirPartituraService;
import br.com.partiturasapi.partituras.service.ListarPartituraService;
import br.com.partiturasapi.shared.exception.GlobalExceptionHandler;
import br.com.partiturasapi.shared.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = PartiturasController.class)
@Import(GlobalExceptionHandler.class)
class PartiturasControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CriarPartituraService criarPartituraService;

    @MockBean
    private AtualizarPartituraService atualizarPartituraService;

    @MockBean
    private DetalharPartituraService detalharPartituraService;

    @MockBean
    private ListarPartituraService listarPartituraService;

    @MockBean
    private ExcluirPartituraService excluirPartituraService;

    @Test
    void deveCriarPartitura() throws Exception {
        CriarPartituraRequest request = PartituraRequestFactory.criarRequest();
        CriarPartituraResponse response = PartituraResponseFactory.criarResponse();

        when(criarPartituraService.criar(any(CriarPartituraRequest.class))).thenReturn(response);

        mockMvc.perform(post("/partituras")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", "/partituras/" + PartituraFactory.ID_PADRAO))
            .andExpect(jsonPath("$.id").value(PartituraFactory.ID_PADRAO.toString()))
            .andExpect(jsonPath("$.titulo").value(PartituraFactory.TITULO_PADRAO))
            .andExpect(jsonPath("$.compositor").value(PartituraFactory.COMPOSITOR_PADRAO));
    }

    @Test
    void deveRetornarBadRequestAoCriarComPayloadInvalido() throws Exception {
        String payloadInvalido = """
            {
              "titulo": "",
              "compositor": "Mozart",
              "instrumento": "Piano",
              "nivel": "INTERMEDIARIO"
            }
            """;

        mockMvc.perform(post("/partituras")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadInvalido))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.message").value("Payload inválido"))
            .andExpect(jsonPath("$.details[0]").value("titulo é obrigatório"));
    }

    @Test
    void deveListarPartituras() throws Exception {
        List<ListarPartituraResponse> response = List.of(PartituraResponseFactory.listarResponse());

        when(listarPartituraService.listar()).thenReturn(response);

        mockMvc.perform(get("/partituras"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(PartituraFactory.ID_PADRAO.toString()))
            .andExpect(jsonPath("$[0].titulo").value(PartituraFactory.TITULO_PADRAO));
    }

    @Test
    void deveDetalharPartitura() throws Exception {
        DetalharPartituraResponse response = PartituraResponseFactory.detalharResponse();

        when(detalharPartituraService.detalhar(PartituraFactory.ID_PADRAO)).thenReturn(response);

        mockMvc.perform(get("/partituras/{id}", PartituraFactory.ID_PADRAO))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(PartituraFactory.ID_PADRAO.toString()))
            .andExpect(jsonPath("$.titulo").value(PartituraFactory.TITULO_PADRAO));
    }

    @Test
    void deveRetornarNotFoundAoDetalharPartituraInexistente() throws Exception {
        when(detalharPartituraService.detalhar(PartituraFactory.ID_INEXISTENTE))
            .thenThrow(new ResourceNotFoundException(PartituraFactory.MENSAGEM_PARTITURA_NAO_ENCONTRADA));

        mockMvc.perform(get("/partituras/{id}", PartituraFactory.ID_INEXISTENTE))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.message").value(PartituraFactory.MENSAGEM_PARTITURA_NAO_ENCONTRADA));
    }

    @Test
    void deveAtualizarPartitura() throws Exception {
        AtualizarPartituraRequest request = PartituraRequestFactory.atualizarRequest();
        AtualizarPartituraResponse response = PartituraResponseFactory.atualizarResponse();

        when(atualizarPartituraService.atualizar(eq(PartituraFactory.ID_PADRAO), any(AtualizarPartituraRequest.class)))
            .thenReturn(response);

        mockMvc.perform(put("/partituras/{id}", PartituraFactory.ID_PADRAO)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(PartituraFactory.ID_PADRAO.toString()))
            .andExpect(jsonPath("$.titulo").value(PartituraFactory.TITULO_ATUALIZADO))
            .andExpect(jsonPath("$.nivel").value(PartituraFactory.NIVEL_ATUALIZADO.toString()));
    }

    @Test
    void deveExcluirPartitura() throws Exception {
        doNothing().when(excluirPartituraService).excluir(PartituraFactory.ID_PADRAO);

        mockMvc.perform(delete("/partituras/{id}", PartituraFactory.ID_PADRAO))
            .andExpect(status().isNoContent());

        verify(excluirPartituraService).excluir(PartituraFactory.ID_PADRAO);
    }
}
