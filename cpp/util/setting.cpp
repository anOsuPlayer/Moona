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

    void Setting::enable() const noexcept {
        this->value = true;
    }
    void Setting::disable() const noexcept {
        this->value = false;
    }
}