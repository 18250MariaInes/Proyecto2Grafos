
create table generos (correlativo int identity, nombre varchar(50))
go
create table peliculas (correlativo int identity, nombre varchar(100), release varchar(10), corre_generos int)
go
create table artistas (correlativo int identity, nombre varchar(50))
go
create table peliculasartistas (correlativo int identity, corre_peliculas int, corre_artistas int)
go

truncate table global
go
truncate table artistas
go
truncate table generos
go
truncate table peliculas
go
truncate table peliculasartistas
go

create table global (genero varchar(50), nombre varchar(100), release varchar(10), artista1 varchar(50) null,
                     artista2 varchar(50) null,artista3 varchar(50) null,artista4 varchar(50) null,artista5 varchar(50) null,artista6 varchar(50) null,
                     artista7 varchar(50) null,artista8 varchar(50) null,artista9 varchar(50) null,artista10 varchar(50) null,artista11 varchar(50) null,
                     artista12 varchar(50) null,artista13 varchar(50) null,artista14 varchar(50) null,artista15 varchar(50) null,artista16 varchar(50) null,
                     artista17 varchar(50) null,artista18 varchar(50) null,artista19 varchar(50) null,artista20 varchar(50) null,artista21 varchar(50) null,
                     artista22 varchar(50) null,artista23 varchar(50) null,artista24 varchar(50) null,artista25 varchar(50) null,artista26 varchar(50) null,
                     artista27 varchar(50) null,artista28 varchar(50) null,artista29 varchar(50) null,artista30 varchar(50) null,artista31 varchar(50) null,
                     artista32 varchar(50) null,artista33 varchar(50) null,artista34 varchar(50) null,artista35 varchar(50) null,artista36 varchar(50) null,
                     artista37 varchar(50) null,artista38 varchar(50) null,artista39 varchar(50) null,artista40 varchar(50) null,artista41 varchar(50) null,
                     artista42 varchar(50) null,artista43 varchar(50) null,artista44 varchar(50) null,artista45 varchar(50) null)
go                     

select * from global
go

/* Genera Generos */

declare @genero varchar(50), @nombre varchar(100), @release varchar(10), @artista1 varchar(50),
        @artista2 varchar(50) ,@artista3 varchar(50) ,@artista4 varchar(50) ,@artista5 varchar(50) ,@artista6 varchar(50) ,
        @artista7 varchar(50) ,@artista8 varchar(50) ,@artista9 varchar(50) ,@artista10 varchar(50) ,@artista11 varchar(50) ,
                     @artista12 varchar(50) ,@artista13 varchar(50) ,@artista14 varchar(50) ,@artista15 varchar(50) ,@artista16 varchar(50) ,
                     @artista17 varchar(50) ,@artista18 varchar(50) ,@artista19 varchar(50) ,@artista20 varchar(50) ,@artista21 varchar(50) ,
                     @artista22 varchar(50) ,@artista23 varchar(50) ,@artista24 varchar(50) ,@artista25 varchar(50) ,@artista26 varchar(50) ,
                     @artista27 varchar(50) ,@artista28 varchar(50) ,@artista29 varchar(50) ,@artista30 varchar(50) ,@artista31 varchar(50) ,
                     @artista32 varchar(50) ,@artista33 varchar(50) ,@artista34 varchar(50) ,@artista35 varchar(50) ,@artista36 varchar(50) ,
                     @artista37 varchar(50) ,@artista38 varchar(50) ,@artista39 varchar(50) ,@artista40 varchar(50) ,@artista41 varchar(50) ,
                     @artista42 varchar(50) ,@artista43 varchar(50) ,@artista44 varchar(50) ,@artista45 varchar(50)

declare @genero_cursor as cursor
set @genero_cursor = cursor static
  for select ltrim(rtrim(genero)) from global
open @genero_cursor
fetch next from @genero_cursor into @genero
while @@fetch_status = 0
  begin
    if not exists (select 1 from generos where ltrim(rtrim(nombre)) = @genero )
       begin
         insert generos (nombre)
            values (@genero)
       end
    fetch next from @genero_cursor into @genero
  end
