#pragma once

#include "moonacore.hpp"
#include "moonahandlingexception.hpp"
#include "../util/setting.hpp"

namespace moona {

    class MoonaSetting : public Object<MoonaSetting>, public Setting {
        private:
            MoonaSetting();
            MoonaSetting(bool value);
            ~MoonaSetting() = default;

        public:
            virtual void enable() const override final;
            virtual void disable() const override final;

        friend class Moona;
    };
}