#include "setting.hpp"

namespace moona {

    Setting::Setting() : Property<bool>(false) {
    }

    Setting::Setting(bool value) : Property<bool>(value) {
    }

    void Setting::enable() const {
        this->value = true;
    }
    void Setting::disable() const {
        this->value = false;
    }

    const char* Setting::toString() const noexcept {
        return (this->value) ? "enabled" : "disabled";
    }
}