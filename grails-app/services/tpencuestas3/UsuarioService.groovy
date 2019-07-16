package tpencuestas3

import grails.gorm.services.Service
import org.springframework.transaction.annotation.Transactional


@Service(Usuario)
@Transactional
interface UsuarioService {

    Usuario get(Serializable id)

    @Transactional (readOnly = true)
    List<Usuario> list(Map args)

    //List<Encuesta> encuestaList(Usuario usuario)

    Long count()

    void delete(Serializable id)


    Usuario save(Usuario usuario)

}