close @genero_cursor
deallocate @genero_cursor
go
select * from generos
go
/* Genera Peliculas */

declare @genero varchar(50), @nombre varchar(100), @release varchar(10), @artista1 varchar(50),
        @artista2 varchar(50) ,@artista3 varchar(50) ,@artista4 varchar(50) ,@artista5 varchar(50) ,@artista6 varchar(50) ,
        @artista7 varchar(50) ,@artista8 varchar(50) ,@artista9 varchar(50) ,@artista10 varchar(50) ,@artista11 varchar(50) ,
                     @artista12 varchar(50) ,@artista13 varchar(50) ,@artista14 varchar(50) ,@artista15 varchar(50) ,@artista16 varchar(50) ,
                     @artista17 varchar(50) ,@artista18 varchar(50) ,@artista19 varchar(50) ,@artista20 varchar(50) ,@artista21 varchar(50) ,
                     @artista22 varchar(50) ,@artista23 varchar(50) ,@artista24 varchar(50) ,@artista25 varchar(50) ,@artista26 varchar(50) ,
                     @artista27 varchar(50) ,@artista28 varchar(50) ,@artista29 varchar(50) ,@artista30 varchar(50) ,@artista31 varchar(50) ,
                     @artista32 varchar(50) ,@artista33 varchar(50) ,@artista34 varchar(50) ,@artista35 varchar(50) ,@artista36 varchar(50) ,
                     @artista37 varchar(50) ,@artista38 varchar(50) ,@artista39 varchar(50) ,@artista40 varchar(50) ,@artista41 varchar(50) ,
                     @artista42 varchar(50) ,@artista43 varchar(50) ,@artista44 varchar(50) ,@artista45 varchar(50)
declare @corre_generos int
declare @peliculas_cursor as cursor
set @peliculas_cursor = cursor static
  for select ltrim(rtrim(nombre)), ltrim(rtrim(release)), ltrim(rtrim(genero)) from global
open @peliculas_cursor
fetch next from @peliculas_cursor into @nombre, @release, @genero
while @@fetch_status = 0
  begin
    if not exists (select 1 from peliculas where nombre = @nombre)
       begin
         select @corre_generos = generos.correlativo
            from generos
            where nombre = @genero
         insert peliculas (nombre, release, corre_generos)
            values (@nombre, @release, @corre_generos)
       end
    fetch next from @peliculas_cursor into @nombre, @release, @genero
  end
close @peliculas_cursor
deallocate @peliculas_cursor
go
select * from peliculas
go

/* Genera Artistas */

declare @genero varchar(50), @nombre varchar(100), @release varchar(10), @artista1 varchar(50),
        @artista2 varchar(50) ,@artista3 varchar(50) ,@artista4 varchar(50) ,@artista5 varchar(50) ,@artista6 varchar(50) ,
        @artista7 varchar(50) ,@artista8 varchar(50) ,@artista9 varchar(50) ,@artista10 varchar(50) ,@artista11 varchar(50) ,
                     @artista12 varchar(50) ,@artista13 varchar(50) ,@artista14 varchar(50) ,@artista15 varchar(50) ,@artista16 varchar(50) ,
                     @artista17 varchar(50) ,@artista18 varchar(50) ,@artista19 varchar(50) ,@artista20 varchar(50) ,@artista21 varchar(50) ,
                     @artista22 varchar(50) ,@artista23 varchar(50) ,@artista24 varchar(50) ,@artista25 varchar(50) ,@artista26 varchar(50) ,
                     @artista27 varchar(50) ,@artista28 varchar(50) ,@artista29 varchar(50) ,@artista30 varchar(50) ,@artista31 varchar(50) ,
                     @artista32 varchar(50) ,@artista33 varchar(50) ,@artista34 varchar(50) ,@artista35 varchar(50) ,@artista36 varchar(50) ,
                     @artista37 varchar(50) ,@artista38 varchar(50) ,@artista39 varchar(50) ,@artista40 varchar(50) ,@artista41 varchar(50) ,
                     @artista42 varchar(50) ,@artista43 varchar(50) ,@artista44 varchar(50) ,@artista45 varchar(50)

