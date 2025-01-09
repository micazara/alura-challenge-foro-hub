CREATE TABLE Topicos (
    id bigint AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    mensaje VARCHAR(255) NOT NULL,
    fechaCreacion DATE NOT NULL,
    status TINYINT(1) NOT NULL,
    autor bigint NOT NULL,
    curso bigint NOT NULL,
    FOREIGN KEY (autor) REFERENCES Usuarios(id),
    FOREIGN KEY (curso) REFERENCES Cursos(id)
);