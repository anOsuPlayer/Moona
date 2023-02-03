#pragma once

#ifndef MOONA_BENCHMARK_CLASS
    #define MOONA_BENCHMARK_CLASS

    #include <iostream>

    namespace moona {

        class Benchmark {
            private:
                Benchmark();
                ~Benchmark();

            public:
                static void printAssertion(const bool& value);
        };
    }

#endif