#include "setting.hpp"

namespace moona {

    Setting::Setting() {
        this->value = false;
    }

    Setting::Setting(bool value) : Property<bool>(value) {
    }

    Setting::~Setting() {
    }

    void Setting::enable() {
        this->value = true;
    }
    void Setting::disable() {
        this->value = false;
    }
}