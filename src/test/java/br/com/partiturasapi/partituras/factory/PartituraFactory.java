package br.com.partiturasapi.partituras.factory;

import br.com.partiturasapi.partituras.entity.NivelPartitura;
import br.com.partiturasapi.partituras.entity.Partitura;
import br.com.partiturasapi.partituras.validator.PartituraNivelValidator;
import br.com.partiturasapi.partituras.validator.PartituraTituloValidator;
import br.com.partiturasapi.partituras.validator.PartituraValidator;
import br.com.partiturasapi.partituras.validator.PartituraValidatorService;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import org.mockito.Mockito;

public final class PartituraFactory {

    public static final UUID ID_PADRAO = UUID.fromString("11111111-1111-1111-1111-111111111111");
    public static final UUID ID_INEXISTENTE = UUID.fromString("22222222-2222-2222-2222-222222222222");
    public static final String TITULO_PADRAO = "Sonata em Dó";
    public static final String TITULO_ATUALIZADO = "Sonata em Dó Maior";
    public static final String COMPOSITOR_PADRAO = "Mozart";
    public static final String INSTRUMENTO_PADRAO = "Piano";
    public static final NivelPartitura NIVEL_PADRAO = NivelPartitura.INTERMEDIARIO;
    public static final NivelPartitura NIVEL_ATUALIZADO = NivelPartitura.AVANCADO;
    public static final String TOM_PADRAO = "C";
    public static final String ARQUIVO_URL_PADRAO = "https://exemplo.com/sonata.pdf";
    public static final String ARQUIVO_URL_ATUALIZADO = "https://exemplo.com/sonata-revisada.pdf";
    public static final String OBSERVACOES_PADRAO = "Versão para estudo";
    public static final String OBSERVACOES_ATUALIZADA = "Versão revisada";
    public static final String TITULO_EM_BRANCO = " ";
    public static final OffsetDateTime CRIADO_EM_PADRAO = OffsetDateTime.parse("2026-06-03T18:00:00Z");
    public static final OffsetDateTime ATUALIZADO_EM_PADRAO = OffsetDateTime.parse("2026-06-03T18:00:00Z");
    public static final OffsetDateTime ATUALIZADO_EM_REVISAO = OffsetDateTime.parse("2026-06-03T19:00:00Z");
    public static final String MENSAGEM_PARTITURA_NAO_ENCONTRADA = "Partitura não encontrada";
    public static final String MENSAGEM_TITULO_OBRIGATORIO = "Título da partitura é obrigatório";
    public static final String MENSAGEM_NIVEL_OBRIGATORIO = "Nível da partitura é obrigatório";

    private PartituraFactory() {
    }

    public static Partitura criar() {
        Partitura partitura = new Partitura();
        partitura.setId(ID_PADRAO);
        partitura.setTitulo(TITULO_PADRAO);
        partitura.setCompositor(COMPOSITOR_PADRAO);
        partitura.setInstrumento(INSTRUMENTO_PADRAO);
        partitura.setNivel(NIVEL_PADRAO);
        partitura.setTom(TOM_PADRAO);
        partitura.setArquivoUrl(ARQUIVO_URL_PADRAO);
        partitura.setObservacoes(OBSERVACOES_PADRAO);
        partitura.setCriadoEm(CRIADO_EM_PADRAO);
        partitura.setAtualizadoEm(ATUALIZADO_EM_PADRAO);
        return partitura;
    }

    public static UUID criarIdInexistente() {
        return ID_INEXISTENTE;
    }

    public static PartituraTituloValidator criarTituloValidator() {
        return new PartituraTituloValidator();
    }

    public static PartituraNivelValidator criarNivelValidator() {
        return new PartituraNivelValidator();
    }

    public static PartituraValidatorService criarValidatorService(PartituraValidator... validators) {
        return new PartituraValidatorService(List.of(validators));
    }

    public static List<Partitura> criarLista() {
        return List.of(criar());
    }

    public static PartituraValidator criarValidatorMock() {
        return Mockito.mock(PartituraValidator.class);
    }

    public static PartituraValidator[] criarValidatorsMockados(int quantidade) {
        PartituraValidator[] validators = new PartituraValidator[quantidade];
        for (int i = 0; i < quantidade; i++) {
            validators[i] = criarValidatorMock();
        }
        return validators;
    }

    public static Partitura criarSemTitulo() {
        Partitura partitura = criar();
        partitura.setTitulo(TITULO_EM_BRANCO);
        return partitura;
    }

    public static Partitura criarSemNivel() {
        Partitura partitura = criar();
        partitura.setNivel(null);
        return partitura;
    }

    public static Partitura criarAtualizada() {
        Partitura partitura = criar();
        partitura.setTitulo(TITULO_ATUALIZADO);
        partitura.setNivel(NIVEL_ATUALIZADO);
        partitura.setArquivoUrl(ARQUIVO_URL_ATUALIZADO);
        partitura.setObservacoes(OBSERVACOES_ATUALIZADA);
        partitura.setAtualizadoEm(ATUALIZADO_EM_REVISAO);
        return partitura;
    }
}
