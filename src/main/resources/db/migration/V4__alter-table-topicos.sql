ALTER TABLE Topicos
ADD CONSTRAINT unique_titulo UNIQUE (titulo);

ALTER TABLE Topicos
ADD CONSTRAINT unique_mensaje UNIQUE (mensaje);
