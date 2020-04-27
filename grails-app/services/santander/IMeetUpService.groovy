package santander


interface IMeetUpService {

    MeetUp get(Serializable id)

    List<MeetUp> list(Map args)

    Long count()

    void delete(Serializable id)

    MeetUp save(MeetUp meetUp)

}
