package santander


import java.time.LocalDateTime

class BootStrap {

    def init = { servletContext ->
        initUsersRolesYMeetUps()
        //cargarDatosClima()
    }

    private static initUsersRolesYMeetUps() {

        Usuario admin = new Usuario(username: 'admin', password: 'admin').save()
        Usuario usuario = new Usuario(username: 'usuario', password: 'usuario').save()

        UsuarioRol.create(usuario, Rol.getUserRol().save(), true)
        UsuarioRol.create(admin, Rol.getAdminRol().save(), true)

        //creadorMeetups(admin)
    }

    private static creadorMeetups(Usuario usuario){
        def meetUp = creaMeetUp("MeetUp 2020", "test")
        meetUp.save()
        usuario.addToMeetUps(meetUp)
    }

    private static MeetUp creaMeetUp(String titulo, String descripcion){
        new MeetUp(titulo: titulo, descripcion: descripcion,fecha: LocalDateTime.now())
    }

    def destroy = {
    }
}
