package santander

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

@Service(MeetUp)
abstract class MeetUpService implements IMeetUpService {

    @Transactional
    MeetUp guardar(MeetUp meetUp, Usuario usuario) {
        save(meetUp)
    }

}
