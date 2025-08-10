ALTER TABLE topicos
	DROP INDEX titulo,
	DROP INDEX mensaje,
	ADD CONSTRAINT unique_titulo_mensaje UNIQUE (titulo, mensaje);