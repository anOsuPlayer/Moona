#include "hallwayexception.hpp"

namespace moona {

    HallwayAccessException::HallwayAccessException()
        : Exception((Moona::enableHallwayAccess) ? "Moona needs to be active in order to access Hallway features." :
        "In order to access the Hallway Interface you need to enable the Moona.enableHallwayAccess setting.") { }

    HallwayAccessException::HallwayAccessException(const char* message) : Exception(message) {
    }

    HallwayAccessException::~HallwayAccessException() {
    }
}