declare @artistas_cursor as cursor
set @artistas_cursor = cursor static
  for select ltrim(rtrim(artista1)),ltrim(rtrim(artista2)),ltrim(rtrim(artista3)),ltrim(rtrim(artista4)),ltrim(rtrim(artista5)),ltrim(rtrim(artista6)),
             ltrim(rtrim(artista7)),ltrim(rtrim(artista8)),ltrim(rtrim(artista9)),ltrim(rtrim(artista10)),ltrim(rtrim(artista11)),
             ltrim(rtrim(artista12)),ltrim(rtrim(artista13)),ltrim(rtrim(artista14)),ltrim(rtrim(artista15)),ltrim(rtrim(artista16)),
             ltrim(rtrim(artista17)),ltrim(rtrim(artista18)),ltrim(rtrim(artista19)),ltrim(rtrim(artista20)),ltrim(rtrim(artista21)),
             ltrim(rtrim(artista22)),ltrim(rtrim(artista23)),ltrim(rtrim(artista24)),ltrim(rtrim(artista25)),ltrim(rtrim(artista26)),
             ltrim(rtrim(artista27)),ltrim(rtrim(artista28)),ltrim(rtrim(artista29)),ltrim(rtrim(artista30)),ltrim(rtrim(artista31)),
             ltrim(rtrim(artista32)),ltrim(rtrim(artista33)),ltrim(rtrim(artista34)),ltrim(rtrim(artista35)),ltrim(rtrim(artista36)),
             ltrim(rtrim(artista37)),ltrim(rtrim(artista38)),ltrim(rtrim(artista39)),ltrim(rtrim(artista40)),ltrim(rtrim(artista41)),
             ltrim(rtrim(artista42)),ltrim(rtrim(artista43)),ltrim(rtrim(artista44)),ltrim(rtrim(artista45))
        from global
open @artistas_cursor
fetch next from @artistas_cursor into @artista1, @artista2,@artista3,@artista4,@artista5,@artista6, @artista7,@artista8,@artista9,@artista10,@artista11,
                                      @artista12,@artista13,@artista14,@artista15,@artista16, @artista17,@artista18,@artista19,@artista20,@artista21,
                                      @artista22,@artista23,@artista24,@artista25,@artista26, @artista27,@artista28,@artista29,@artista30,@artista31,
                                      @artista32,@artista33,@artista34,@artista35,@artista36, @artista37,@artista38,@artista39,@artista40,@artista41,
                                      @artista42,@artista43,@artista44,@artista45
