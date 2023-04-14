#include "moonasetting.hpp"

namespace moona {

    MoonaSetting::MoonaSetting() : Setting(false) {
    }

    MoonaSetting::MoonaSetting(bool value) : Setting(value) {
    }

    void MoonaSetting::enable() const {
        if (MoonaCore::on) {
            throw MoonaHandlingException("MoonaSettings cannot be changed after Moona is started.");
        }
        this->value = true;
    }
    void MoonaSetting::disable() const {
        if (MoonaCore::on) {
            throw MoonaHandlingException("MoonaSettings cannot be changed after Moona is started.");
        }
        this->value = false;
    }
}