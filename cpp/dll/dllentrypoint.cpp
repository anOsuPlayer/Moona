#include "dllutil.hpp"

DLLEntryPoint(HINSTANCE hinst, unsigned long reason, void* lpReserved) {
    return 1;
}

WinMain(HINSTANCE hInstance, HINSTANCE hPrevInstance, LPSTR lpCmdLine, int nCmdShow) {
    return 0;
}