while @@fetch_status = 0
  begin
    if @artista1 is not null and ltrim(rtrim(@artista1)) <> '' and len(@artista1) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista1)
            begin
              insert artistas (nombre)
                values (@artista1)
            end    
       end
    if @artista2 is not null and ltrim(rtrim(@artista2)) <> '' and len(@artista2) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista2)
            begin
              insert artistas (nombre)
                values (@artista2)
            end    
       end
    if @artista3 is not null and ltrim(rtrim(@artista3)) <> '' and len(@artista3) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista3)
            begin
              insert artistas (nombre)
                values (@artista3)
            end    
       end
    if @artista4 is not null and ltrim(rtrim(@artista4)) <> '' and len(@artista4) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista4)
            begin
              insert artistas (nombre)
                values (@artista4)
            end    
       end
    if @artista5 is not null and ltrim(rtrim(@artista5)) <> '' and len(@artista5) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista5)
            begin
              insert artistas (nombre)
                values (@artista5)
            end    
       end
    if @artista6 is not null and ltrim(rtrim(@artista6)) <> '' and len(@artista6) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista6)
            begin
              insert artistas (nombre)
                values (@artista6)
            end    
       end
    if @artista7 is not null and ltrim(rtrim(@artista7)) <> '' and len(@artista7) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista7)
            begin
              insert artistas (nombre)
                values (@artista7)
            end    
       end
    if @artista8 is not null and ltrim(rtrim(@artista8)) <> '' and len(@artista8) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista8)
            begin
              insert artistas (nombre)
                values (@artista8)
            end    
       end
    if @artista9 is not null and ltrim(rtrim(@artista9)) <> '' and len(@artista9) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista9)
            begin
              insert artistas (nombre)
                values (@artista9)
            end    
       end
    if @artista10 is not null and ltrim(rtrim(@artista10)) <> '' and len(@artista10) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista10)
            begin
              insert artistas (nombre)
                values (@artista10)
            end    
       end
    if @artista11 is not null and ltrim(rtrim(@artista11)) <> '' and len(@artista11) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista11)
            begin
              insert artistas (nombre)
                values (@artista11)
            end    
       end
    if @artista12 is not null and ltrim(rtrim(@artista12)) <> '' and len(@artista12) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista12)
            begin
              insert artistas (nombre)
                values (@artista12)
            end    
       end
    if @artista13 is not null and ltrim(rtrim(@artista13)) <> '' and len(@artista13) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista13)
            begin
              insert artistas (nombre)
                values (@artista13)
            end    
       end
    if @artista14 is not null and ltrim(rtrim(@artista14)) <> '' and len(@artista14) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista14)
            begin
              insert artistas (nombre)
                values (@artista14)
            end    
       end
    if @artista15 is not null and ltrim(rtrim(@artista15)) <> '' and len(@artista15) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista15)
            begin
              insert artistas (nombre)
                values (@artista15)
            end    
       end
    if @artista16 is not null and ltrim(rtrim(@artista16)) <> '' and len(@artista16) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista16)
            begin
              insert artistas (nombre)
                values (@artista16)
            end    
       end
    if @artista17 is not null and ltrim(rtrim(@artista17)) <> '' and len(@artista17) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista17)
            begin
              insert artistas (nombre)
                values (@artista17)
            end    
       end
    if @artista18 is not null and ltrim(rtrim(@artista18)) <> '' and len(@artista18) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista18)
            begin
              insert artistas (nombre)
                values (@artista18)
            end    
       end
    if @artista19 is not null and ltrim(rtrim(@artista19)) <> '' and len(@artista19) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista19)
            begin
              insert artistas (nombre)
                values (@artista19)
            end    
       end
    if @artista20 is not null and ltrim(rtrim(@artista20)) <> '' and len(@artista20) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista20)
            begin
              insert artistas (nombre)
                values (@artista20)
            end    
       end
    if @artista21 is not null and ltrim(rtrim(@artista21)) <> '' and len(@artista21) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista21)
            begin
              insert artistas (nombre)
                values (@artista21)
            end    
       end
    if @artista22 is not null and ltrim(rtrim(@artista22)) <> '' and len(@artista22) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista22)
            begin
              insert artistas (nombre)
                values (@artista22)
            end    
       end
    if @artista23 is not null and ltrim(rtrim(@artista23)) <> '' and len(@artista23) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista23)
            begin
              insert artistas (nombre)
                values (@artista23)
            end    
       end
    if @artista24 is not null and ltrim(rtrim(@artista24)) <> '' and len(@artista24) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista24)
            begin
              insert artistas (nombre)
                values (@artista24)
            end    
       end
    if @artista25 is not null and ltrim(rtrim(@artista25)) <> '' and len(@artista25) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista25)
            begin
              insert artistas (nombre)
                values (@artista25)
            end    
       end
    if @artista26 is not null and ltrim(rtrim(@artista26)) <> '' and len(@artista26) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista26)
            begin
              insert artistas (nombre)
                values (@artista26)
            end    
       end
    if @artista27 is not null and ltrim(rtrim(@artista27)) <> '' and len(@artista27) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista27)
            begin
              insert artistas (nombre)
                values (@artista27)
            end    
       end
    if @artista28 is not null and ltrim(rtrim(@artista28)) <> '' and len(@artista28) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista28)
            begin
              insert artistas (nombre)
                values (@artista28)
            end    
       end
    if @artista29 is not null and ltrim(rtrim(@artista29)) <> '' and len(@artista29) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista29)
            begin
              insert artistas (nombre)
                values (@artista29)
            end    
       end
    if @artista30 is not null and ltrim(rtrim(@artista30)) <> '' and len(@artista30) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista30)
            begin
              insert artistas (nombre)
                values (@artista30)
            end    
       end
    if @artista31 is not null and ltrim(rtrim(@artista31)) <> '' and len(@artista31) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista31)
            begin
              insert artistas (nombre)
                values (@artista31)
            end    
       end
    if @artista32 is not null and ltrim(rtrim(@artista32)) <> '' and len(@artista32) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista32)
            begin
              insert artistas (nombre)
                values (@artista32)
            end    
       end
    if @artista33 is not null and ltrim(rtrim(@artista33)) <> '' and len(@artista33) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista33)
            begin
              insert artistas (nombre)
                values (@artista33)
            end    
       end
    if @artista34 is not null and ltrim(rtrim(@artista34)) <> '' and len(@artista34) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista34)
            begin
              insert artistas (nombre)
                values (@artista34)
            end    
       end
    if @artista35 is not null and ltrim(rtrim(@artista35)) <> '' and len(@artista35) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista35)
            begin
              insert artistas (nombre)
                values (@artista35)
            end    
       end
    if @artista36 is not null and ltrim(rtrim(@artista36)) <> '' and len(@artista36) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista36)
            begin
              insert artistas (nombre)
                values (@artista36)
            end    
       end
    if @artista37 is not null and ltrim(rtrim(@artista37)) <> '' and len(@artista37) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista37)
            begin
              insert artistas (nombre)
                values (@artista37)
            end    
       end
    if @artista38 is not null and ltrim(rtrim(@artista38)) <> '' and len(@artista38) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista38)
            begin
              insert artistas (nombre)
                values (@artista38)
            end    
       end
    if @artista39 is not null and ltrim(rtrim(@artista39)) <> '' and len(@artista39) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista39)
            begin
              insert artistas (nombre)
                values (@artista39)
            end    
       end
    if @artista40 is not null and ltrim(rtrim(@artista40)) <> '' and len(@artista40) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista40)
            begin
              insert artistas (nombre)
                values (@artista40)
            end    
       end
    if @artista41 is not null and ltrim(rtrim(@artista41)) <> '' and len(@artista41) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista41)
            begin
              insert artistas (nombre)
                values (@artista41)
            end    
       end
    if @artista42 is not null and ltrim(rtrim(@artista42)) <> '' and len(@artista42) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista42)
            begin
              insert artistas (nombre)
                values (@artista42)
            end    
       end
    if @artista43 is not null and ltrim(rtrim(@artista43)) <> '' and len(@artista43) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista43)
            begin
              insert artistas (nombre)
                values (@artista43)
            end    
       end
    if @artista44 is not null and ltrim(rtrim(@artista44)) <> '' and len(@artista44) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista44)
            begin
              insert artistas (nombre)
                values (@artista44)
            end    
       end
    if @artista45 is not null and ltrim(rtrim(@artista45)) <> '' and len(@artista45) > 1
       begin
         if not exists (select 1 from artistas where ltrim(rtrim(nombre)) = @artista45)
            begin
              insert artistas (nombre)
                values (@artista45)
            end    
       end

    fetch next from @artistas_cursor into @artista1, @artista2,@artista3,@artista4,@artista5,@artista6, @artista7,@artista8,@artista9,@artista10,@artista11,
                                          @artista12,@artista13,@artista14,@artista15,@artista16, @artista17,@artista18,@artista19,@artista20,@artista21,
                                         @artista22,@artista23,@artista24,@artista25,@artista26, @artista27,@artista28,@artista29,@artista30,@artista31,
                                         @artista32,@artista33,@artista34,@artista35,@artista36, @artista37,@artista38,@artista39,@artista40,@artista41,
                                         @artista42,@artista43,@artista44,@artista45
  end
