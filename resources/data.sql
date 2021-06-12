
insert into categorias (categoria_id, nombre, descripcion) values (
1, "Seinen", "Gore, Adults");

INSERT INTO mangas (manga_id, nombre, autor, anio_publicacion, capitulos_disponibles, categoria_id)
VALUES (1, "Berserk", "Kentaro Miura", 1989, 363, 1);


insert into mangas_capitulos (capitulo_id, manga_id, nombre, fecha_publicacion,total_paginas)values(
1, 1, "The Black Swordsman", "1990-09-26", 12);

insert into mangas_capitulos_paginas (pagina_id, capitulo_id, image_url) values (
1, 1, "Berserk_1_004.jpg"

);
insert into mangas_capitulos_paginas (pagina_id, capitulo_id, image_url) values (
2, 1, "Berserk_1_005.jpg"

);
insert into mangas_capitulos_paginas (pagina_id, capitulo_id, image_url) values (
3, 1, "Berserk_1_006.jpg"

);
insert into mangas_capitulos_paginas (pagina_id, capitulo_id, image_url) values (
4, 1, "Berserk_1_007.jpg"

);
