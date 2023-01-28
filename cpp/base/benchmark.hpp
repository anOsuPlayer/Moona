#pragma once

#ifndef MOONA_BENCHMARK
    #define MOONA_BENCHMARK

    namespace moona {

        class Benchmark {
            private:
                Benchmark();
                ~Benchmark();

            public:
                static void printAssertion(bool value);
        };
    }

#endif