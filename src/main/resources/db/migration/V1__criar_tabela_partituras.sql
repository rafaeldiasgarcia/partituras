CREATE TABLE partituras (
    id UUID PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    compositor VARCHAR(255) NOT NULL,
    instrumento VARCHAR(255) NOT NULL,
    nivel VARCHAR(20) NOT NULL,
    tom VARCHAR(100),
    arquivo_url VARCHAR(500),
    observacoes TEXT,
    criado_em TIMESTAMP WITH TIME ZONE NOT NULL,
    atualizado_em TIMESTAMP WITH TIME ZONE NOT NULL,
    CONSTRAINT ck_partituras_nivel
        CHECK (nivel IN ('INICIANTE', 'INTERMEDIARIO', 'AVANCADO'))
);