close @artistas_cursor
deallocate @artistas_cursor
go
select * from artistas
go

/* Genera PeliculasArtistas */

declare @genero varchar(50), @nombre varchar(100), @release varchar(10), @artista1 varchar(50),
        @artista2 varchar(50) ,@artista3 varchar(50) ,@artista4 varchar(50) ,@artista5 varchar(50) ,@artista6 varchar(50) ,
        @artista7 varchar(50) ,@artista8 varchar(50) ,@artista9 varchar(50) ,@artista10 varchar(50) ,@artista11 varchar(50) ,
                     @artista12 varchar(50) ,@artista13 varchar(50) ,@artista14 varchar(50) ,@artista15 varchar(50) ,@artista16 varchar(50) ,
                     @artista17 varchar(50) ,@artista18 varchar(50) ,@artista19 varchar(50) ,@artista20 varchar(50) ,@artista21 varchar(50) ,
                     @artista22 varchar(50) ,@artista23 varchar(50) ,@artista24 varchar(50) ,@artista25 varchar(50) ,@artista26 varchar(50) ,
                     @artista27 varchar(50) ,@artista28 varchar(50) ,@artista29 varchar(50) ,@artista30 varchar(50) ,@artista31 varchar(50) ,
                     @artista32 varchar(50) ,@artista33 varchar(50) ,@artista34 varchar(50) ,@artista35 varchar(50) ,@artista36 varchar(50) ,
                     @artista37 varchar(50) ,@artista38 varchar(50) ,@artista39 varchar(50) ,@artista40 varchar(50) ,@artista41 varchar(50) ,
                     @artista42 varchar(50) ,@artista43 varchar(50) ,@artista44 varchar(50) ,@artista45 varchar(50)
