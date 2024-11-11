package service;

import modelo.FavoritoPromocion;
import modelo.Promocion;
import modelo.Usuario;

public class FavoritoPromocionService {

    // Verificar si el usuario ya reaccionó a la promoción
    public boolean usuarioHaReaccionado(Usuario usuario, Promocion promocion) {
        return promocion.getFavoritosPromocion().stream()
                .anyMatch(favorito -> favorito.getUsuario().equals(usuario));
    }

    // Método para crear una nueva reacción "Me Encanta"
    public FavoritoPromocion agregarMeEncanta(Promocion promocion, Usuario usuario) {
        FavoritoPromocion favorito = new FavoritoPromocion();
        favorito.setUsuario(usuario);
        favorito.setPromocion(promocion);
        promocion.getFavoritosPromocion().add(favorito); // Agregar a la lista de favoritos
        return favorito; // Retornar para persistir
    }
}