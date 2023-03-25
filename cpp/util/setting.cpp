#include "setting.hpp"

namespace moona {

    Setting::Setting() {
        this->value = false;
    }

    Setting::Setting(bool value) {
        this->value = value;
    }

    Setting::~Setting() {
    }

    void Setting::enable() const {
        this->value = true;
    }
    void Setting::disable() const {
        this->value = false;
    }
}