declare @corre_peliculas int
declare @corre_artistas int
declare @peliculasartistas_cursor as cursor
set @peliculasartistas_cursor = cursor static
  for select ltrim(rtrim(nombre)),ltrim(rtrim(artista1)),ltrim(rtrim(artista2)),ltrim(rtrim(artista3)),ltrim(rtrim(artista4)),ltrim(rtrim(artista5)),ltrim(rtrim(artista6)),
             ltrim(rtrim(artista7)),ltrim(rtrim(artista8)),ltrim(rtrim(artista9)),ltrim(rtrim(artista10)),ltrim(rtrim(artista11)),
             ltrim(rtrim(artista12)),ltrim(rtrim(artista13)),ltrim(rtrim(artista14)),ltrim(rtrim(artista15)),ltrim(rtrim(artista16)),
             ltrim(rtrim(artista17)),ltrim(rtrim(artista18)),ltrim(rtrim(artista19)),ltrim(rtrim(artista20)),ltrim(rtrim(artista21)),
             ltrim(rtrim(artista22)),ltrim(rtrim(artista23)),ltrim(rtrim(artista24)),ltrim(rtrim(artista25)),ltrim(rtrim(artista26)),
             ltrim(rtrim(artista27)),ltrim(rtrim(artista28)),ltrim(rtrim(artista29)),ltrim(rtrim(artista30)),ltrim(rtrim(artista31)),
             ltrim(rtrim(artista32)),ltrim(rtrim(artista33)),ltrim(rtrim(artista34)),ltrim(rtrim(artista35)),ltrim(rtrim(artista36)),
             ltrim(rtrim(artista37)),ltrim(rtrim(artista38)),ltrim(rtrim(artista39)),ltrim(rtrim(artista40)),ltrim(rtrim(artista41)),
             ltrim(rtrim(artista42)),ltrim(rtrim(artista43)),ltrim(rtrim(artista44)),ltrim(rtrim(artista45))
        from global
open @peliculasartistas_cursor
fetch next from @peliculasartistas_cursor into @nombre, @artista1, @artista2,@artista3,@artista4,@artista5,@artista6, @artista7,@artista8,@artista9,@artista10,@artista11,
                                      @artista12,@artista13,@artista14,@artista15,@artista16, @artista17,@artista18,@artista19,@artista20,@artista21,
                                      @artista22,@artista23,@artista24,@artista25,@artista26, @artista27,@artista28,@artista29,@artista30,@artista31,
                                      @artista32,@artista33,@artista34,@artista35,@artista36, @artista37,@artista38,@artista39,@artista40,@artista41,
                                      @artista42,@artista43,@artista44,@artista45
