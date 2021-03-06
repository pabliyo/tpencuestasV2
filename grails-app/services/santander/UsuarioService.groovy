package santander

import grails.gorm.services.Service
import org.springframework.transaction.annotation.Transactional

@Service(Usuario)
@Transactional
interface UsuarioService {

    Usuario get(Serializable id)

    @Transactional(readOnly = true)
    List<Usuario> list(Map args)

    Long count()

    void delete(Serializable id)

    Usuario save(Usuario usuario)

}
