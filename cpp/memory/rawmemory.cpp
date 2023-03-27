#include "rawmemory.hpp"

namespace moona {

    RawMemory::RawMemory(ChainedPointer* begin, ChainedPointer* end) {
        this->begin = begin; this->end = end;
    }

    RawMemory::RawMemory() {
        this->begin = new ChainedPointer();
        this->end = new ChainedPointer();

        this->begin->setNext(end); this->begin->setPrev(nullptr);
        this->end->setNext(nullptr); this->end->setPrev(begin);
    }

    RawMemory::~RawMemory() {
        delete this->begin;
    }

    int RawMemory::getSize() const {
        return this->size;
    }
}