while @@fetch_status = 0
  begin
    select @corre_peliculas = correlativo
      from peliculas
      where nombre = @nombre
      
    if @artista1 is not null and ltrim(rtrim(@artista1)) <> '' and len(@artista1) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista1
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
            insert peliculasartistas (corre_peliculas, corre_artistas)
                  values (@corre_peliculas, @corre_artistas)
       end
    if @artista2 is not null and ltrim(rtrim(@artista2)) <> '' and len(@artista2) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista2
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista3 is not null and ltrim(rtrim(@artista3)) <> '' and len(@artista3) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista3
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista4 is not null and ltrim(rtrim(@artista4)) <> '' and len(@artista4) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista4
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista5 is not null and ltrim(rtrim(@artista5)) <> '' and len(@artista5) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista5
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista6 is not null and ltrim(rtrim(@artista6)) <> '' and len(@artista6) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista6
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista7 is not null and ltrim(rtrim(@artista7)) <> '' and len(@artista7) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista7
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista8 is not null and ltrim(rtrim(@artista8)) <> '' and len(@artista8) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista8
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista9 is not null and ltrim(rtrim(@artista9)) <> '' and len(@artista9) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista9
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista10 is not null and ltrim(rtrim(@artista10)) <> '' and len(@artista10) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista10
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista11 is not null and ltrim(rtrim(@artista11)) <> '' and len(@artista11) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista11
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista12 is not null and ltrim(rtrim(@artista12)) <> '' and len(@artista12) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista12
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista13 is not null and ltrim(rtrim(@artista13)) <> '' and len(@artista13) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista13
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista14 is not null and ltrim(rtrim(@artista14)) <> '' and len(@artista14) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista14
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista15 is not null and ltrim(rtrim(@artista15)) <> '' and len(@artista15) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista15
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista16 is not null and ltrim(rtrim(@artista16)) <> '' and len(@artista16) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista16
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista17 is not null and ltrim(rtrim(@artista17)) <> '' and len(@artista17) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista17
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista18 is not null and ltrim(rtrim(@artista18)) <> '' and len(@artista18) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista18
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista19 is not null and ltrim(rtrim(@artista19)) <> '' and len(@artista19) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista19
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista20 is not null and ltrim(rtrim(@artista20)) <> '' and len(@artista20) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista20
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista21 is not null and ltrim(rtrim(@artista21)) <> '' and len(@artista21) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista21
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista22 is not null and ltrim(rtrim(@artista22)) <> '' and len(@artista22) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista22
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista23 is not null and ltrim(rtrim(@artista23)) <> '' and len(@artista23) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista23
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista24 is not null and ltrim(rtrim(@artista24)) <> '' and len(@artista24) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista24
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista25 is not null and ltrim(rtrim(@artista25)) <> '' and len(@artista25) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista25
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista26 is not null and ltrim(rtrim(@artista26)) <> '' and len(@artista26) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista26
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista27 is not null and ltrim(rtrim(@artista27)) <> '' and len(@artista27) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista27
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista28 is not null and ltrim(rtrim(@artista28)) <> '' and len(@artista28) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista28
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista29 is not null and ltrim(rtrim(@artista29)) <> '' and len(@artista29) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista29
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista30 is not null and ltrim(rtrim(@artista30)) <> '' and len(@artista30) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista30
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista31 is not null and ltrim(rtrim(@artista31)) <> '' and len(@artista31) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista31
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista32 is not null and ltrim(rtrim(@artista32)) <> '' and len(@artista32) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista32
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista33 is not null and ltrim(rtrim(@artista33)) <> '' and len(@artista33) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista33
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista34 is not null and ltrim(rtrim(@artista34)) <> '' and len(@artista34) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista34
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista35 is not null and ltrim(rtrim(@artista35)) <> '' and len(@artista35) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista35
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista36 is not null and ltrim(rtrim(@artista36)) <> '' and len(@artista36) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista36
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista37 is not null and ltrim(rtrim(@artista37)) <> '' and len(@artista37) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista37
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista38 is not null and ltrim(rtrim(@artista38)) <> '' and len(@artista38) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista38
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista39 is not null and ltrim(rtrim(@artista39)) <> '' and len(@artista39) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista39
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista40 is not null and ltrim(rtrim(@artista40)) <> '' and len(@artista40) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista40
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista41 is not null and ltrim(rtrim(@artista41)) <> '' and len(@artista41) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista41
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista42 is not null and ltrim(rtrim(@artista42)) <> '' and len(@artista42) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista42
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista43 is not null and ltrim(rtrim(@artista43)) <> '' and len(@artista43) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista43
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista44 is not null and ltrim(rtrim(@artista44)) <> '' and len(@artista44) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista44
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end
    if @artista45 is not null and ltrim(rtrim(@artista45)) <> '' and len(@artista45) > 1
       begin
         select @corre_artistas = correlativo
           from artistas
           where nombre = @artista45
         if not exists (select 1 from peliculasartistas where corre_peliculas = @corre_peliculas and corre_artistas = @corre_artistas)
         insert peliculasartistas (corre_peliculas, corre_artistas)
                values (@corre_peliculas, @corre_artistas)
       end

    fetch next from @peliculasartistas_cursor into @nombre, @artista1, @artista2,@artista3,@artista4,@artista5,@artista6, @artista7,@artista8,@artista9,@artista10,@artista11,
                                          @artista12,@artista13,@artista14,@artista15,@artista16, @artista17,@artista18,@artista19,@artista20,@artista21,
                                         @artista22,@artista23,@artista24,@artista25,@artista26, @artista27,@artista28,@artista29,@artista30,@artista31,
                                         @artista32,@artista33,@artista34,@artista35,@artista36, @artista37,@artista38,@artista39,@artista40,@artista41,
                                         @artista42,@artista43,@artista44,@artista45
  end
