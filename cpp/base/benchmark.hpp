#pragma once

#include <iostream>

namespace moona {

    class Benchmark {
        private:
            Benchmark() = delete;
            ~Benchmark() = delete;

        public:
            static void printAssertion(const bool& value) noexcept;
    };
}