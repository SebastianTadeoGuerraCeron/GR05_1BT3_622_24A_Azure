package service;

import modelo.FavoritoPromocion;
import modelo.Promocion;
import modelo.Usuario;

public class FavoritoPromocionService {

    // Verificar si el usuario ya reaccionó a la promoción y refactorizae
    public boolean usuarioHaReaccionado(Usuario usuario, Promocion promocion) {
        return existeFavoritoDeUsuario(promocion, usuario);
    }

    private boolean existeFavoritoDeUsuario(Promocion promocion, Usuario usuario) {
        return promocion.getFavoritosPromocion().stream()
                .anyMatch(favorito -> favorito.getUsuario().equals(usuario));
    }


    // Método para crear una nueva reacción "Me Encanta" y refactorizacion
    public FavoritoPromocion agregarMeEncanta(Promocion promocion, Usuario usuario) {
        FavoritoPromocion favorito = crearFavorito(promocion, usuario);
        agregarFavoritoAPromocion(promocion, favorito);
        return favorito;
    }

    private FavoritoPromocion crearFavorito(Promocion promocion, Usuario usuario) {
        FavoritoPromocion favorito = new FavoritoPromocion();
        favorito.setUsuario(usuario);
        favorito.setPromocion(promocion);
        return favorito;
    }

    private void agregarFavoritoAPromocion(Promocion promocion, FavoritoPromocion favorito) {
        promocion.getFavoritosPromocion().add(favorito);
    }

}