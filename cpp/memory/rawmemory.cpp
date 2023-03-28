#include "rawmemory.hpp"

namespace moona {

    ChainedPointer::ChainedPointer(std::any* value, ChainedPointer* next, ChainedPointer* prev) {
        this->value = value;
        this->next = next;
        this->prev = prev;
    }

    ChainedPointer::ChainedPointer(ChainedPointer* next, ChainedPointer* prev) : ChainedPointer(nullptr, next, prev) {
    }

    ChainedPointer::ChainedPointer() : ChainedPointer(nullptr, nullptr, nullptr) {
    }

    ChainedPointer::~ChainedPointer() {
        delete this->value;
        delete this->next;
    }

    void ChainedPointer::setPrev(ChainedPointer* prev) const {
        this->prev = prev;
    }
    void ChainedPointer::setNext(ChainedPointer* next) const {
        this->next = next;
    }

    const std::any& ChainedPointer::get() const {
        return *this->value;
    }

    const ChainedPointer* ChainedPointer::getPrev() const {
        return this->prev;
    }
    const ChainedPointer* ChainedPointer::getNext() const {
        return this->next;
    }

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

    int RawMemory::getSize() const noexcept {
        return this->size;
    }
}