close @peliculasartistas_cursor
deallocate @peliculasartistas_cursor
go
select * from peliculasartistas
go
                     
select nombre, count(*)
  from global 
  group by nombre
  having count(*) > 1
go  




select peliculas.correlativo, generos.nombre, peliculas.nombre, artistas.correlativo, artistas.nombre
  from peliculas inner join generos on (peliculas.corre_generos = generos.correlativo)
                 inner join peliculasartistas on (peliculas.correlativo = peliculasartistas.corre_peliculas)
                 inner join artistas on (peliculasartistas.corre_artistas = artistas.correlativo)
  where peliculas.nombre like '%Que esperar%'
order by peliculas.nombre
go               
select * from global where nombre = 'Amor a primera Visa'
go
/* Peliculas */
select 'Create (P'+cast(peliculas.correlativo as varchar)+':Movie {title:"'+peliculas.nombre+'", released:'+cast(peliculas.release as varchar) + ', tagline:"tagline", genre :"'+generos.nombre+'"})'
  from peliculas inner join generos on (peliculas.corre_generos = generos.correlativo)
go
/*artistas*/
select 'Create (A' +cast(artistas.correlativo as varchar) +  ':Person {name:"' + artistas.nombre + '", born:1950})' from artistas
go
/*relaciones*/

select '(A'+cast(artistas.correlativo as varchar) +') - [:ACTED_IN]->(P'+cast(peliculas.correlativo as varchar)+'),'
   from peliculas inner join peliculasartistas on (peliculas.correlativo = peliculasartistas.corre_peliculas)
                  inner join artistas on (peliculasartistas.corre_artistas = artistas.correlativo)
                  inner join generos on (peliculas.corre_generos = generos.correlativo)
go                  


select peliculas.correlativo, generos.nombre, peliculas.nombre, artistas.correlativo, artistas.nombre
  from peliculas inner join generos on (peliculas.corre_generos = generos.correlativo)
                 inner join peliculasartistas on (peliculas.correlativo = peliculasartistas.corre_peliculas)
                 inner join artistas on (peliculasartistas.corre_artistas = artistas.correlativo)
  where peliculas.nombre like '%crucificada%'
order by artistas.correlativo
go

select * from peliculasartistas where corre_peliculas = 3
order by corre_artistas
go




select corre_peliculas, corre_artistas, count(*)
   from peliculasartistas
   group by corre_peliculas, corre_artistas
   having count(*) > 1
go   


